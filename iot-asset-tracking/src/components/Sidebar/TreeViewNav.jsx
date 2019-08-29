import React, { Component } from 'react'
import TreeView from 'react-treeview'

import log from 'loglevel'
import Spinner from '../Spinner/Spinner';
import { getTextAfterColon } from '../../util'
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')

class TreeViewNav extends Component {
  state = {
    loading: true
  }

  getEntityChilds (things, references) {
    if (references.length === 0) {
      return []
    }

    const referenceLabels = references.map(ref => ref.thingId)
    const entityList = referenceLabels.map(label => {
      const elem = things.find(thing => thing.thingId === label)
      const elemLabel = getTextAfterColon(elem.thingId)
      const elemReferences = elem.attributes.topology.references

      const topology = {}
      topology[elemLabel] = this.getEntityChilds(things, elemReferences)
      return topology
    }, this)

    return entityList
  }

  buildTopology (things) {
    if (!things) {
      return {}
    }

    const root = things.find(thing => {
      const thingTopo = thing.attributes.topology

      if (!thingTopo) {
        return false
      }

      return thingTopo.definition.includes('org.eclipse.vorto:Topology')
    })

    if (!root) {
      return {}
    }

    log.debug(`Root element...${JSON.stringify(root)}`)

    const rootLabel = getTextAfterColon(root.thingId)
    const rootReferences = root.attributes.topology.references

    const topology = {}
    topology[rootLabel] = this.getEntityChilds(things, rootReferences)

    return topology
  }

  handleClick (i) {
    const [...collapsedBookkeeping] = this.state.collapsedBookkeeping
    collapsedBookkeeping[i] = !collapsedBookkeeping[i]
    this.setState({ collapsedBookkeeping: collapsedBookkeeping })
  }

  getTreeViewStructure (topology) {
    return Object.keys(topology).map((label, index) => {
      if (topology[label].length === 0) {
        return <div className='info' onClick={() => { }} key={index}>{label}</div>
      }

      const labelNode =
        <span className='node' onClick={() => { }}>
          {label}
        </span>

      return (
        <TreeView
          key={index}
          nodeLabel={labelNode}
          onClick={() => {}}>
          {topology[label].map(child => this.getTreeViewStructure(child), this)}
        </TreeView>)
    }, this)
  }

  buildTreeView (topology) {
    const treeViewRootName = Object.keys(topology)[0]
    const treeViewRoot = topology[treeViewRootName]

    if (!treeViewRoot) {
      return (<TreeView />)
    }

    const labelNode =
      <span className='node' onClick={() => { }}>
        {treeViewRootName}
      </span>

    return (<TreeView onClick={() => { }} nodeLabel={labelNode}>
      {treeViewRoot.map(child => this.getTreeViewStructure(child), this)}
    </TreeView>)
  }

  render () {
    const topology = this.buildTopology(this.props.things)
    const TreeViewNav = this.buildTreeView(topology)

    if (this.state.loading && Object.keys(topology).length !== 0) {
      this.setState({
        ...this.state,
        loading: false
      })
    }

    if (this.state.loading) {
      return <Spinner />
    }

    return TreeViewNav
  }
}

export default TreeViewNav
