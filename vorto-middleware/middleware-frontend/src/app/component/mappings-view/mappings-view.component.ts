import { Component, OnInit } from '@angular/core';
import { APIService } from 'src/app/service/api/api.service';

@Component({
  selector: 'app-mappings-view',
  templateUrl: './mappings-view.component.html',
  styleUrls: ['./mappings-view.component.scss']
})
export class MappingsViewComponent implements OnInit {

  constructor(private mappingService: APIService) { }


  public mappingList: Array<string> = []
  public extendedMappingIds: Array<string> = []

  public toggleIcon = "../../assets/icon/single-toggle.svg";
  public urlIcon = "../../assets/icon/url.svg";
  public vortoLogo = "../../assets/img/vorto-logo.png";


  ngOnInit() {
    this.updateMappings()
  }

  refreshMappings(res) {
    let refreshedMappings = []
    if (res) {
      res.map(element => {if (element) {
        const displayName = (element.infoModel.displayName) ? element.infoModel.displayName : "No name provided"
        const namespace = (element.infoModel.id.namespace) ? element.infoModel.id.namespace : "No namespace provided"
        const version = (element.infoModel.id.version) ? element.infoModel.id.version : "No version provided"
        const url = (element.infoModel.id.prettyFormat) ? this.getRepositoryUrl(element.infoModel.id.prettyFormat) : "empty"
        
        refreshedMappings.push(
          {
            displayName : displayName, 
            namespace : namespace,
            version : version,
            url : url
          })}});
      if (JSON.stringify(this.mappingList) !== JSON.stringify(refreshedMappings)) {
        this.mappingList = refreshedMappings
       }
    }
  }
 
  updateMappings() {
   this.mappingService.mappingsList.subscribe(
        async res => {
          this.refreshMappings(res)
          console.log("Refreshing list of Mappings: ", res)
        }, (err) => console.log(err)
      )
  }


  getRepositoryUrl(id){
    return "https://vorto.eclipse.org/#/details/"+ id
  }}
