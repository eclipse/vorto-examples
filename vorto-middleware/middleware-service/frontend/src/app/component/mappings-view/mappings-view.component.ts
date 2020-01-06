import { Component, OnInit } from '@angular/core';
import { APIService, LoginState, PollingState } from 'src/app/service/api/api.service';

@Component({
  selector: 'app-mappings-view',
  templateUrl: './mappings-view.component.html',
  styleUrls: ['./mappings-view.component.scss']
})
export class MappingsViewComponent implements OnInit {

  public mappingList = [];
  public installedMappingList = [];
  public discoveredMappingList = [];
  public checked;
  public toggleIcon = '../../assets/icon/single-toggle.svg';
  public urlIcon = '../../assets/icon/url.svg';
  public vortoLogo = '../../assets/img/vorto-logo.png';
  public isLoggedIn;
  public mappingPollingState;
  public pollingStateTypes = PollingState;

  constructor(private apiService: APIService) { }

  ngOnInit() {

    this.apiService.loginState.subscribe(
      async res => {
        this.isLoggedIn = (res === LoginState.AUTHORIZED) ? true : false;
      }, (err) => console.log(err)
    );


    this.apiService.mappingPollingState.subscribe(
      async res => {
        this.mappingPollingState = res;
      }, (err) => console.log(err)
    );


    this.apiService.installedMappingsList.subscribe(
      async res => {
        this.addToMappingList(res, this.installedMappingList);
        this.combineMappingLists();
      }, (err) => console.log(err)
    );

    this.apiService.discoveredMappingsList.subscribe(
      async res => {
        this.addToMappingList(res, this.discoveredMappingList);
        this.combineMappingLists();
      }, (err) => console.log(err)
    );

    this.apiService.lastResolvedModelId.subscribe(
      async res => {
        console.log('Successfully resolved information model:  ', res);
        this.mappingList.forEach((mapping, index) => {

          if (mapping.modelId === res) {
            this.mappingList[index].checked = true;
          }
        });
      }, (err) => console.log(err)
    );


    // subscribe to successful install/uninstall
    this.apiService.installUpdateResult.subscribe(
      async res => {
        if (res.status === 200) {
          this.installedMappingList = [];
          this.discoveredMappingList = [];

          this.apiService.getInstalledMappings().subscribe();
          this.apiService.getDiscoveredMappings().subscribe();

          this.removeFromCombinedMappingList(res.updatedId);
          this.combineMappingLists();

        }
      }, (err) => console.log(err)
    );
  }


  addToMappingList(mappingListToAdd, targetMappingList) {

    mappingListToAdd.map(element => {

      const modelId = (element.modelId && element.modelId.prettyFormat) ? element.modelId.prettyFormat : 'No id provided';

      const mapping = {
        modelId,
        displayName: (element.modelId && element.modelId.name) ? element.modelId.name : 'No name provided',
        namespace: (element.modelId && element.modelId.namespace) ? element.modelId.namespace : 'No namespace provided',
        description: (element.description) ? element.description : 'No description provided',
        version: (element.modelId && element.modelId.version) ? element.modelId.version : 'No version provided',
        isInstalled: (element.installed) ? element.installed : false,
        unresolved: element.unresolved,
        checked: false,
        url: this.getRepositoryUrl(modelId)
      };
      targetMappingList.push(mapping);
    });
  }

  combineMappingLists() {

    // go through all locally installed (github)
    this.installedMappingList.map(mapping => {
      if (!this.isMappingInCombinedList(mapping)) {
        this.mappingList.push(mapping);
      }
    });
    // go through all discoverd (github)
    this.discoveredMappingList.map(mapping => {

      if (!this.isMappingInCombinedList(mapping)) {
        this.mappingList.push(mapping);
      }
      // updated if unresolved state has changed
      if (this.hasUnresolvedStateChanged(mapping)) {
        console.log('Updating: ', mapping.modelId);
        this.updateMappingInList(mapping);
      }
    });

    const sortedMappings: { installed: boolean }[] = this.mappingList.sort((x, y) => {
      return (x.isInstalled === y.isInstalled) ? 0 : x.isInstalled ? -1 : 1;
  });

    // sort mapping list --> installed mappings first
    this.mappingList = this.mappingList.sort((x, y) => {
      return (x.isInstalled === y.isInstalled) ? 0 : x.isInstalled ? -1 : 1;
    });

    this.mappingList = sortedMappings;

    console.log('List of Mappings: ', this.mappingList);

  }


  // checks
  isMappingInCombinedList(mapping) {
    return this.mappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.description === mapping.description);
  }

  isMappingInstalled(mapping) {
    return this.installedMappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.description === mapping.description);
  }

  hasUnresolvedStateChanged(mapping) {
    return this.mappingList.some(x =>
      x.modelId === mapping.modelId &&
      x.unresolved !== mapping.unresolved);
  }


  // actions
  updateMappingInList(mapping2update) {
    this.mappingList.forEach((mapping, index) => {
      if (mapping.modelId === mapping2update.modelId) {
        this.mappingList[index] = mapping2update;

      }
    });
  }

  // remove from mappingList as it will be added again from other list
  removeFromCombinedMappingList(modelId) {
    this.mappingList.forEach((mapping, index) => {
      if (mapping.modelId === modelId) { this.mappingList.splice(index, 1); }
    });
    this.installedMappingList = [];
    this.discoveredMappingList = [];
  }


  installMapping(modelId) {
    this.apiService.updateMappingInstallState(modelId, true);
  }

  uninstallMapping(modelId) {
    this.apiService.updateMappingInstallState(modelId, false);
  }

  getRepositoryUrl(id) {
    return 'https://vorto.eclipse.org/#/details/' + id;
  }

  createMapping(id) {
    const url = 'https://vorto.eclipse.org/#/payloadmapping/' + id;
    window.open(url, '_blank');
  }
}
