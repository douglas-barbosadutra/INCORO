import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule,ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './services/user.service';
import { ThingListComponent } from './components/thing/thing-list/thing-list.component';
import { UserInsertComponent } from './components/user/user-insert/user-insert.component';
import { UserUpdateComponent } from './components/user/user-update/user-update.component';
import { UserShowComponent } from './components/user/user-show/user-show.component';
import { UserDeleteComponent } from './components/user/user-delete/user-delete.component';
import { HardwareInsertComponent } from './components/hardware/hardware-insert/hardware-insert.component';
import { HardwareUpdateComponent } from './components/hardware/hardware-update/hardware-update.component';
import { HardwareListComponent } from './components/hardware/hardware-list/hardware-list.component';
import { ThingService } from './services/thing.service';
import { HardwareService } from './services/hardware.service';
import { LoginComponent } from './components/login/login/login.component';
import { LoginService } from './services/login.service';
import { LabelInsertComponent } from './components/label/label-insert/label-insert/label-insert.component';
import { LabelUpdateComponent } from './components/label/label-update/label-update.component';
import { LabelDeleteComponent } from './components/label/label-delete/label-delete.component';
import { LabelListComponent } from './components/label/label-list/label-list.component';
import { ThingUpdateComponent } from './components/thing/thing-update/thing-update.component';
import { ThingDeleteComponent } from './components/thing/thing-delete/thing-delete.component';
import { ThingInsertComponent } from './components/thing/thing-insert/thing-insert.component';
import { BehaviorInsertComponent } from './components/behavior/behavior-insert/behavior-insert.component';
import { BehaviorUpdateComponent } from './components/behavior/behavior-update/behavior-update.component';
import { BehaviorListComponent } from './components/behavior/behavior-list/behavior-list.component';
import { CodeInsertComponent } from './components/code/code-insert/code-insert.component';
import { CodeUpdateComponent } from './components/code/code-update/code-update.component';
import { CodeListComponent } from './components/code/code-list/code-list.component';
import { HomeBoComponent } from './components/home/home-bo/home-bo.component';
import { HomeAdminComponent } from './components/home/home-admin/home-admin.component';
import { MenuBoComponent } from './components/menu/menu-bo/menu-bo.component';
import { MenuAdminComponent } from './components/menu/menu-admin/menu-admin.component';


@NgModule({
  declarations: [
    AppComponent,
    UserInsertComponent,
    UserUpdateComponent,
    UserShowComponent,
    UserDeleteComponent,
    HardwareInsertComponent,
    HardwareUpdateComponent,
    HardwareListComponent,
    ThingListComponent,
    LoginComponent,
    LabelInsertComponent,
    LabelUpdateComponent,
    LabelDeleteComponent,
    LabelListComponent,
    ThingUpdateComponent,
    ThingDeleteComponent,
    ThingInsertComponent,
    BehaviorInsertComponent,
    BehaviorUpdateComponent,
    BehaviorListComponent,
    CodeInsertComponent,
    CodeUpdateComponent,
    CodeListComponent,
    HomeBoComponent,
    HomeAdminComponent,
    MenuBoComponent,
    MenuAdminComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService, ThingService, HardwareService, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
