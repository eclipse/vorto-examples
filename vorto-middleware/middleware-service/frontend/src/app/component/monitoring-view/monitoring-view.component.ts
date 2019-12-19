import { Component, OnInit, Input } from '@angular/core';
import { WebsocketService } from 'src/app/service/monitoring/websocket.service';
import { async } from 'q';



@Component({
  selector: 'app-monitoring-view',
  templateUrl: './monitoring-view.component.html',
  styleUrls: ['./monitoring-view.component.scss']
})
export class MonitoringViewComponent implements OnInit {

  @Input() filterString = ""


  public messageGroupList: Array<any> = []
  public filteredGroupList: Array<any> = []

  public freezedList: Array<any> = []

  public outputFreezed: boolean = false

  public messagesActivity: boolean = false

  public freezeIcon = "../../../assets/icon/console/freeze.svg";
  public resumeIcon = "../../../assets/icon/console/resume.svg";
  public deleteIcon = "../../../assets/icon/console/delete.svg";

  constructor(private wsService: WebsocketService) { }

  ngOnInit() {
    this.subscribeMessages()
  }

  subscribeMessages() {
    this.wsService.correlatedMessages.subscribe(async messageGroupList => {
      if (messageGroupList.length > 500) {
        this.clearResults()
      } else {
        this.messageGroupList = messageGroupList
        this.filteredGroupList = this.filterMessages(this.messageGroupList, this.filterString)
      }
    })
    this.wsService.messagesActivity.subscribe(async messagesActivity => {
      this.messagesActivity = messagesActivity
    })
  }


  filterMessages(messageGroupList, filterString) {
    if (filterString.length !== 0 && filterString.length >= 2) {
      var searchRes = {}
      let corrId = ''

      Object.values(messageGroupList).forEach((messageGroup) => {
        Object.values(messageGroup).forEach((message) => {
          if (message.deviceId.toLowerCase().includes(filterString.toLowerCase())) {

            // if correlation id is not already in
            if (message.correlationId !== corrId) {
              searchRes[message.correlationId] = messageGroup
              corrId = message.correlationId
            }
          }
        })
      })

      return searchRes
    }
    return messageGroupList
  }


  refreshList() {
    if (this.messageGroupList && this.filterString) {
      if (this.filterString.length >= 2) {
        this.filteredGroupList = this.filterMessages(this.messageGroupList, this.filterString)
      } else {
        this.filteredGroupList = this.messageGroupList
      }

    }
  }


  clearResults() {
    this.wsService.clearResults()
    this.filteredGroupList = []
    this.messageGroupList = []

  }


  toggleFreeze() {
    this.outputFreezed = this.outputFreezed ? false : true
    if (this.outputFreezed) {
      this.wsService.freeze(this.messageGroupList)
      
    }
  else {
      this.wsService.connect()
    }
  }
}
