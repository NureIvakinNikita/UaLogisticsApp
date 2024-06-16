import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'uaLogisticWeb';

  constructor(public translateService: TranslateService) {
    const storedLang = localStorage.getItem('appLanguage') || (navigator.language || 'en').split('-')[0];
    this.translateService.setDefaultLang(storedLang);
    this.translateService.use(storedLang);
  };
}
