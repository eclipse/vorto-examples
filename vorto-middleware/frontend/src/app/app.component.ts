import { Component, OnInit } from '@angular/core';
import { WebsocketService } from './service/monitoring/websocket.service';
import { Observable } from 'rxjs';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [WebsocketService]
})
export class AppComponent{

  title = 'vorto-middleware-ui';

}
