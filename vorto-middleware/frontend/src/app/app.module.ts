import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SettingsViewComponent } from './component/settings-view/settings-view.component';
import { MonitoringViewComponent } from './component/monitoring-view/monitoring-view.component';
import { PluginsViewComponent } from './component/plugins-view/plugins-view.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { MessageListComponent } from './component/monitoring-view/message-list/message-list.component'; 


@NgModule({
  declarations: [
    AppComponent,
    SettingsViewComponent,
    MonitoringViewComponent,
    PluginsViewComponent,
    SidebarComponent,
    MessageListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
