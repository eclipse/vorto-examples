import React, { Component } from "react";

import LoginCard from "components/LoginCard/LoginCard"
import UserCard from "components/UserCard/UserCard"

class Account extends Component {
  render() {
    const isLoggedIn = this.props.isLoggedIn;
    if (isLoggedIn) {
      return <LoginCard />;
    }
    return <UserCard />;
  }
}

export default Account;
