import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PluginsViewComponent } from './component/plugins-view/plugins-view.component';
import { SettingsViewComponent } from './component/settings-view/settings-view.component';
import { MonitoringViewComponent } from './component/monitoring-view/monitoring-view.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'plugins',
    pathMatch: 'full'
  },
  {path: 'plugins', component: PluginsViewComponent},
  {path: 'monitoring', component: MonitoringViewComponent},
  {path: 'settings', component: SettingsViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
