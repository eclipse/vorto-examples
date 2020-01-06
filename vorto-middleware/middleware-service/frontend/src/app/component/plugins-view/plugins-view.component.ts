import { Component, OnInit } from '@angular/core';
import { APIService } from 'src/app/service/api/api.service';
import { Plugin } from 'src/app/model/plugin';

@Component({
  selector: 'app-plugins-view',
  templateUrl: './plugins-view.component.html',
  styleUrls: ['./plugins-view.component.scss']
})
export class PluginsViewComponent implements OnInit {

  constructor(private apiService: APIService) { }

  public result = '';

  public pluginList: Array<Plugin> = [];
  public extendedPluginIds: Array<string> = [];
  public toggleIcon = '../../assets/icon/toggle.svg';


  ngOnInit() {
    this.updatePluginStates();
    this.apiService.getUser();
  }



  getPluginData(res) {
    const refreshedPlugins: Array<Plugin> = [];
    if (res) {
      res.map(element => {
        if (element.id) {
          const plugin = new Plugin(element.id);
          plugin.name = element.name ? element.name : '';
          plugin.description = element.description ? element.description : '';
          plugin.imageUrl = element.imageUrl ? element.imageUrl : '';
          plugin.isActivated = element.started ? element.started : false;
          plugin.getConfiguration(element.configuration);
          plugin.hidePassword();
          refreshedPlugins.push(plugin);

        }
      }
      );
      if (JSON.stringify(this.pluginList) !== JSON.stringify(refreshedPlugins)) {
        this.pluginList = refreshedPlugins;
       }
    }
  }



  getConfiguration(plugin) {
    if (Object.keys(plugin).length !== 0) {
      const result = new Map(Object.entries(plugin.configuration));
      return result;
    }
  }



  toggle(id) {
    const found = this.extendedPluginIds.find(function(element) {
      return element === id;
    });
    if (!found) {
      this.extendedPluginIds.push(id);
    } else {
      const index = this.extendedPluginIds.indexOf(id, 0);
      if (index > -1) {
        this.extendedPluginIds.splice(index, 1);
      }
    }
  }

  isExtended(id) {
    const isExtended = this.extendedPluginIds.find((element) => {
      return element === id;
    });
    return isExtended;
  }


  updatePluginStates() {
    this.apiService.pluginList
      .subscribe(
        async res => {
          console.log('Refreshing list of Plugins: ', res);
          this.getPluginData(res);
        }, (err) => console.log(err)
      );
  }
}
