import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.scss']
})
export class MessageListComponent implements OnInit {

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
    let timestamp = new Date()   
    let severity = (message.severity) ? message.severity : "INFO"
    let deviceId = (message.deviceId) ? message.deviceId.substring(message.deviceId.indexOf(":") + 1, message.deviceId.length) : "no-deviceId"

    return this.addZero(timestamp.getHours()) + ":" + 
           this.addZero(timestamp.getMinutes()) + ":" +  
           this.addZero(timestamp.getSeconds()) +" "  +
            "[" + severity + "] "+ deviceId +": "
  }
  getMessageString(message){
    // let text = (message.text) ? message.text : ""
    // let changes = (text.substring(text.indexOf("/features") + 10, text.length))
    // let changesWithoutConfig = changes.substring(0, changes.indexOf(", \"configuration"))

    return JSON.stringify(message)
  
  }}

