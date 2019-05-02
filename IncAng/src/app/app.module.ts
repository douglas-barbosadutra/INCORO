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
