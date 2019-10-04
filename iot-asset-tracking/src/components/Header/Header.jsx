import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap'

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

  render () {
    return (
      <Navbar fluid>
        <Navbar.Header>
          <Nav pullRight>
            <Navbar.Toggle onClick={this.mobileSidebarToggle} />
            <div className='navbar-header__button-row'>
                <button className='header-button'>Run Simulation</button>
                {/* <button className='header-button'>color Toggle</button> */}

            </div>

          </Nav>
        </Navbar.Header>
      </Navbar >
    )
  }
}

export default Header
