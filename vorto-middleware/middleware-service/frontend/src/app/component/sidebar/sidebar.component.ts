import { Component, OnInit } from '@angular/core';
import { APIService, LoginState } from 'src/app/service/api/api.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public vortoLogo = '../../assets/img/vorto-logo.png';
  public gitHubLogo = '../../assets/img/github-social.png';
  public gitHubLogoWhite = '../../assets/img/github-social-white.png';
  public isLoggedIn = false;
  public loginState;
  public userName;

  constructor(private apiService: APIService) { }

  ngOnInit() {

    this.apiService.getUser().subscribe();


    this.apiService.authorizedUserId.subscribe(
      async res => {
        if (res) {
          this.userName = res;
          console.log('User Name : ', res);
        }
      }, (err) => console.log(err)
    );

    this.apiService.loginState.subscribe(
      async res => {

        if (res === LoginState.AUTHORIZED) {
          this.isLoggedIn = true;
        } else {
          this.isLoggedIn = false;
        }
      }, (err) => console.log(err)
    );
  }

  public startAuthFlow() {
    this.apiService.login();
  }
  public gitHubLogout() {
    this.apiService.logout();
  }

}
