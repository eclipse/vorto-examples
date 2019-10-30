import { Component, OnInit } from '@angular/core';
import { WebsocketService } from './service/monitoring/websocket.service';
import { startWith, switchMap } from 'rxjs/operators';
import { APIService } from './service/api/api.service';
import { interval } from 'rxjs';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [WebsocketService]
})
export class AppComponent implements OnInit {

  title = 'vorto-middleware';
  private PLUGIN_UPDATE_INTERVAL = 5000

  constructor(private wsService: WebsocketService, private pluginService: APIService) { }


  public messageList: String[]


  ngOnInit(): void {
    //connect to websocket
    this.wsService.connect()
    //subscribe to plugin API
    interval(this.PLUGIN_UPDATE_INTERVAL).pipe(startWith(0), switchMap(() => this.pluginService.getPlugins()))
    .subscribe(
      async res => {
      }, (err) => console.log(err)
    )
    //subscribe to plugin API
    interval(this.PLUGIN_UPDATE_INTERVAL).pipe(startWith(0), switchMap(() => this.pluginService.getMappings()))
    .subscribe(
      async res => {
      }, (err) => console.log(err)
    )
  }


  ngOnDestroy(): void {
    this.wsService.disconnect()
  }
}
