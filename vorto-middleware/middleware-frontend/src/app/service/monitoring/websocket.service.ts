
import { Injectable } from '@angular/core';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client'
import { BehaviorSubject, Subscription } from 'rxjs';
import { environment } from 'src/environments/environment';

const WEBSOCKET_URL: string = environment.apiBaseUrl + "/endpoint"

export interface Message {
  content: string;
}

export function SocketFactory() {
  return new SockJS(WEBSOCKET_URL);
}

@Injectable()
export class WebsocketService {

  private messageSubscription: Subscription


  private _correlatedMessages = new BehaviorSubject<any[]>([])
  readonly correlatedMessages = this._correlatedMessages.asObservable()

  private _messagesActivity  = new BehaviorSubject<boolean>(false)
  readonly messagesActivity = this._messagesActivity.asObservable()

  private stompClient = null;


  constructor() {

  }
  public messageActivity(){
    const _this = this
    if(!_this._messagesActivity.value){
      _this._messagesActivity.next(true)
      setTimeout(function(){ 
        _this._messagesActivity.next(false)
      }, 3000);
    }
  }

  public connect() {
    const socket = SocketFactory;
    this.stompClient = Stomp.Stomp.over(socket)
    this.stompClient.reconnectDelay = 5000;
    this.stompClient.heartbeatIncoming = 0 // Typical value 0 - disabled
    this.stompClient.heartbeatOutgoing = 20000 // Typical value 20000 - every 20 seconds
    const _this = this

    this.stompClient.connect({}, function (frame) {
      console.log('Connected to websocket. Now subscribing: ' + frame);

      _this.stompClient.send('/middleware/endpoint/subscribe');

      let corrId = ''
      let group = []

      _this.messageSubscription = _this.stompClient.subscribe('/topic/device/', function (response) {
        _this.messageActivity()
        const msg = JSON.parse(response.body)
        corrId = (corrId.length === 0) ? msg.correlationId : corrId
        if (msg.correlationId === corrId) {
          group.push(msg)
        } else {
          _this._correlatedMessages.value[corrId] = group
          corrId = ''
          group = []
          group.push(msg)
        }
        _this._correlatedMessages.next(_this._correlatedMessages.value)   
      });
    }
    );
  }

  reconnect = function () {
    this.connect();
  };



  public disconnect() {
    const _this = this
    if (_this.stompClient != null) {
      _this.stompClient.disconnect()
      _this.stompClient.unsubscribe()
    }
    console.log('Disconnected!');
  }

  public clearResults() {
    const _this = this
    if (_this.messageSubscription) {
      _this.messageSubscription.unsubscribe()
      _this._correlatedMessages.next([]);
      this.connect()
    }
  }

  public freeze(freezedList) {
    const _this = this
    if (_this.messageSubscription) {
      _this.messageSubscription.unsubscribe()
      _this._correlatedMessages.next(freezedList);
    }
  }


}
