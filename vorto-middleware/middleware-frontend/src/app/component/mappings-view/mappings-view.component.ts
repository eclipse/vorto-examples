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

  public toggleIcon = "../../assets/icon/single-toggle.svg";
  public urlIcon = "../../assets/icon/url.svg";
  public vortoLogo = "../../assets/img/vorto-logo.png";


  ngOnInit() {
    this.updateMappings()
  }

  updateMappings() {
    this.mappingService.mappingsList.subscribe(
      async res => {
        this.refreshMappings(res)
        console.log("Refreshing list of Mappings: ", res)
      }, (err) => console.log(err)
    )
  }

  refreshMappings(res) {
    let refreshedMappings = []

    if (res) {
      res.map(element => {
        if (element) {
          const modelId = (element.modelId && element.modelId.prettyFormat) ? element.modelId.prettyFormat : "No id provided"
          const displayName = (element.modelId && element.modelId.name) ? element.modelId.name : "No name provided"
          const namespace = (element.modelId && element.modelId.namespace) ? element.modelId.namespace : "No namespace provided"
          const description = (element.description) ? element.description : "No description provided"
          const version = (element.modelId && element.modelId.version) ? element.modelId.version : "No version provided"
          const url = this.getRepositoryUrl(modelId)
          const isInstalled = (element.installed) ? element.installed : false

          const mappingContent = {
            modelId: modelId,
            displayName: displayName,
            namespace: namespace,
            description: description,
            version: version,
            isInstalled : isInstalled,
            url: url
          }
            refreshedMappings.push(mappingContent) 
        }
      });
      ///if there were any changes after an update
      if (JSON.stringify(this.mappingList) !== JSON.stringify(refreshedMappings)) {
        this.mappingList = refreshedMappings
      }
    }
  }

  installMapping(modelId){
    this.mappingService.updateMappingInstallState(modelId,true)
  }

  uninstallMapping(modelId){
    this.mappingService.updateMappingInstallState(modelId,false)
  }

  getRepositoryUrl(id) {
    return "https://vorto.eclipse.org/#/details/" + id
  }
}
