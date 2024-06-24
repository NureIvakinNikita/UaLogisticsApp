import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { BattalionGroupDto, BrigadeGroupDto, CompanyGroupDto } from '../../services/models';
import { BattalionCommanderControllerService, BrigadeCommanderControllerService } from '../../services/services';
import { Commander } from '../../services/models/commander';
import { Role } from '../../services/models/Role';



@Component({
  selector: 'app-battle-groups-list',
  templateUrl: './battle-groups-list.component.html',
  styleUrls: ['./battle-groups-list.component.scss']
})
export class BattleGroupsListComponent implements OnInit {


  role: string = '';
  battleGroups: BattalionGroupDto[] = [];
  brigadeGroups: BrigadeGroupDto[] = [];
  companyGroups: CompanyGroupDto[] = [];
  commander: Commander | undefined;
  commanders: Commander[] = [];
  type: string = "";
  constructor(
    private router: Router,
    private tokenService: TokenService,
    private route: ActivatedRoute,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private battalionCommanderService: BattalionCommanderControllerService,
    
  ) {}

  ngOnInit() {
    this.role = this.tokenService.getRoleFromToken();
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.type = state.type;
      console.log(this.type);
    });
    if (this.type === "groups") {
      if (this.role === Role.BRIGADE_COMMANDER) {
        this.loadBattalionGroups();
      }
    } else if (this.type !== ""){
      this.loadYourGroup(this.type);
    }
    
  }

  loadYourGroup(type: string) {
    if (type === "Brigade") {
      this.brigadeCommanderService.getBrigadeResources().subscribe(
        (data: BrigadeGroupDto) => {
          this.brigadeGroups.push(data);
        },
        (error) => {
          console.error('Error loading brigade group', error);
        }
      );
    } else if (type == "Battalion") {
      this.battalionCommanderService.getBattalionResources().subscribe(
        (data: BattalionGroupDto) => {
          this.battleGroups.push(data);
        },
        (error) => {
          console.error('Error loading battalion group', error);
        }
      );
    }
  }

  loadBattalionGroups() {
    this.brigadeCommanderService.getBattalionGroups().subscribe(
      (data: BattalionGroupDto[]) => {
        this.battleGroups = data;
      },
      (error) => {
        console.error('Error loading battalion groups', error);
      }
    );
  }

  seeResource(id: number) {
    const battleGroup = this.currentGroups.find(group => group.id === id);
    const type = this.type;
    const actionType = this.getAction();
    
    if (battleGroup) {
      this.router.navigate(['/group-resources'], { state: { battleGroup, type, actionType} });
    }
  }

  seeCommanderInfo(id: number) {
    const battleGroup = this.currentGroups.find(group => group.id === id);
    const type = this.type;
    if (battleGroup?.commander) {
      const commander = battleGroup.commander;
      this.router.navigate(['/commander-info'], { state: { commander, type } });
    }
  }

  addBattleGroup() {
    const actionType = "create"
    const type = this.type;
    this.router.navigate(['/group-resources'], { state: { actionType, type } });
  }

  assignCommander(id: number){
    const type = this.type;
    this.router.navigate(['/assing-commander'], { state: { id, type } });
  }

  askForResources() {
    const actionType = "ask"
    const battleGroup = this.currentGroups[0];
    const commanderId = battleGroup.commander?.id;
    const militaryGroupId = battleGroup.id;
    const roleOfCommander = battleGroup.commander?.role;
    const type = this.type;
    this.router.navigate(['/requested-resources'], { state: { actionType, type, commanderId, militaryGroupId, roleOfCommander } });
  }

  seeSupplyRequests() {
    const type = this.type;
    this.router.navigate(['/supply-requests'], { state: { type } });
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

  getAction() {
    if (this.type !== 'groups') {
      return "update";
    } else {
      return "none";
    }
  }

  get currentGroups() {

    if (this.role === Role.BRIGADE_COMMANDER) {
      if (this.type === "Brigade") {
        return this.brigadeGroups;
      } else {
        return this.battleGroups;
      }
    } else if (this.role === Role.BATTALION_COMMANDER){
      if (this.type === "Battalion") {
        return this.battleGroups;
      } else {
        return this.companyGroups;
      }
    } else {
      return [];
    }
  }
}