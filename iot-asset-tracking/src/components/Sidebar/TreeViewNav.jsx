import React, { Component } from 'react'
import TreeView from 'react-treeview'

import log from 'loglevel'
import Spinner from '../Spinner/Spinner';
import { getTextAfterColon } from '../../util'
import Actions from '../../actions/'
import { connect } from 'react-redux';
import { withRouter } from 'react-router';



log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const { store } = require('../../store')

class TreeViewNav extends Component {
  state = {
    loading: true,
    selectedStates: {}
  }


  getEntityChilds(things, references) {
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

  buildTopology(things) {
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

    const rootLabel = getTextAfterColon(root.thingId)
    const rootReferences = root.attributes.topology.references

    const topology = {}
    topology[rootLabel] = this.getEntityChilds(things, rootReferences)
    return topology
  }



  handleUnselect(id, origin) {
    var joined = this.state.selectedStates
    // unselect all other nodes with different id

    joined.subNodes.forEach(element => {
      if (element.id !== id || joined.id === id) {
        element.selected = false
      }

      element.subNodes.forEach(unselect => {
        unselect.selected = false
      })

      // if thing clicked unselect all others things with different id
      element.subNodes.forEach(subElement => {
        subElement.selected = (subElement.id === id && element.id === origin) ? true : false
      })
    })

    this.setState({
      selectedStates: { ...joined }
    })

  }

  handleLabelClick(id, origin) {
    var joined = this.state.selectedStates
    joined.subNodes.forEach(element => {
      if (element.id === id) {
        //unselect all other labels
        this.handleUnselect(id, origin)
        //label selected
        element.selected = true
        this.dispatchSelectedDevice(id)
      }

      //thing selected if origin is the parent -> go to subNode
      if (element.id === origin) {
        element.subNodes.forEach(subElement => {

          //if thing selected
          if (subElement.id === id) {

            subElement.selected = true
            if (subElement.selected) {
              this.handleUnselect(id, origin)
            }
            this.dispatchSelectedDevice(id)
            this.props.history.push('/device')
          }
        })
      }
    })

    //root
    joined.selected = (joined.id === id) ? true : false
    this.handleUnselect(id, origin)
    this.dispatchSelectedDevice(id)
    // set State for ui
    this.setState({ selectedStates: { ...joined } })
  }

  dispatchSelectedDevice(id) {
    // save selected thing to redux store
    store.dispatch(Actions.selectDevice(
      this.props.things.find(element => {return getTextAfterColon(element.thingId) === id})))

  }


  isLabelInside(struct, id) {
    var contains = false
    struct.forEach(element => {
      if (id === element.id) {
        contains = true
      }
    })
    return contains
  }

  isNodeSelected(id, origin) {
    var isSelected = false
    const selectedStates = this.state.selectedStates
    const propsSelectedDeviceId = getTextAfterColon(this.props.selectedDevice.thingId)

    if (selectedStates.subNodes !== undefined) {

      //root
      if (selectedStates.id === id && selectedStates.selected | 
        // if selected in props after e.g. page reload 
        selectedStates.id === propsSelectedDeviceId) {
        isSelected = true
      }

      //labels
      selectedStates.subNodes.forEach(element => {

        if (element.id === id && element.selected |
          element.id === propsSelectedDeviceId) {
          isSelected = true
        }

        //things
        if (element.id === origin) {
          element.subNodes.forEach(subElement => {
            if (subElement.id === id && subElement.selected |
              subElement.id === propsSelectedDeviceId) {
              isSelected = true
            }
          })
        }
      })
    }
    return isSelected
  }


  buildTreeView(topology) {
    const treeViewRootName = Object.keys(topology)[0]
    const treeViewRoot = topology[treeViewRootName]

    if (!treeViewRoot) {
      return (<TreeView />)
    }

    const selectedRoot = { id: treeViewRootName, selected: false, subNodes: [] };

    //add root of selectedState Array
    if (this.state.loading && Object.keys(topology).length !== 0) {
      this.setState({ selectedStates: { ...selectedRoot } });
    }

    const rootNode =
      <div className={"clickable-label " + (this.isNodeSelected(treeViewRootName) ? "selected" : "")}
        onClick={() => {
          this.handleLabelClick(treeViewRootName)
          this.props.history.push('/main')
        }}>
        <span >
          {treeViewRootName}
        </span>
      </div>

    return (<TreeView 
      onClick={() => {}} 
      nodeLabel={rootNode}>
      {treeViewRoot.map(child => this.getTreeViewStructure(child, treeViewRootName), this)}
    </TreeView>)
  }

  getTreeViewStructure(topology, origin) {
    // const thingIcon_helmet = require('../../assets/img/thing-icons/helmet.svg');
    const thingIcon = require('../../assets/img/thing-icons/thing.svg');

    var joined = this.state.selectedStates

    return Object.keys(topology).map((label) => {

      // if it has no child nodes it is a child node/thing
      if (topology[label].length === 0) {

        //add things to selectedState array
        if (joined.subNodes !== undefined) {
          joined.subNodes.forEach(element => {

            if (!this.isLabelInside(element.subNodes, label)) {
              const selectedThing = { id: label, selected: false, subNodes: [] };

              if (element.id === origin) {
                element.subNodes.push(selectedThing)
                this.setState({
                  selectedStates: { ...joined }
                })
              }
            }
          })
        }


        return <div className={"thing " + (this.isNodeSelected(label, origin) ? "selected" : "")} onClick={() => {
          this.handleLabelClick(label, origin)
        }}>
          <div className='thing_icon-container'>
            <img className='thing_icon'
              alt="thing-icon"
              src={thingIcon} />
          </div>
          <span>{label}</span>
        </div>
      }

      //add labels to selectedState array
      if (joined.subNodes !== undefined) {
        if (!this.isLabelInside(joined.subNodes, label)) {
          const selectedLabel = { id: label, selected: false, subNodes: [] };
          joined.subNodes.push(selectedLabel)
          this.setState({
            selectedStates: { ...joined }
          })
        }
      }


      const labelNode =
        <div
          className={"clickable-label " + (this.isNodeSelected(label, origin) ? "selected" : "")}
          onClick={() => {
            this.handleLabelClick(label, origin)
            this.props.history.push('/main')
          }}>
          <span className='node'>
            {label}
          </span>
        </div>



      return (
        <TreeView
          nodeLabel={labelNode}>
          {topology[label].map(child => this.getTreeViewStructure(child, label), this)}
        </TreeView>)
    }, this)
  }



  render() {


    const topology = this.buildTopology(this.props.things)
    const TreeViewNav = this.buildTreeView(topology)


    if (this.state.loading && Object.keys(topology).length !== 0) {
      this.setState({ loading: false });
      ;
    }



    if (this.state.loading) {
      return <Spinner />
    }

    return TreeViewNav
  }
}

const mapStateToProps = function() {
  return {
    selectedDevice: store.getState().selectedDevice,
  }
}

export default withRouter(connect(mapStateToProps)(TreeViewNav)) 
