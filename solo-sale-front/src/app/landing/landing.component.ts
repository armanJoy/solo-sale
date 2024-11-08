import { Component, OnInit } from '@angular/core';
import { RestService } from "src/app/shared/rest";
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  apiResponse: any;
  constructor(
    private restService: RestService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // this.restService.landing().subscribe((data) => {
    //   this.apiResponse = data
    // });
  }

  navigateToLogin() {
    this.router.navigate(['/login']);
  }

}
