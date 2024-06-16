import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';
import { BattalionGroupDto } from '../../services/models';
import { BrigadeCommanderControllerService } from '../../services/services';
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
  commander: Commander | undefined;

  constructor(
    private router: Router,
    private tokenService: TokenService,
    private brigadeCommanderService: BrigadeCommanderControllerService
  ) {}

  ngOnInit() {
    this.role = this.tokenService.getRoleFromToken();
    if (this.role === Role.BRIGADE_COMMANDER) {
      this.loadBattalionGroups();
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
    const battalionGroup = this.battleGroups.find(group => group.id === id);
    if (battalionGroup) {
      this.router.navigate(['/group-resources'], { state: { battalionGroup } });
    }
  }

  seeCommanderInfo(id: number) {
    const battalionGroup = this.battleGroups.find(group => group.id === id);
    if (battalionGroup?.battalionCommanderDTO) {
      const commander = battalionGroup.battalionCommanderDTO;
      this.router.navigate(['/commander-info'], { state: { commander } });
    }
  }

  addBattleGroup() {
    const actionType = "create"
    this.router.navigate(['/group-resources'], { state: { actionType } });
  }

  assignCommander(id: number){
    const type = this.getType();
    this.router.navigate(['/assing-commander'], { state: { id, type } });
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