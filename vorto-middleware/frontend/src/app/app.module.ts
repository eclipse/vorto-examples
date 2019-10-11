import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SettingsViewComponent } from './component/settings-view/settings-view.component';
import { MonitoringViewComponent } from './component/monitoring-view/monitoring-view.component';
import { PluginsViewComponent } from './component/plugins-view/plugins-view.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    SettingsViewComponent,
    MonitoringViewComponent,
    PluginsViewComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
