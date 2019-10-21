import { Component, OnInit, Input } from '@angular/core';
import { WebsocketService } from 'src/app/service/monitoring/websocket.service';
import { Observable } from 'rxjs';



@Component({
  selector: 'app-monitoring-view',
  templateUrl: './monitoring-view.component.html',
  styleUrls: ['./monitoring-view.component.scss']
})
export class MonitoringViewComponent implements OnInit {
  @Input() filterString = ""

  messages: Observable<String[]>;
  public messageList: String[] = []
  public filteredMessages: String[] = []


  constructor(private wsService: WebsocketService) {}

  ngOnInit() {
    this.wsService.messages.subscribe(async messages => {
      this.filteredMessages = this.filterMessages(messages, this.filterString)  
      this.messageList = messages
    })
  }

  refreshList(){
    if(this.messageList && this.filterString){
      if(this.filterString.length >= 2){
        this.filteredMessages = this.filterMessages(this.messageList, this.filterString)
      }else{
        this.filteredMessages = this.messageList
      }
    }
  }

  filterMessages(messages, filterString){
    if(filterString.length !== 0 && filterString.length >= 2 ){
       var searchRes: String[] = []
       for(var message of messages){
         if( message.deviceId.toLowerCase().includes(filterString.toLowerCase())){
          searchRes.push(message)
         }
       }
       return searchRes
    }
    return messages
  }



}
