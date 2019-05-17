import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login/login.component';
// import dei component di behavior
import { BehaviorInsertComponent} from './components/behavior/behavior-insert/behavior-insert.component';
import { BehaviorListComponent} from './components/behavior/behavior-list/behavior-list.component';
import { BehaviorUpdateComponent} from './components/behavior/behavior-update/behavior-update.component';
// import dei component di linkTK
import { LinkTKInsertComponent} from './components/linkTK/link-tk-insert/link-tk-insert.component';
import { LinkTkListComponent } from './components/linkTK/link-tk-list/link-tk-list/link-tk-list.component';
// import dei component di keyword
import {KeywordInsertComponent} from './components/keyword/keyword-insert/keyword-insert.component';
import {KeywordListComponent} from './components/keyword/keyword-list/keyword-list.component';
import {KeywordUpdateComponent} from './components/keyword/keyword-update/keyword-update.component';
// import dei component di code
import { CodeInsertComponent} from './components/code/code-insert/code-insert.component';
import { CodeListComponent} from './components/code/code-list/code-list.component';
import { CodeUpdateComponent} from './components/code/code-update/code-update.component';
//import dei componet di Hardware
import { HardwareInsertComponent} from './components/hardware/hardware-insert/hardware-insert.component';
import { HardwareListComponent} from './components/hardware/hardware-list/hardware-list.component';
import { HardwareUpdateComponent} from './components/hardware/hardware-update/hardware-update.component';
//import dei component relativi alle homepage degli admin e degli utenti
import { HomeAdminComponent} from './components/home/home-admin/home-admin.component';
import { HomeBoComponent } from './components/home/home-bo/home-bo.component';
//import dei component di label
import { LabelInsertComponent} from './components/label/label-insert/label-insert/label-insert.component';
import { LabelListComponent} from './components/label/label-list/label-list.component';
import { LabelUpdateComponent} from './components/label/label-update/label-update.component';
// import dei component dei vari menu
import { MenuAdminComponent} from './components/menu/menu-admin/menu-admin.component';
import { MenuBoComponent} from './components/menu/menu-bo/menu-bo.component';
//import dei component di thing
import { ThingInsertComponent} from './components/thing/thing-insert/thing-insert.component';
import { ThingListComponent} from './components/thing/thing-list/thing-list.component';
import { ThingUpdateComponent} from './components/thing/thing-update/thing-update.component';
//import dei componet di user
import { UserInsertComponent} from './components/user/user-insert/user-insert.component';
import { UserShowComponent} from './components/user/user-show/user-show.component';
import { UserUpdateComponent} from './components/user/user-update/user-update.component';
//
import { NavbarComponent} from './components/navbar/navbar.component';

const routes: Routes = [
  {path: 'navbar', component: NavbarComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  //behavior
  {path: 'insertBehavior', component: BehaviorInsertComponent},
  {path: 'listBehavior', component: BehaviorListComponent},
  {path: 'updateBehavior', component: BehaviorUpdateComponent},
  //Code
  {path: 'insertCode', component: CodeInsertComponent},
  {path: 'listCode', component: CodeListComponent},
  {path: 'updateCode', component: CodeUpdateComponent},
  //keyword
  {path: 'insertKeyword', component: KeywordInsertComponent},
  {path: 'listKeyword', component: KeywordListComponent},
  {path: 'updateKeyword',component:KeywordUpdateComponent},
  // link
  {path: 'insertLinkTK', component: LinkTKInsertComponent},
  {path: 'listLinkTK', component: LinkTkListComponent},
  //hardware
  {path: 'insertHardware', component: HardwareInsertComponent},
  {path: 'listHardware', component: HardwareListComponent},
  {path: 'updateHardware', component: HardwareUpdateComponent},
  //label
  {path: 'insertLabel', component: LabelInsertComponent},
  {path: 'listLabel', component: LabelListComponent},
  {path: 'updateLabel', component: LabelUpdateComponent},
  //thing
  {path: 'insertThing', component: ThingInsertComponent},
  {path: 'listThing', component: ThingListComponent},
  {path: 'updateThing', component: ThingUpdateComponent},
  //user
  {path: 'insertUser', component: UserInsertComponent},
  {path: 'listUser', component: UserShowComponent},
  {path: 'updateUser', component: UserUpdateComponent},
  //home
  {path: 'homeAdmin', component: HomeAdminComponent},
  {path: 'homeBo', component: HomeBoComponent},
  //menu
  {path: 'menuAdmin', component: MenuAdminComponent},
  {path: 'menuBo', component: MenuBoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
