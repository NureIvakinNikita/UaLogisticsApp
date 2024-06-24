import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './home/home.component';
import { InterceptorService } from './services/intercepters/intercepter.service';
import { BattleGroupsListComponent } from './pages/battle-groups-list/battle-groups-list.component';
import { TokenInterceptor } from './services/intercepters/token-interceptor.service';
import { GroupResourcesComponent } from './pages/group-resources/group-resources.component';
import { CommanderInfoComponent } from './pages/commander-info/commander-info.component';
import { AssignCommanderComponent } from './pages/assign-commander/assign-commander.component';
import { SupplyRequestsComponent } from './pages/supply-requests/supply-requests.component';
import { RequestedResourcesComponent } from './pages/requested-resources/requested-resources.component';
import {MatDialogModule} from '@angular/material/dialog';
import { PostsComponent } from './pages/posts/posts.component';
import { PostComponent } from './pages/post/post.component';
import { DeviceComponent } from './pages/device/device.component';
import { CarPopupComponent } from './pages/popups/car-popup/car-popup.component';
import { CarChecksComponent } from './pages/car-checks/car-checks.component';
import { SendingResourcesComponent } from './pages/popups/sending-resources/sending-resources.component';

export function HttpLoaderFactory(http: HttpClient) {
  console.log(http);
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BattleGroupsListComponent,
    GroupResourcesComponent,
    CommanderInfoComponent,
    AssignCommanderComponent,
    SupplyRequestsComponent,
    RequestedResourcesComponent,
    PostsComponent,
    PostComponent,
    DeviceComponent,
    CarPopupComponent,
    CarChecksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    HttpClient,
    provideAnimationsAsync(),
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
