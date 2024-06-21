import { Component, OnInit } from '@angular/core';
import { ResourcesRequest, ResourcesRequestDto } from '../../services/models';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { HttpErrorResponse } from '@angular/common/http';
import { BrigadeCommanderControllerService, BattalionCommanderControllerService } from '../../services/services';

@Component({
  selector: 'app-requested-resources',
  templateUrl: './requested-resources.component.html',
  styleUrl: './requested-resources.component.scss'
})
export class RequestedResourcesComponent implements OnInit {
  requestedResources: ResourcesRequest = {
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
  }
  requestedResourcesDto: ResourcesRequestDto = {
    commanderId: 0,
    militaryGroupId: 0,
    roleOfCommander: 'BRIGADE_COMMANDER',
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
  }
  
  type: string = "";
  actionType: string = "";
  errorMsg: Array<string> = [];

  constructor(private router: Router, private route: ActivatedRoute,
    private tokenService: TokenService,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private battalionCommanderService: BattalionCommanderControllerService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.actionType = state.actionType;
      if (this.actionType !== 'ask') this.requestedResources = state.resourcesRequest;
      else {
        this.requestedResourcesDto.commanderId = state.commanderId;
        this.requestedResourcesDto.militaryGroupId = state.militaryGroupId;
        this.requestedResourcesDto.roleOfCommander = state.roleOfCommander;
      }
      console.log(this.requestedResources);
      this.type = state.type;
      
    });
  }

  onSubmit(){
    this.errorMsg = [];
    if (this.actionType === "ask") {
      this.makeRequest();
    } 
  }

  makeRequest() {
    if (this.type === "Brigade") {
      this.convertToDto(this.requestedResources);
      this.brigadeCommanderService.askForResources2({ body: this.requestedResourcesDto }).subscribe({
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

  convertToDto(resources: ResourcesRequest) {
    this.requestedResourcesDto.ammo145KpvtCount = resources.ammo145KpvtCount;
    this.requestedResourcesDto.ammo40mmGpCount = resources.ammo40mmGpCount;
    this.requestedResourcesDto.ammo40mmRpgCount = resources.ammo40mmRpgCount;
    this.requestedResourcesDto.ammo545x39AkRpkCount = resources.ammo545x39AkRpkCount;
    this.requestedResourcesDto.ammo556x45ArCount = resources.ammo556x45ArCount;
    this.requestedResourcesDto.ammo762PktCount = resources.ammo762PktCount;
    this.requestedResourcesDto.ammo762x39AkCount = resources.ammo762x39AkCount;
    this.requestedResourcesDto.apcCount = resources.apcCount;
    this.requestedResourcesDto.bodyArmorCount = resources.bodyArmorCount;
    this.requestedResourcesDto.defensiveGrenadesCount = resources.defensiveGrenadesCount;
    this.requestedResourcesDto.dryRationsCount = resources.dryRationsCount;
    this.requestedResourcesDto.foodCount = resources.foodCount;
    this.requestedResourcesDto.helmetsCount = resources.helmetsCount;
    this.requestedResourcesDto.machineGunsCount = resources.machineGunsCount;
    this.requestedResourcesDto.offensiveGrenadesCount = resources.offensiveGrenadesCount;
    this.requestedResourcesDto.riflesCount = resources.riflesCount;
    this.requestedResourcesDto.tankCount = resources.tankCount;
  }

  goBack() {
    const type = this.type;
    console.log(this.actionType);
    if (this.actionType === 'ask') this.router.navigate(['/battle-groups'], { state: { type } }); 
    else this.router.navigate(['/supply-requests'], { state: { type } });
  }
}
