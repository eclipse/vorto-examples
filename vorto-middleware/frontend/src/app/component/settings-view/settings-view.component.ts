import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { APIService } from 'src/app/service/api/api.service';
import { startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-settings-view',
  templateUrl: './settings-view.component.html',
  styleUrls: ['./settings-view.component.scss'],
  providers: [APIService]
})
export class SettingsViewComponent implements OnInit {

  constructor(private mappingService: APIService) { }

  private PLUGIN_UPDATE_INTERVAL = 5000

  public mappingList: Array<string> = []
  public extendedMappingIds: Array<string> = []

  public toggleIcon = "../../assets/icon/single-toggle.svg";



  ngOnInit() {
    this.updateMappings()

  }

  refreshMappings(res) {
    let refreshedMappings = []
    if (res) {
      res.map(element => {if (element) {
        const id = (element.infoModel.fullQualifiedFileName) ? element.infoModel.fullQualifiedFileName : "no id provided"
        refreshedMappings.push({mappingId : id, content : element})}});
      if (JSON.stringify(this.mappingList) !== JSON.stringify(refreshedMappings)) {
        this.mappingList = refreshedMappings
       }
    }
  }

  
  updateMappings() {
    interval(this.PLUGIN_UPDATE_INTERVAL).pipe(startWith(0), switchMap(() => this.mappingService.getMappings()))
      .subscribe(
        async res => {
          this.refreshMappings(res)
          console.log("updating mappings: ", this.mappingList)
        }, (err) => console.log(err)
      )
  }

  toggle(id) {
    var found = this.extendedMappingIds.find(function (element) {
      return element === id;
    });
    if (!found) {
      this.extendedMappingIds.push(id)
    } else {
      const index = this.extendedMappingIds.indexOf(id, 0);
      if (index > -1) {
        this.extendedMappingIds.splice(index, 1);
      }
    }
    console.log(this.extendedMappingIds)
  }


  isExtended(id) {
    var isExtended = this.extendedMappingIds.find((element) => {
      return element === id;
    });
    return isExtended
  }

}


