import { Component, OnInit, SimpleChanges } from '@angular/core';
import { PluginService } from 'src/app/service/plugin/plugin.service';
import { async } from 'q';
import { interval } from 'rxjs';

import { startWith, switchMap } from 'rxjs/operators';
import { Plugin } from 'src/app/model/plugin';

@Component({
  selector: 'app-plugins-view',
  templateUrl: './plugins-view.component.html',
  styleUrls: ['./plugins-view.component.scss'],
  providers: [PluginService]
})
export class PluginsViewComponent implements OnInit {

  constructor(private pluginService: PluginService) { }

  private PLUGIN_UPDATE_INTERVAL = 3000
  public result = ""

  public pluginList: Array<Plugin> = []
  public extendedPluginIds: Array<string> = []
  public toggleIcon = "../../assets/icon/toggle.svg";


  ngOnInit() {
    this.updatePluginStates()
  }



  getPluginData(res) {
    let refreshedPlugins: Array<Plugin> = []
    if (res) {
      res.map(element => {
        if (element.id) {
          let plugin = new Plugin(element.id)
          plugin.name = element.name ? element.name : ""
          plugin.description = element.description ? element.description : ""
          plugin.imageUrl = element.imageUrl ? element.imageUrl : ""
          plugin.isActivated = element.isActivated ? element.isActivated : false
          plugin.getConfiguration(element.configuration)
          plugin.hidePassword()
          refreshedPlugins.push(plugin)

        }
      }
      );
      if (JSON.stringify(this.pluginList) !== JSON.stringify(refreshedPlugins)) {
        this.pluginList = refreshedPlugins
       }
    }
  }



  getConfiguration(plugin) {
    if (Object.keys(plugin).length !== 0) {
      let result = new Map(Object.entries(plugin.configuration));
      return result
    }
  }



  toggle(id) {
    var found = this.extendedPluginIds.find(function (element) {
      return element === id;
    });
    if (!found) {
      this.extendedPluginIds.push(id)
    } else {
      const index = this.extendedPluginIds.indexOf(id, 0);
      if (index > -1) {
        this.extendedPluginIds.splice(index, 1);
      }
    }
  }

  isExtended(id) {
    var isExtended = this.extendedPluginIds.find(function (element) {
      return element === id;
    });
    return isExtended
  }




  updatePluginStates() {
    interval(this.PLUGIN_UPDATE_INTERVAL).pipe(startWith(0), switchMap(() => this.pluginService.getPlugins()))
      .subscribe(
        async res => {
          this.getPluginData(res)
          console.log("updating plugin list: ", res)
        }, (err) => console.log(err)
      )
  }




}
