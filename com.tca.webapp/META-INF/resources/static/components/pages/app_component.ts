import {Component} from "@angular/core";

@Component({
	selector: 'tca-app',
	template: `
	<div class="row">
		<div class="col-md-12">
			<div class="generic-container">
				<router-outlet> </router-outlet>
			</div>
		</div>
	</div>
	`
})
export class AppComponent { 
	
}