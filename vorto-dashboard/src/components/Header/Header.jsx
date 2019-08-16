import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap'

import dashboardRoutes from '../../routes/dashboard.jsx'
import Search from './Search'

class Header extends Component {
  constructor (props) {
    super(props)
    this.mobileSidebarToggle = this.mobileSidebarToggle.bind(this)
    this.state = {
      sidebarExists: true
    }
  }

  mobileSidebarToggle (e) {
    if (this.state.sidebarExists === false) {
      this.setState({
        sidebarExists: true
      })
    }
    e.preventDefault()
    document.documentElement.classList.toggle('nav-open')
    var node = document.createElement('div')
    node.id = 'bodyClick'
    node.onclick = function () {
      this.parentElement.removeChild(this)
      document.documentElement.classList.toggle('nav-open')
    }
    document.body.appendChild(node)
  }

  getBrand () {
    var name
    dashboardRoutes.map((prop, key) => {
      if (prop.collapse) {
        prop.views.map((prop, key) => {
          if (prop.path === this.props.location.pathname) {
            name = prop.name
          }
          return null
        })
      } else {
        if (prop.redirect) {
          if (prop.path === this.props.location.pathname) {
            name = prop.name
          }
        } else {
          if (prop.path === this.props.location.pathname) {
            name = prop.name
          }
        }
      }
      return null
    })
    return name
  }

  render () {
    const brand = this.getBrand()

    return (
      <Navbar fluid>
        <Navbar.Header>
          <Navbar.Brand>
            {brand}
          </Navbar.Brand>
          <Nav pullRight>
            <a href='https://github.com/eclipse/vorto-examples/tree/master/vorto-dashboard/docs/AssetTracking.md' target='_blank' className='how-link'>Peek behind the Scenes</a>
            <Search brand={brand} />
            <Navbar.Toggle onClick={this.mobileSidebarToggle} />
          </Nav>
        </Navbar.Header>
      </Navbar >
    )
  }
}

export default Header
