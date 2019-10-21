import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'monitoring-console',
  templateUrl: './monitoring-console.component.html',
  styleUrls: ['./monitoring-console.component.scss']
})
export class MonitoringConsole implements OnInit {

  constructor() { }

  ngOnInit() {

  }


  @Input('messages')
  public messages

  addZero(i) {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }
  getMessageTitle(message){
    let timestamp = new Date(message.timestamp)
    let severity = (message.severity) ? message.severity : "INFO"
    let deviceId = (message.deviceId) ? message.deviceId.substring(message.deviceId.indexOf(":") + 1, message.deviceId.length) : "no-deviceId"

    return this.addZero(timestamp.getHours()) + ":" + 
           this.addZero(timestamp.getMinutes()) + ":" +  
           this.addZero(timestamp.getSeconds()) +" "  +
            "[" + severity + "] "+ deviceId +": "
  }
  getMessageString(message){
    return JSON.stringify(message)
  
  }}

