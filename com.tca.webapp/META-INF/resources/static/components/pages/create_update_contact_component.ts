import {Component, Inject} from "@angular/core";
import {Router} from "@angular/router";
import {EmployeeServices} from "appjs/employee_services";

@Component({
	templateUrl: 'components/templates/create_update_employee_component.html',
	providers: [EmployeeServices]
})

export class CreateUpdateEmployeeComponent {
	
	public update: true;
	
	public currentContact: null;

	private contactServices: null;

	private router: null;
	
	constructor(@Inject(ContactServices) contactServices: ContactServices, @Inject(Router) router: Router) {
		this.contactServices = contactServices;
   		this.router = router;
		this.currentContact = this.contactServices.getContactForCreateUpdate();
		if(this.currentEmployee==null)
		{
			this.update = false;
			this.currentContact = {firstName:"", lastName:"", mailAddress:""};
		}
	}

	updateContact() {
		if(this.currentContact!=null)
		{
			this.contactServices.updateContact(currentContact).subscribe(contact => {
				this.router.navigate(["/showcontacts"]);
			});
		}
	}
	
	deleteContact() {
		if(this.currentContact!=null)
		{
			this.contactServices.deleteContact(this.currentContact.id).subscribe(ret => {
				this.router.navigate(["/showcontacts"]);
			});
		}
	}
	
	createContact() {
		if(this.currentContact!=null)
		{
			this.contactServices.addContact(this.currentContact).subscribe(contact => {
				this.router.navigate(["/showcontacts"]);
			});
		}
	}
}