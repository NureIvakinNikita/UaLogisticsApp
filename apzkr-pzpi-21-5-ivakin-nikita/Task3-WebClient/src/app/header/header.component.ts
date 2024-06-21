import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { TokenService } from '../services/token/token.service';
import { Role } from '../services/models/Role';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  role: string = 'empty';

  constructor(private translateService: TranslateService,
              private router: Router,
              private tokenService: TokenService) {
    const storedLang = localStorage.getItem('appLanguage') || (navigator.language || 'en').split('-')[0];
    this.translateService.setDefaultLang(storedLang);
    this.translateService.use(storedLang);
    try {
      this.role = this.tokenService.getRoleFromToken();
      console.log(this.role);
    } catch (error) {
      console.log('User isn\'t authenticated');
    }
  }

  ngOnInit(): void {
    try {
      this.role = this.tokenService.getRoleFromToken();
    } catch (error) {
      console.log('User isn\'t authenticated');
    }
  }

  public setLanguage(lang: string) {
    this.translateService.use(lang);
    localStorage.setItem('appLanguage', lang); 
  }

  seeYourGroups() {
    const type = "groups";
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/battle-groups'], { state: { type } });
    });
  }

  seeYourGroup() {
    const type = this.getType();
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/battle-groups'], { state: { type } });
    });
  }

  seeBrigadeRequests() {
    const type = this.getType();
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/supply-requests'], { state: { type } });
    });
  }

  logout() {
    this.tokenService.logout();
    this.role = 'empty';
    this.router.navigate(['/'])
  }

  checkToken() {
    return this.tokenService.token === null || this.tokenService.token === "";
  }

  getToken() {
    return this.tokenService.token;
  }

  getType() {
    try {
      this.role = this.tokenService.getRoleFromToken();
    } catch (error) {
      console.log('User isn\'t authenticated');
    }
    switch (this.role) {
      case Role.BRIGADE_COMMANDER:
        return 'Brigade';
      case Role.BATTALION_COMMANDER:
        return 'Battalion';
      case Role.COMPANY_COMMANDER:
        return 'Company';
      case Role.ADMIN:
        return 'Admin';
      case Role.LOGISTIC_COMMANDER: 
        return 'Logistic'
      default:
        return 'Error';
    }
  }
}
