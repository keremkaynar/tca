import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';
import {AppComponent} from 'appjs/app_component';
import {ShowContactsComponent} from 'appjs/show_contacts_component';
import {CreateUpdateContactComponent} from 'appjs/create_update_contact_component';

const appRoutes=
	[
	 {path: 'showcontacts', component: ShowContactsComponent},
	 {path: 'createupdatecontact', component: CreateUpdateContactComponent},
	 {path: '**', component: ShowContactsComponent}
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
		  ShowContactsComponent,
		  CreateUpdateContactComponent,
		  ],
	bootstrap: [AppComponent]
})

export class AppModule { }