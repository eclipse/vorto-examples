import { Component, OnInit } from '@angular/core';
import { WebsocketService } from 'src/app/service/monitoring/websocket.service';
import { Observable } from 'rxjs';

const WEBSOCKET_URL: string = "http://localhost:8080/endpoint/"

@Component({
  selector: 'app-monitoring-view',
  templateUrl: './monitoring-view.component.html',
  styleUrls: ['./monitoring-view.component.scss'],
  providers: [WebsocketService]
})
export class MonitoringViewComponent implements OnInit {

  messages: Observable<String[]>;
  public messageList: String[]

  constructor(private wsService: WebsocketService) {
    this.wsService.connect(WEBSOCKET_URL)
  }


  ngOnInit() {

    this.messages = this.wsService.messages;

    this.messages.subscribe(async res => {
      this.messageList = res
    })
  }



}
