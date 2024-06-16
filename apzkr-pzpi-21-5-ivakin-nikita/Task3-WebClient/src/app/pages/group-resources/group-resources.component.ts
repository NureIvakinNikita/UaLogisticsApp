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

  battalionGroup: BattalionGroupDto = {
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
  constructor(private router: Router, private route: ActivatedRoute,
    private tokenService: TokenService,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private battalionCommanderService: BattalionCommanderControllerService
  ) {}

  ngOnInit() {
    this.role = this.tokenService.getRoleFromToken();
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.battalionGroup = state.battalionGroup;
      console.log(this.battalionGroup);
      this.actionType = state.actionType;

      if (this.actionType === 'create') {
        this.battalionGroup = {
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
      } else if (!this.battalionGroup) {
        console.error('No battalion group data available');
      }
    });
  }

  onSubmit() {
    this.errorMsg = [];
    if (this.actionType === "create") {
      if (this.getType() === "battalion") {
        this.brigadeCommanderService.createBattalion({ body: this.battalionGroup }).subscribe({
          next: (response) => {
            console.log('Battalion created successfully', response);
            this.router.navigate(['/battle-groups']);  // Redirect after successful creation
          },
          error: (err: HttpErrorResponse) => {
            if (err.error) {
              this.errorMsg = err.error.validationErrors || [];
            } else {
              console.log(err);
              this.errorMsg = ["An unexpected error occurred. Please try again."];
            }
          }
        });
      }
    }
  }

  goBack() {
    this.router.navigate(['/battle-groups']);
  }


  getType() {
    switch (this.role) {
      case Role.BRIGADE_COMMANDER:
        return 'battalion';
      case Role.BATTALION_COMMANDER:
        return 'company';
      case Role.COMPANY_COMMANDER:
        return 'plat';
      case Role.PLAT_COMMANDER:
        return 'brigade';
      default:
        return 'Error';
    }
  }
}
