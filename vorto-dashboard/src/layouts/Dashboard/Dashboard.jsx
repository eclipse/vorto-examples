import React, { Component } from "react";
import { Route, Switch, Redirect } from "react-router-dom";

import Header from "../../components/Header/Header";
import Sidebar from "../../components/Sidebar/Sidebar";

import dashboardRoutes from "../../routes/dashboard.jsx";

class Dashboard extends Component {
  componentDidUpdate(e) {
    if (
      window.innerWidth < 993 &&
      e.history.location.pathname !== e.location.pathname &&
      document.documentElement.className.indexOf("nav-open") !== -1
    ) {
      document.documentElement.classList.toggle("nav-open");
    }
    if (e.history.action === "PUSH") {
      document.documentElement.scrollTop = 0;
      document.scrollingElement.scrollTop = 0;
      this.refs.mainPanel.scrollTop = 0;
    }
  }
  render() {
    const routes = dashboardRoutes.map((prop, key) => {
      if (prop.redirect) {
        return (<Redirect from={prop.path} to={prop.to} key={key} />);
      }
      return (<Route path={prop.path} component={prop.component} key={key} />);
    })

    return (
      <div className="wrapper">
        <Sidebar {...this.props} />
        <div id="main-panel" className="main-panel" ref="mainPanel">
          <Header {...this.props} />
          <Switch>
            {routes}
          </Switch>
        </div>
      </div>
    );
  }
}

export default Dashboard;
