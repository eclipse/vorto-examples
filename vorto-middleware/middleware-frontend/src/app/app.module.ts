import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MappingsViewComponent } from './component/mappings-view/mappings-view.component';
import { MonitoringViewComponent } from './component/monitoring-view/monitoring-view.component';
import { PluginsViewComponent } from './component/plugins-view/plugins-view.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { MonitoringConsole } from './component/monitoring-view/monitoring-console/monitoring-console.component'; 
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    MappingsViewComponent,
    MonitoringViewComponent,
    PluginsViewComponent,
    SidebarComponent,
    MonitoringConsole
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
