import React, { Component } from "react";
import { NavLink } from "react-router-dom";

import logo from "../../assets/img/vorto_logo.png";

import dashboardRoutes from "../../routes/dashboard.jsx";

class Sidebar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      width: window.innerWidth
    };
  }
  activeRoute(routeName) {
    return this.props.location.pathname.indexOf(routeName) > -1 ? "active" : "";
  }
  updateDimensions() {
    this.setState({ width: window.innerWidth });
  }
  componentDidMount() {
    this.updateDimensions();
    window.addEventListener("resize", this.updateDimensions.bind(this));
  }
  render() {
    const routes = dashboardRoutes.map((prop, key) => {
      if (prop.hidden) {
        return null;
      }

      if (!prop.redirect)
        return (
          <li
            className={
              prop.upgrade
                ? "active active-pro"
                : this.activeRoute(prop.path)
            }
            key={key}
          >
            <NavLink
              to={prop.path}
              className="nav-link"
              activeClassName="active"
            >
              <i className={prop.icon} />
              <p>{prop.name}</p>
            </NavLink>
          </li>
        );
      return null;
    });

    return (
      <div
        id="sidebar"
        className="sidebar"
        data-color="black">
        <div className="logo">
          <a
            href="https://www.eclipse.org/vorto/"
            className="simple-text logo-mini"
          >
            <div className="logo-img">
              <img src={logo} alt="logo_image" />
            </div>
          </a>
          <a
            href="https://www.eclipse.org/vorto/"
            className="simple-text logo-normal"
          >
            Eclipse Vorto
          </a>
        </div>
        <div className="sidebar-wrapper">
          <ul className="nav">
            {routes}
          </ul>
        </div>
      </div>
    );
  }
}

export default Sidebar;
