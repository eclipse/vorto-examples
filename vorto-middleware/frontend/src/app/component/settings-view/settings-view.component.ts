import { Component, OnInit } from '@angular/core';
import { APIService } from 'src/app/service/api/api.service';

@Component({
  selector: 'app-settings-view',
  templateUrl: './settings-view.component.html',
  styleUrls: ['./settings-view.component.scss']
})
export class SettingsViewComponent implements OnInit {

  constructor(private mappingService: APIService) { }


  public mappingList: Array<string> = []
  public extendedMappingIds: Array<string> = []

  public toggleIcon = "../../assets/icon/single-toggle.svg";
  public urlIcon = "../../assets/icon/url.svg";




  ngOnInit() {
    this.updateMappings()

  }

  refreshMappings(res) {
    let refreshedMappings = []
    if (res) {
      res.map(element => {if (element) {
        const id = (element.infoModel.fullQualifiedFileName) ? element.infoModel.fullQualifiedFileName : "no id provided"
        const url = (element.infoModel.id.prettyFormat) ? this.getRepositoryUrl(element.infoModel.id.prettyFormat) : "empty"
        refreshedMappings.push({mappingId : id, url : url})}});
      if (JSON.stringify(this.mappingList) !== JSON.stringify(refreshedMappings)) {
        this.mappingList = refreshedMappings
       }
    }
  }

  
  updateMappings() {
   this.mappingService.mappingsList.subscribe(
        async res => {
          this.refreshMappings(res)
          console.log("refreshing mappings: ", res)
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
  }


  isExtended(id) {
    var isExtended = this.extendedMappingIds.find((element) => {
      return element === id;
    });
    return isExtended
  }

  getRepositoryUrl(id){
    return "https://vorto.eclipse.org/#/details/"+ id
  }}
