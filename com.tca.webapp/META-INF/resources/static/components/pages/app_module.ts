import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';
import {AppComponent} from 'appjs/app_component';
import {ShowEmployeesComponent} from 'appjs/show_employees_component';
import {CreateUpdateEmployeeComponent} from 'appjs/create_update_employee_component';

const appRoutes=
	[
	 {path: 'showemployees', component: ShowEmployeesComponent},
	 {path: 'createupdateemployee', component: CreateUpdateEmployeeComponent},
	 {path: '**', component: ShowEmployeesComponent}
	 ];

@NgModule({
	imports: 
		[
		 BrowserModule,
		 FormsModule,
		 HttpModule,
		 RouterModule.forRoot(appRoutes)
		 ],
    declarations: 
		 [
		  AppComponent,
		  ShowEmployeesComponent,
		  CreateUpdateEmployeeComponent,
		  ],
	bootstrap: [AppComponent]
})

export class AppModule { }