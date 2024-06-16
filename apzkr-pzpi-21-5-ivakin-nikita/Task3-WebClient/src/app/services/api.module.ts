/* tslint:disable */
/* eslint-disable */
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationParams } from './api-configuration';

import { PlatCommanderControllerService } from './services/plat-commander-controller.service';
import { LogisticCommanderControllerService } from './services/logistic-commander-controller.service';
import { CompanyCommanderControllerService } from './services/company-commander-controller.service';
import { BrigadeCommanderControllerService } from './services/brigade-commander-controller.service';
import { BattalionCommanderControllerService } from './services/battalion-commander-controller.service';
import { AdminControllerService } from './services/admin-controller.service';
import { ScanningDeviceControllerService } from './services/scanning-device-controller.service';
import { AuthenticationControllerService } from './services/authentication-controller.service';
import { UserControllerService } from './services/user-controller.service';

/**
 * Module that provides all services and configuration.
 */
@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    PlatCommanderControllerService,
    LogisticCommanderControllerService,
    CompanyCommanderControllerService,
    BrigadeCommanderControllerService,
    BattalionCommanderControllerService,
    AdminControllerService,
    ScanningDeviceControllerService,
    AuthenticationControllerService,
    UserControllerService,
    ApiConfiguration
  ],
})
export class ApiModule {
  static forRoot(params: ApiConfigurationParams): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: params
        }
      ]
    }
  }

  constructor( 
    @Optional() @SkipSelf() parentModule: ApiModule,
    @Optional() http: HttpClient
  ) {
    if (parentModule) {
      throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
    }
    if (!http) {
      throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
      'See also https://github.com/angular/angular/issues/20575');
    }
  }
}
