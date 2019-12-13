import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public vortoLogo = "../../assets/img/vorto-logo.png";
  public gitHubLogo = "../../assets/img/github-social.png";
  public isLoggedIn = false

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }


  public startAuthFlow(){
      this.authService.login()
  }

}
