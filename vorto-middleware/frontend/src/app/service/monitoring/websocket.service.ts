
import { Injectable } from '@angular/core';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client'
import { Subject, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';



export interface Message {
  content: string;
}

@Injectable({ providedIn: 'root' })
export class WebsocketService {
   private _messages = new BehaviorSubject<String[]>([]);
   readonly messages = this._messages.asObservable()

  constructor() {
   }


  private stompClient = null;



  public connect(url) {
    const socket = new SockJS(url);
    this.stompClient = Stomp.Stomp.over(socket)
    const _this = this
    let result: Array<String> = []

    this.stompClient.connect({}, function (frame) {
      console.log('Connected to websocket. Now subscribing: ' + frame);

      _this.stompClient.send("/middleware/endpoint/subscribe")

      _this.stompClient.subscribe('/topic/device/', function (response) {
        result.push(JSON.parse(response.body))
        // Push a new copy of our message list to all Subscribers.
        _this._messages.next(result)
      });
    });
  }




  //Todo: to be used
  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
     console.log('Disconnected!');
  }

}
