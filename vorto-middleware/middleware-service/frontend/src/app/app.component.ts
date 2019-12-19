import { Component, OnInit } from '@angular/core';
import { WebsocketService } from './service/monitoring/websocket.service';
import { APIService, LoginState } from './service/api/api.service';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [WebsocketService]
})
export class AppComponent implements OnInit {

  title = 'vorto-middleware';

  constructor(private wsService: WebsocketService, private apiService: APIService) { }



  ngOnInit(): void {
    //connect to websocket
    this.wsService.connect()

    //subscribe to plugin API
     this.apiService.getPlugins().subscribe()
    //subscribe to mapping API
     this.apiService.getInstalledMappings().subscribe() 
    
     this.apiService.loginState.subscribe(
      async res => {
        if(res === LoginState.AUTHORIZED){
          this.apiService.getDiscoveredMappings().subscribe() 
        }
      }, (err) => console.log(err)
    )

    
  }


  ngOnDestroy(): void {
    this.wsService.disconnect()
  }
}
