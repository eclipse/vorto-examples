import { Component, OnInit } from '@angular/core';
import { APIService, LoginState, PollingState } from 'src/app/service/api/api.service';
import { async } from '@angular/core/testing';

@Component({
  selector: 'app-mappings-view',
  templateUrl: './mappings-view.component.html',
  styleUrls: ['./mappings-view.component.scss']
})
export class MappingsViewComponent implements OnInit {

  constructor(private apiService: APIService) { }

  public mappingList = []
  public installedMappingList = []
  public discoveredMappingList = []
  public checked

  public toggleIcon = "../../assets/icon/single-toggle.svg";
  public urlIcon = "../../assets/icon/url.svg";
  public vortoLogo = "../../assets/img/vorto-logo.png";
  public isLoggedIn

  public mappingPollingState

  public pollingStateTypes = PollingState

  ngOnInit() {

    this.apiService.loginState.subscribe(
      async res => {
        this.isLoggedIn = (res === LoginState.AUTHORIZED) ? true : false
      }, (err) => console.log(err)
    )


    this.apiService.mappingPollingState.subscribe(
      async res => {
        this.mappingPollingState = res
      }, (err) => console.log(err)
    )


    this.apiService.installedMappingsList.subscribe(
      async res => {
        console.log("Refreshing list of installed Mappings: ", res)
        this.addToMappingList(res, this.installedMappingList)
        this.combineMappingLists()
      }, (err) => console.log(err)
    )

    this.apiService.discoveredMappingsList.subscribe(
      async res => {
        this.addToMappingList(res, this.discoveredMappingList)
        this.combineMappingLists()
        console.log("Refreshing list of other discovered Mappings: ", res)
      }, (err) => console.log(err)
    )

    this.apiService.lastResolvedModelId.subscribe(
      async res => {
        console.log("this is now resolved", res)
        this.mappingList.forEach((mapping, index) => {
         
           if (mapping.modelId === res){
             this.mappingList[index].checked = true 
           }
        });
      }, (err) => console.log(err)
    )
  }



  addToMappingList(mappingListToAdd, targetMappingList) {


    mappingListToAdd.map(element => {

      const modelId = (element.modelId && element.modelId.prettyFormat) ? element.modelId.prettyFormat : "No id provided"

      const mapping = {
        modelId: modelId,
        displayName: (element.modelId && element.modelId.name) ? element.modelId.name : "No name provided",
        namespace: (element.modelId && element.modelId.namespace) ? element.modelId.namespace : "No namespace provided",
        description: (element.description) ? element.description : "No description provided",
        version: (element.modelId && element.modelId.version) ? element.modelId.version : "No version provided",
        isInstalled: (element.installed) ? element.installed : false,
        unresolved: element.unresolved,
        checked: false,
        url: this.getRepositoryUrl(modelId)
      }
      targetMappingList.push(mapping)
    })
  }

  combineMappingLists() {
    this.installedMappingList.map(mapping => {
      if (!this.isMappingIn(mapping)) {
        this.mappingList.push(mapping)
      } 
    })



    this.discoveredMappingList.map(mapping => {

      if (!this.isMappingIn(mapping) && !this.isMappingInstalled(mapping)) {
        this.mappingList.push(mapping)
      }
      //updated if unresolved state has changed
      if (this.hasUnresolvedStateChanged(mapping)) {
        console.log("changing state of", mapping.modelId)
        console.log(mapping)
        this.updateMappingInList(mapping)
      }
    })

    var sortedMappings: { installed: boolean }[] = this.mappingList.sort((x,y) => {
      return (x.isInstalled === y.isInstalled)? 0 : x.isInstalled? -1 : 1;
  });


  console.log("full list before sort", this.mappingList)

  this.mappingList = sortedMappings
  console.log("full list after sort", this.mappingList)

  }

  updateMappingInList(mapping2update) {
    this.mappingList.forEach((mapping) => {
      if (mapping.modelId === mapping2update.modelId) {
        this.removeFromMappingList(mapping.modelId)
        this.mappingList.push(mapping2update)
        // this.mappingList[index] = mapping2update
      }
    })
  }

  isMappingInstalled(mapping){
    return this.installedMappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.description === mapping.description)
  }

  isMappingIn(mapping) {
    return this.mappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.description === mapping.description)
  }

  hasUnresolvedStateChanged(mapping) {
    return this.mappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.unresolved !== mapping.unresolved)
  }


  removeFromMappingList(id) {
    this.mappingList.forEach((mapping, index) => {
      if (mapping.modelId === id) this.mappingList.splice(index, 1);
    });
    this.installedMappingList = []
    this.discoveredMappingList = []
  }


  installMapping(modelId) {
    this.apiService.updateMappingInstallState(modelId, true)
    this.removeFromMappingList(modelId)
  }

  uninstallMapping(modelId) {
    this.apiService.updateMappingInstallState(modelId, false)
    this.removeFromMappingList(modelId)
  }

  getRepositoryUrl(id) {
    return "https://vorto.eclipse.org/#/details/" + id
  }
}
