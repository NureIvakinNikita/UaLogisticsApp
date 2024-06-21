import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { BattleGroupsListComponent } from './pages/battle-groups-list/battle-groups-list.component';
import { AuthGuard } from './services/auth.guard';
import { GroupResourcesComponent } from './pages/group-resources/group-resources.component';
import { CommanderInfoComponent } from './pages/commander-info/commander-info.component';
import { AssignCommanderComponent } from './pages/assign-commander/assign-commander.component';
import { SupplyRequestsComponent } from './pages/supply-requests/supply-requests.component';
import { RequestedResourcesComponent } from './pages/requested-resources/requested-resources.component';
import { PostsComponent } from './pages/posts/posts.component';
import { PostComponent } from './pages/post/post.component';

const routes: Routes = [
  {path: 'header', component: HeaderComponent},
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'battle-groups', component:BattleGroupsListComponent , canActivate: [AuthGuard] },
  {path: 'group-resources', component:GroupResourcesComponent , canActivate: [AuthGuard] },
  {path: 'commander-info', component:CommanderInfoComponent , canActivate: [AuthGuard] },
  {path: 'assing-commander', component:AssignCommanderComponent , canActivate: [AuthGuard] },
  {path: 'supply-requests', component:SupplyRequestsComponent , canActivate: [AuthGuard] },
  {path: 'requested-resources', component:RequestedResourcesComponent , canActivate: [AuthGuard] },
  {path: 'posts', component:PostsComponent , canActivate: [AuthGuard] },
  {path: 'create/post', component: PostComponent, canActivate: [AuthGuard] },
  {path: 'assign/device', component: PostComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
