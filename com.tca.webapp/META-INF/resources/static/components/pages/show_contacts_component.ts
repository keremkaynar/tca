import {Component, OnInit, AfterViewInit, Inject} from "@angular/core";
import {Router} from "@angular/router";
import {EmployeeServices} from "appjs/contact_services";

@Component({
	templateUrl: 'components/templates/show_contacts_component.html',
	providers: [ContactServices]
})

export class ShowContactsComponent implements OnInit {
	
	public teams: [];
	
	public currentTeam: null;
	
	public contacts: [];

	public currentContact: null;

	private contactServices: null;

	private router: null;

   	constructor(@Inject(ContactServices) contactServices: ContactServices, @Inject(Router) router: Router) {
   		this.contactServices = contactServices;
   		this.router = router;
   	}
	
	getAllTeams() {
		console.log("Get all teams is called...");
		this.contactServices.getAllTeams().subscribe(teams => {
			console.log("Get all teams: "+teams);
			this.teams = teams;
			if(this.teams!=null && this.teams.length>0)
			{
				this.currentTeam = this.teams[0];
			}
		}, error => console.error(error));
	}
	
	getAllContactsByTeamName(teamName) {
		console.log("Get all contacts by team name is called...");
		this.contactServices.getAllContactsByTeamName(teamName).subscribe(contacts => {
			console.log("Get all contacts by team name ("+teamName+"):"+contacts);
			this.contacts = contacts;
		}, error => console.error(error));
	}
	
	newContact() {
		this.contactServices.setContactForCreateUpdate(null);
		this.currentContact = null;
		this.router.navigate(["/createupdatecontact"]);
	}
	
	contactClicked(contact)
	{
		this.currentContact=contact;
		this.contactServices.setContactForCreateUpdate(contact);
		this.router.navigate(["/createupdatecontact"]);
	}
	
	ngOnInit() {
		this.getAllTeams();
	}
	
	ngAfterViewInit()
	{
		if(this.currentTeam!=null)
		{
			this.getAllContactsByTeamName(this.currentTeam.name);
		}
	}
}