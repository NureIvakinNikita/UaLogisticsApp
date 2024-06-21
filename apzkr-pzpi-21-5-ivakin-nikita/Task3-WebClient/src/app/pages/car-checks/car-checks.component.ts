import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TokenService } from '../../services/token/token.service';
import { Role } from '../../services/models/Role';
import { CarCheckDto } from '../../services/models';

@Component({
  selector: 'app-car-checks',
  templateUrl: './car-checks.component.html',
  styleUrl: './car-checks.component.scss'
})
export class CarChecksComponent implements OnInit {

  carChecks: CarCheckDto[] = [];
  type: string = "";

  constructor(private translateService: TranslateService,
    private router: Router,
    private tokenService: TokenService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.type = state.type;
      this.carChecks = state.carChecks;
    });
  }

  
  goBack() {
    const type = this.type;
    this.router.navigate(['/supply-requests'], { state: { type } });  
  }

}
