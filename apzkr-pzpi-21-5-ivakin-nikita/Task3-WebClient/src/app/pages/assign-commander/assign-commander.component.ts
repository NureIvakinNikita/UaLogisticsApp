import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrigadeCommanderControllerService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';
import { Role } from '../../services/models/Role';
import { HttpErrorResponse } from '@angular/common/http';
import { AssignBattalionCommander$Params } from '../../services/fn/brigade-commander-controller/assign-battalion-commander';

@Component({
  selector: 'app-assign-commander',
  templateUrl: './assign-commander.component.html',
  styleUrl: './assign-commander.component.scss'
})
export class AssignCommanderComponent implements OnInit {

  assign = {
    groupId: 0,
    commanderId: 0
  }
  role: string | undefined;
  errorMsg: Array<string> = [];
  type:string = '';
  
  constructor(
    private router: Router,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private route: ActivatedRoute,
    private tokenService: TokenService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.assign.groupId = state.id;
      this.type = state.type;
    });
    this.role = this.tokenService.getRoleFromToken();
  }


  onSubmit() {
    this.errorMsg = [];
    if (this.role === Role.BRIGADE_COMMANDER) {
      const params: AssignBattalionCommander$Params = {
        battalionGroupId: this.assign.groupId,
        battalionCommanderId: this.assign.commanderId
      };
      this.brigadeCommanderService.assignBattalionCommander(params).subscribe({
        next: (response) => {
          console.log('Battalion created successfully', response);
          const type = this.type;
          this.router.navigate(['/battle-groups'], { state: { type } });  // Redirect after successful creation
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


  onCancel() {
    const type = this.type;
    this.router.navigate(['/battle-groups'], { state: { type } });
  }
}
