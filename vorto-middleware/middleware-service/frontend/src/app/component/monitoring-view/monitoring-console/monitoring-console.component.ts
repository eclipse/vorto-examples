import { Component, Input } from '@angular/core';
import { APIService } from 'src/app/service/api/api.service';
import { Key } from 'selenium-webdriver';
import { OnInit } from '@angular/core';
import { Observable } from 'rxjs';

const enum Direction {
  OUTBOUND = "OUTBOUND",
  INBOUND = "INBOUND"
}

@Component({
  selector: 'monitoring-console',
  templateUrl: './monitoring-console.component.html',
  styleUrls: ['./monitoring-console.component.scss']
})
export class MonitoringConsole {

  constructor() { }


  @Input('messageGroups')
  public messageGroups


  addZero(i) {
    if (i < 10) {
      i = "0" + i
    }
    return i
  }
  getMessageTitle(message) {
    let timestamp = new Date(message.timestamp)
    let direction = this.getMessageDirection(message)
    let pluginId = this.getPluginId(message)
    let deviceId = (message.deviceId) ? message.deviceId.substring(message.deviceId.indexOf(":") + 1, message.deviceId.length) : "no-deviceId"

    return this.addZero(timestamp.getHours()) + ":" +
      this.addZero(timestamp.getMinutes()) + ":" +
      this.addZero(timestamp.getSeconds()) + " " +
      "[" + direction + "] | " + pluginId + " " + deviceId + ": "
  }

  getMessageDirection(message) {
    let direction: Direction = (message.direction === Direction.INBOUND) ? Direction.INBOUND : Direction.OUTBOUND
    return direction
  }

  getMessageString(message) {
    return (message.text)
  }

  getPluginId(message) {
    return (message.outboundPluginId) ? " Plugin: " + message.outboundPluginId + " | " : " "
  }


  messageGroupCount() {
    return Object.keys(this.messageGroups).length
  }
}

