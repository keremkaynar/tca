import {Component, OnInit, Inject} from "@angular/core";
import {Router} from "@angular/router";
import {EmployeeServices} from "appjs/employee_services";

@Component({
	templateUrl: 'components/templates/show_employees_component.html',
	providers: [EmployeeServices]
})

export class ShowEmployeesComponent implements OnInit {
	
	public employees: [];

	public currentEmployee: null;

	private employeeServices: null;

	private router: null;

   	constructor(@Inject(EmployeeServices) employeeServices: EmployeeServices, @Inject(Router) router: Router) {
   		this.employeeServices = employeeServices;
   		this.router = router;
   	}

	getAllEmployees() {
		console.log("Get all employees is called...");
		this.employeeServices.getAllEmployees().subscribe(employees => {
			console.log("Get all employees: "+employees);
			this.employees = employees;
		}, error => console.error(error));
	}
	
	createEmployee() {
		this.employeeServices.setEmployeeForCreateUpdate(null);
		this.router.navigate(["/createupdateemployee"]);
	}
	
	updateEmployee() {
		if(this.currentEmployee!=null)
		{
			this.employeeServices.setEmployeeForCreateUpdate(currentEmployee);
		}
		this.router.navigate(["/createupdateemployee"]);
	}
	
	deleteEmployee() {
		if(this.currentEmployee!=null)
		{
			this.employeeServices.deleteEmployee(this.currentEmployee.id).subscribe(() => {
				this.employees = this.employees
					.filter(function(el) {
						return el.id != this.currentEmployee.id;
					});
			});
		}
	}
	
	ngOnInit() {
		this.getAllEmployees();
	}
}