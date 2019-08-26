import React, { Component } from 'react'
import TreeView from 'react-treeview'

const dataSource = [
  ['Apple', 'Orange'],
  ['Facebook', 'Google'],
  ['Celery', 'Cheeseburger']
]

class Sidebar extends Component {
  constructor (props) {
    super(props)
    this.state = {
      width: window.innerWidth,
      collapsedBookkeeping: dataSource.map(() => false)
    }
  }

  activeRoute (routeName) {
    return this.props.location.pathname.indexOf(routeName) > -1 ? 'active' : ''
  }

  updateDimensions () {
    this.setState({ width: window.innerWidth })
  }

  componentDidMount () {
    this.updateDimensions()
    window.addEventListener('resize', this.updateDimensions.bind(this))
  }

  handleClick (i) {
    const [...collapsedBookkeeping] = this.state.collapsedBookkeeping
    collapsedBookkeeping[i] = !collapsedBookkeeping[i]
    this.setState({ collapsedBookkeeping: collapsedBookkeeping })
  }

  collapseAll () {
    this.setState({
      collapsedBookkeeping: this.state.collapsedBookkeeping.map(() => true)
    })
  }

  render () {
    const collapsedBookkeeping = this.state.collapsedBookkeeping
    return (
      <div
        id='sidebar'
        className='sidebar'
        data-color='black'>
        <div className='logo'>
          <a className='simple-text logo-normal text-center'>
            Asset Tracking
          </a>
        </div>
        <div className='sidebar-wrapper'>
          <TreeView onClick={this.handleClick.bind(this)} nodeLabel={'Elements'}>
            {dataSource.map((node, i) => {
              // Let's make it so that the tree also toggles when we click the
              // label. Controlled components make this effortless.
              const label =
                <span className='node' onClick={this.handleClick.bind(this, i)}>
                  Type {i}
                </span>
              return (
                <TreeView
                  key={i}
                  nodeLabel={label}
                  collapsed={collapsedBookkeeping[i]}
                  onClick={this.handleClick.bind(this, i)}>
                  {node.map(entry => <div className='info' key={entry}>{entry}</div>)}
                </TreeView>
              )
            }, this)}
          </TreeView>
        </div>
      </div>
    )
  }
}

export default Sidebar
