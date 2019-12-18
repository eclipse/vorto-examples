import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PluginsViewComponent } from './component/plugins-view/plugins-view.component';
import { MappingsViewComponent } from './component/mappings-view/mappings-view.component';
import { MonitoringViewComponent } from './component/monitoring-view/monitoring-view.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'plugins',
    pathMatch: 'full',
    runGuardsAndResolvers: 'always'
  },
  { path: 'plugins', component: PluginsViewComponent, runGuardsAndResolvers: 'always' },
  { path: 'monitoring', component: MonitoringViewComponent, runGuardsAndResolvers: 'always' },
  { path: 'mappings', component: MappingsViewComponent, runGuardsAndResolvers: 'always' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
