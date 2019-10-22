
import { Injectable } from '@angular/core';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client'
import { BehaviorSubject } from 'rxjs';


//const WEBSOCKET_URL: string = 'http://localhost:8080/endpoint/'
const WEBSOCKET_URL: string = 'http://vorto-normalizer-dev.eu-central-1.elasticbeanstalk.com:8080/endpoint/'



export interface Message {
  content: string;
}


export function SocketFactory() {
  return new SockJS(WEBSOCKET_URL);
}

@Injectable()
export class WebsocketService {
  private _messages = new BehaviorSubject<String[]>([]);
  readonly messages = this._messages.asObservable()

  private stompClient = null;


  constructor() {

  }




  public connect() {
    const socket = SocketFactory;
    this.stompClient = Stomp.Stomp.over(socket)
    this.stompClient.reconnect_delay = 5000;
    const _this = this
    let result: Array<String> = []

    this.stompClient.connect({}, function (frame) {
      console.log('Connected to websocket. Now subscribing: ' + frame);

      _this.stompClient.send('/middleware/endpoint/subscribe');

      _this.stompClient.subscribe('/topic/device/', function (response) {
        result.push(JSON.parse(response.body));
        // Push a new copy of our message list to all Subscribers.
        _this._messages.next(result)
      });
    }
    );

  }

  reconnect = function() {
    console.log(':zooo')
      this.connect();
  };

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected!');
  }

}
