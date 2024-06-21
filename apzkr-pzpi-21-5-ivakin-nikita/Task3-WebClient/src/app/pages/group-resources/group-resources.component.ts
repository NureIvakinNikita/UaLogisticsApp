import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BattalionGroupDto } from '../../services/models';
import { TokenService } from '../../services/token/token.service';
import { Role } from '../../services/models/Role';
import { BattalionCommanderControllerService, BrigadeCommanderControllerService } from '../../services/services';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-group-resources',
  templateUrl: './group-resources.component.html',
  styleUrls: ['./group-resources.component.scss']
})
export class GroupResourcesComponent implements OnInit {

  battleGroup: any = {
    id: 0,
    ammo556x45ArCount: 0,
    ammo40mmRpgCount: 0,
    offensiveGrenadesCount: 0,
    machineGunsCount: 0,
    ammo545x39AkRpkCount: 0,
    ammo40mmGpCount: 0,
    bodyArmorCount: 0,
    dryRationsCount: 0,
    ammo762x39AkCount: 0,
    ammo762PktCount: 0,
    helmetsCount: 0,
    tankCount: 0,
    ammo145KpvtCount: 0,
    defensiveGrenadesCount: 0,
    riflesCount: 0,
    apcCount: 0
  };
  
  actionType: string | undefined;
  role: string | undefined;
  errorMsg: Array<string> = [];
  type: string = "";
  constructor(private router: Router, private route: ActivatedRoute,
    private tokenService: TokenService,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private battalionCommanderService: BattalionCommanderControllerService
  ) {}

  ngOnInit() {
    this.role = this.tokenService.getRoleFromToken();
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.battleGroup = state.battleGroup;
      this.type = state.type;
      console.log(this.battleGroup);
      this.actionType = state.actionType;

      if (this.actionType === 'create') {
        this.battleGroup = {
          id: 0,
          personnelCount: 0,
          ammo556x45ArCount: 0,
          ammo40mmRpgCount: 0,
          offensiveGrenadesCount: 0,
          machineGunsCount: 0,
          ammo545x39AkRpkCount: 0,
          ammo40mmGpCount: 0,
          bodyArmorCount: 0,
          dryRationsCount: 0,
          ammo762x39AkCount: 0,
          ammo762PktCount: 0,
          helmetsCount: 0,
          tankCount: 0,
          ammo145KpvtCount: 0,
          defensiveGrenadesCount: 0,
          riflesCount: 0,
          apcCount: 0
        };
      } else if (!this.battleGroup) {
        console.error('No battalion group data available');
      }
    });
  }

  onSubmit() {
    this.errorMsg = [];
    if (this.actionType === "create") {
      if (this.getType() === "Battalion") {
        this.brigadeCommanderService.createBattalion({ body: this.battleGroup }).subscribe({
          next: (response) => {
            console.log('Battalion created successfully', response);
            const type = this.type;
            this.router.navigate(['/battle-groups'], { state: { type } });  
          },
          error: (err: HttpErrorResponse) => {
            if (err.error) {
              this.errorMsg = err.error.validationErrors || err.error.message;
            } else {
              console.log(err);
              this.errorMsg = ["An unexpected error occurred. Please try again."];
            }
          }
        });
      }
    } if (this.actionType === "update") {
      this.update();
    } 
  }

  update(){
    if (this.getType() === "Brigade")  {
      this.brigadeCommanderService.updateBrigadeResources({ body: this.battleGroup }).subscribe({
        next: (response) => {
          const type = this.type;
          this.router.navigate(['/battle-groups'], { state: { type } });  
        },
        error: (err: HttpErrorResponse) => {
          if (err.error) {
            this.errorMsg = err.error.validationErrors || err.error.message;
          } else {
            console.log(err);
            this.errorMsg = ["An unexpected error occurred. Please try again."];
          }
        }
      });
    }
  }

  goBack() {
    const type = this.type;
    this.router.navigate(['/battle-groups'], { state: { type } });  
  }


  getType() {
    switch (this.role) {
      case Role.BRIGADE_COMMANDER:
        return this.type === "" || this.type === "groups" ? 'Battalion' : this.type;
      case Role.BATTALION_COMMANDER:
        return this.type === "" || this.type === "groups" ? 'Company' : this.type;
      case Role.COMPANY_COMMANDER:
        return this.type === "" || this.type === "groups" ? 'Plat' : this.type;
      case Role.PLAT_COMMANDER:
        return 'Plat';
      default:
        return 'Error';
    }
  }
}
