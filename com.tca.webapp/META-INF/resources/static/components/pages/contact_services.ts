import {Injectable, Inject} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ContactServices {

	private REST_SERVICE_URI = 'http://localhost:8080/tca/';

	private contactForCreateUpdate = null;

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

	getAllTeams(): Observable {
		return this.http.get(this.REST_SERVICE_URI+"getallteams")
		.map(res=>res.json())
		.catch(this.handleError);
	}
	
	getAllContactsByTeamName(teamName): Observable {
		return this.http.get(this.REST_SERVICE_URI+teamName+"/getallcontacts")
		.map(res=>res.json())
		.catch(this.handleError);
	}

	addContact(contact): Observable {
		return this.http.post(this.REST_SERVICE_URI+"addcontact", contact)
		.map(res=>res.json())
		.catch(this.handleError);
	}

	updateContact(contact): Observable {
		return this.http.put(this.REST_SERVICE_URI+"updatecontact", contact)
		.map(res=>res.json())
		.catch(this.handleError);
	}

	deleteContact(id): Observable {
		return this.http.delete(this.REST_SERVICE_URI+"deletecontact/"+id)
		.catch(this.handleError);
	}

	setContactForCreateUpdate(contact)
	{
		this.contactForCreateUpdate = contact;
	}

	getContactForCreateUpdate()
	{
		return this.contactForCreateUpdate;
	}
}