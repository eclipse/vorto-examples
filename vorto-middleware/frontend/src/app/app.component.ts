import { Component, OnInit } from '@angular/core';
import { WebsocketService } from './service/monitoring/websocket.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [WebsocketService]
})
export class AppComponent implements OnInit {

  title = 'vorto-middleware';


  constructor(private wsService: WebsocketService) { }


  public messageList: String[]


  ngOnInit(): void {
    this.wsService.connect()
    this.wsService.messages.subscribe(async messages => {
      this.messageList = messages
    })
  }

  ngOnDestroy(): void {
    this.wsService.disconnect()
  }
}
