import {Injectable, Inject} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class EmployeeServices {

	private REST_SERVICE_URI = 'http://localhost:8080/test.spring.boot.web.employee/';

	private employeeForCreateUpdate = null;

	private http: null;
	
	constructor (@Inject(Http) http: Http) {
		this.http = http;
	}

	private handleError (error: Response | any) {
		let errMsg: string;
	if (error instanceof Response) {
		const body = error.json() || '';
		const err = body.error || JSON.stringify(body);
		errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
	} else {
		errMsg = error.message ? error.message : error.toString();
	}
	console.error(errMsg);
	return Observable.throw(errMsg);
	}

	getAllEmployees(): Observable {
		return this.http.get(this.REST_SERVICE_URI+"getallemployees")
		.map(res=>res.json())
		.catch(this.handleError);
	}

	addEmployee(employee): Observable {
		return this.http.post(this.REST_SERVICE_URI+"addemployee", employee)
		.map(res=>res.json())
		.catch(this.handleError);
	}


	updateEmployee(employee): Observable {
		return this.http.put(this.REST_SERVICE_URI+"updateemployee", employee)
		.map(res=>res.json())
		.catch(this.handleError);
	}

	deleteEmployee(id): Observable {
		return this.http.delete(this.REST_SERVICE_URI+"deleteemployee/"+id)
		.map(res=>res.json())
		.catch(this.handleError);
	}

	setEmployeeForCreateUpdate(employee)
	{
		this.employeeForCreateUpdate = employee;
	}

	getEmployeeForCreateUpdate()
	{
		return this.employeeForCreateUpdate;
	}
}