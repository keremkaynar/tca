import {Component, Inject} from "@angular/core";
import {Router} from "@angular/router";
import {EmployeeServices} from "appjs/employee_services";

@Component({
	templateUrl: 'components/templates/create_update_employee_component.html',
	providers: [EmployeeServices]
})

export class CreateUpdateEmployeeComponent {
	
	private update = true;

	public currentEmployee: null;

	public createdCertificate = {name:"", type:""};

	public currentCertificate: null;
	
	private employeeServices: null;

	private router: null;
	
	constructor(@Inject(EmployeeServices) employeeServices: EmployeeServices, @Inject(Router) router: Router) {
		this.employeeServices = employeeServices;
   		this.router = router;
		this.currentEmployee = this.employeeServices.getEmployeeForCreateUpdate();
		if(this.currentEmployee==null)
		{
			this.update = false;
			this.currentEmployee = {firstName:"", lastName:"", mail:"", address:"", certificates:[]};
		}
	}
	
	addCertificate() {
		if (this.createdCertificate.name != "") {
			if (this.createdCertificate.type != "") {
				this.currentEmployee.certificates
						.push(this.createdCertificate);
			}
		}
	}

	deleteCertificate() {
		if (this.currentCertificate) {
			this.currentEmployee.certificates = this.currentEmployee.certificates
					.filter(function(el) {
						return el.id != this.currentCertificate.id;
					});
		}
	}

	saveEmployee() {
		if (this.update == true) {
			this.employeeServices
			.updateEmployee(
					this.currentEmployee).subscribe(() => {this.router.navigate(["/showemployees"]);});
		}
		else
		{
			this.employeeServices
			.addEmployee(
					this.currentEmployee).subscribe(() => {this.router.navigate(["/showemployees"]);});
		}
	}
}