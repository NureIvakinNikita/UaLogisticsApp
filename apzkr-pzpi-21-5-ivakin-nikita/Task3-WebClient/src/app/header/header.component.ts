import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { TokenService } from '../services/token/token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  role: string = 'empty';

  constructor(private translateService: TranslateService,
              private tokenService: TokenService) {
    const storedLang = localStorage.getItem('appLanguage') || (navigator.language || 'en').split('-')[0];
    this.translateService.setDefaultLang(storedLang);
    this.translateService.use(storedLang);

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
}
