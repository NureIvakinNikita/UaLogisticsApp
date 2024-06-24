import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BattalionCommanderControllerService, BrigadeCommanderControllerService, LogisticCommanderControllerService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';
import { Role } from '../../services/models/Role';
import { SupplyRequest } from '../../services/models/supply-request';
import { HttpErrorResponse } from '@angular/common/http';
import { SendResources1$Params } from '../../services/fn/brigade-commander-controller/send-resources-1';
import { CarCheckDto, ResourcesUpdateResponse, SupplyCarDto } from '../../services/models';
import { MatDialog } from '@angular/material/dialog';
import { SendingResourcesComponent } from '../popups/sending-resources/sending-resources.component';
import { CarPopupComponent } from '../popups/car-popup/car-popup.component';
@Component({
  selector: 'app-supply-requests',
  templateUrl: './supply-requests.component.html',
  styleUrl: './supply-requests.component.scss'
})
export class SupplyRequestsComponent implements OnInit {


  role: string = '';
  supplyRequests: SupplyRequest[] = [];
  errorMsg: Array<string> = [];
  reourcesUpdated?: boolean;
  needForSupply?: boolean;
  type: string = "";

  constructor(
    private router: Router,
    private tokenService: TokenService,
    private route: ActivatedRoute,
    private brigadeCommanderService: BrigadeCommanderControllerService,
    private logisticService: LogisticCommanderControllerService,
    private battalionService: BattalionCommanderControllerService,
    private dialogModul: MatDialog
  ) {}

  ngOnInit() {
    this.role = this.tokenService.getRoleFromToken();
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.type = state.type;
    });
    if (this.role === Role.BRIGADE_COMMANDER) {
      if (this.type === "groups") {
        this.loadBattalionsRequests();
      } else {
        this.loadBrigadeRequests();
      }
    } else if (this.role === Role.LOGISTIC_COMMANDER) {
      this.loadRequestsForLogistic();
    }
  }

  loadBattalionsRequests() {
    this.brigadeCommanderService.getBattalionsRequests().subscribe({
      next: (response: SupplyRequest[]) => {
        this.supplyRequests = response;
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  loadRequestsForLogistic(){
    this.logisticService.getAllRequests().subscribe({
      next: (response: SupplyRequest[]) => {
        this.supplyRequests = response;
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  loadBrigadeRequests() {
    this.brigadeCommanderService.getBrigadeRequests().subscribe({
      next: (response: SupplyRequest[]) => {
        this.supplyRequests = response;
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  sendResources(id: number) {
    const supplyRequest = this.supplyRequests.find(request => request.requestId === id);
    
    if (!supplyRequest) {
      this.errorMsg = ["Supply request not found."];
      return;
    }
  
    const params: SendResources1$Params = { body: supplyRequest };
  
    this.brigadeCommanderService.sendResources1(params).subscribe({
      next: (response: ResourcesUpdateResponse) => {
        this.needForSupply = response.needForSupply;
        this.reourcesUpdated = response.updated;
        console.log('Resources updated successfully', response);
        this.loadBattalionsRequests();
        this.openDialog(response.needForSupply!, response.updated!);
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }
  
  openDialog(needForSupply: boolean, reourcesUpdated: boolean) {
    this.dialogModul.open(SendingResourcesComponent, {
      data: { needForSupply, reourcesUpdated }
    });
  }

  execute(id: number){
    this.logisticService.takeExecutionOfRequest({id: id}).subscribe({
      next: (response) => {
        this.loadRequestsForLogistic();
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  confirm(id: number) {
    this.logisticService.confirmDeliveryOfRequest({id: id}).subscribe({
      next: (response) => {
        this.loadRequestsForLogistic();
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  createSupplyCar(id: number) {
    const request = this.supplyRequests.find(req => req.requestId === id);
    const car: SupplyCarDto = {
      supplyRequestId: request?.requestId
    };
  
    this.logisticService.createSupplyCar({body: car}).subscribe({
      next: (response) => {
        this.loadRequestsForLogistic();
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  openCarPopup() {
    this.dialogModul.open(CarPopupComponent);
  }

  getCarCkecs(id: number) {
    this.logisticService.getCarChecks({id: id}).subscribe({
      next: (response: CarCheckDto[]) => {
        this.openCarChecks(response);
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


  openCarChecks(res: CarCheckDto[]) {
    const type = this.type;
    this.router.navigate(['/car-checks'], { state: { type, res } });  
  }

  goBack() {
    const type = this.type;
    console.log(type + "supply");
    this.router.navigate(['/battle-groups'], { state: { type } });  
  }

  seeResources(id: number) {
    const supplyRequest = this.supplyRequests.find(request => request.requestId === id);
    const resourcesRequest = supplyRequest?.resourcesRequestId;
    const type = this.type;
    this.router.navigate(['/requested-resources'], { state: { resourcesRequest, type } });
  }
}
