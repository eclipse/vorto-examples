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



let changingValuesIds = []

class TreeViewNav extends Component {
  state = {
    loading: true,
    selectedStates: []
  }


  getEntityChilds(things, references) {

    const referenceLabels = references.map(ref => ref.thingId)

    const entityList = referenceLabels.map(label => {
      const elem = things.find(thing => thing.thingId === label)
      const elemLabel = getTextAfterColon(elem.thingId)
      const elemReferences = elem.attributes.topology.references

      const topology = []
      topology[elemLabel] = this.getEntityChilds(things, elemReferences)
      return topology
    }, this)
    return entityList
  }


  getThingsWithoutTopology(things) {
    const toplessThings = []
    things.find(thing => {
      const thingTopo = thing.attributes.topology
      if (!thingTopo) {
        const thingId = getTextAfterColon(thing.thingId)
        toplessThings.push({ [thingId]: [] })
      }
    })
    return toplessThings
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


    const defaultLabel = "My Devices"
    const topology = {}

    if (!root) {
      if (things.length !== 0) {
        topology[defaultLabel] = this.getThingsWithoutTopology(things)
        return topology
      } else return {}
    }

    // if root of topology exists
    const rootLabel = getTextAfterColon(root.thingId)
    const rootReferences = root.attributes.topology.references

    // add both topologies
    topology[rootLabel] = this.getEntityChilds(things, rootReferences)
    topology[rootLabel]["selected"] = false

    topology[defaultLabel] = this.getThingsWithoutTopology(things)
    topology[defaultLabel]["selected"] = false
    return topology
  }



  handleUnselect(id, origin) {
    var joined = this.state.selectedStates
    // unselect all other nodes with different id
    Object.keys(joined).map(key => {
      joined[key].subNodes.forEach(element => {
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
    })

    this.setState({
      selectedStates: { ...joined }
    })

  }

  removeBodyClickDiv() {
    if (document.getElementById('bodyClick')) {
      document.getElementById('bodyClick').remove();
    }
    document.documentElement.classList.toggle('nav-open')
  }


  handleLabelClick(id, origin) {

    this.removeBodyClickDiv()

    var joined = this.state.selectedStates
    Object.keys(joined).map(key => {

      joined[key].subNodes.forEach(element => {

        //if either a label or a thing as direct child of root node selected
        if (element.id === id) {
          //unselect all other labels
          this.handleUnselect(id, origin)
          element.selected = true
          this.dispatchSelectedDevice(id)

          // no subchild = empty label or device => select it, go to devices route
          if (element.subNodes.length === 0) {
            this.props.history.push('/device')
          }
        }



        //thing in label selected if origin is the parent -> go to subNode
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
      joined[key].selected = (key === id) ? true : false
    })
    this.handleUnselect(id, origin)
    this.dispatchSelectedDevice(id)

    // set State for ui update
    this.setState({ selectedStates: { ...joined } })
  }

  dispatchSelectedDevice(id) {
    // save selected thing to redux store
    store.dispatch(Actions.selectDevice(
      this.props.things.find(element => { return getTextAfterColon(element.thingId) === id })))
  }

  dispatchValueChanges(ids) {
    if (ids.length > 0) {
      store.dispatch(Actions.changingValues(ids))
    }
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



  areValuesChanging(id) {
    var isSimulating = false
    const selectedStates = this.state.selectedStates

    Object.keys(selectedStates).map(key => {
      if (selectedStates[key].subNodes) {

        selectedStates[key].subNodes.forEach(element => {
          if (element.id === id && element.simulating) {
            isSimulating = true
          }

          element.subNodes.forEach(subElement => {
            if (subElement.id === id && subElement.simulating) {
              isSimulating = true
            }
          })

        })
      }
    })
    return isSimulating
  }

  isNodeSelected(id, origin) {
    var isSelected = false
    const selectedStates = this.state.selectedStates
    const propsSelectedDeviceId = getTextAfterColon(this.props.selectedDevice.thingId)

    Object.keys(selectedStates).map(key => {
      if (selectedStates[key].subNodes !== undefined) {
        //root
        if (key === id && selectedStates[key].selected |
          // if selected in props after e.g. page reload 
          key === propsSelectedDeviceId) {
          isSelected = true
        }

        //labels
        selectedStates[key].subNodes.forEach(element => {

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
    })
    return isSelected
  }



  buildTreeView(topology, things) {

    var treeViewResult = []

    for (let [key] of Object.entries(topology)) {
      // Mapping through root

      const treeViewRootName = key
      const treeViewRoot = topology[treeViewRootName]

      /// root nodes for items with topology
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

      const selectedRoot = { selected: false, subNodes: [] };
      let joined = this.state.selectedStates

      //add root of selectedState Array
      if (this.state.loading && Object.keys(topology).length !== 0) {

        joined[treeViewRootName] = selectedRoot
        this.setState({ selectedStates: { ...joined } });
      }

      treeViewResult = [treeViewResult, <TreeView
        onClick={() => { }}
        nodeLabel={rootNode}>
        {treeViewRoot.map((child) => this.getTreeViewStructure(child, treeViewRootName, true), this)}
      </TreeView>]
    }
    return (<div>{treeViewResult}</div>)
  }


  getThingIcon(id) {
    let thingIcon = require('../../assets/img/thing-icons/thing.svg');
    const things = (this.props.things) ? (this.props.things) : {}
    for (var thing of things) {
      if (getTextAfterColon(thing.thingId) === id) {
        thingIcon = (thing.imgSrc) || thingIcon
      }
    }
    return thingIcon
  }


  getTreeViewStructure(topology, origin, isRootNode) {
    var joined = this.state.selectedStates


    return Object.keys(topology).map((label) => {

      // if it has no child nodes it is a child node/thing
      if (topology[label].length === 0) {

        const selectedThing = { id: label, selected: false, simulating: false, subNodes: [] };


        // if we are adding things without topology directly to the root element && element is not already inside
        if (isRootNode && !this.isLabelInside(joined[origin].subNodes, label)) {

          joined[origin].subNodes.push(selectedThing)
          this.setState({
            selectedStates: { ...joined }
          })
        }

        //if we are adding a thing and we are not coming from a root element, so coming from a sublabel
        if (!isRootNode) {

          Object.keys(joined).map(key => {
            // add things to selectedState array
            if (joined[key].subNodes !== undefined) {
              joined[key].subNodes.forEach(element => {

                // if we are adding things in a sublabel
                if (!this.isLabelInside(element.subNodes, label)) {

                  if (element.id === origin) {
                    element.subNodes.push(selectedThing)
                    this.setState({
                      selectedStates: { ...joined }
                    })
                  }
                }
              })
            }
          })
        }

        const thingIcon = this.getThingIcon(label)

        return <div className={"thing " + (this.isNodeSelected(label, origin) ? "selected" : "")} onClick={() => {
          this.handleLabelClick(label, origin)
        }}>
          <div className={"simulation-indicator " + (this.areValuesChanging(label, origin) ? "visible" : "")}><div></div><div></div><div></div><div></div></div>
          <div className='thing_icon-container'>
            <img className='thing_icon'
              alt="thing-icon"
              src={thingIcon} />
          </div>
          <span>{label}</span>
        </div>
      }


      //   add labels to selectedState array
      if (isRootNode) {
        if (joined[origin]) {
          if (!this.isLabelInside(joined[origin].subNodes, label)) {
            const selectedLabel = { id: label, selected: false, subNodes: [] };
            joined[origin].subNodes.push(selectedLabel)
            this.setState({
              selectedStates: { ...joined }
            })
          }

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
          {topology[label].map((child) =>
            this.getTreeViewStructure(child, label, false), this)}
        </TreeView>)
    }, this)
  }

  refreshUpdatingAnimation(id, simulating) {
    var joined = this.state.selectedStates
    Object.keys(joined).map(key => {
      joined[key].subNodes.forEach(element => {
        if (element.id === id) {
          element.simulating = simulating
        }

        element.subNodes.forEach(subElement => {
          if (subElement.id === id) {
            subElement.simulating = simulating
          }
        })
      })
    })
    this.setState({ selectedStates: { ...joined } })
  }

  checkForValueChanges() {
    const current = store.getState().devices.devices
    const previous = store.getState().devices.lastState

    for (var i in current) {
      const thingId = getTextAfterColon(current[i].thingId)
      const areEqual = (JSON.stringify(current[i]) === JSON.stringify(previous[i]))
      const hasFeatures = (current[i].features && current[i].features.length !== 0) ? true : false
      // const isAThing = (current[i].features && previous[i].features)

      if (!areEqual) {
        if (changingValuesIds.indexOf(thingId) === -1 && hasFeatures) {
          changingValuesIds.push(thingId)
          this.dispatchValueChanges(changingValuesIds)
          this.refreshUpdatingAnimation(thingId, true)
        }
      }
      
      for (var changingValId of changingValuesIds) {
        if (thingId === changingValId && areEqual) {
          //remove from changing values array
          const index = changingValuesIds.map(function (item) {
            return item
          }).indexOf(thingId);

          changingValuesIds.splice(index, 1);
          this.dispatchValueChanges(changingValuesIds)
          this.refreshUpdatingAnimation(thingId, false)
        }
      }
    }

  }


  render() {

    store.subscribe(() => { this.checkForValueChanges() })

    const topology = this.buildTopology(this.props.things)
    const TreeViewNav = this.buildTreeView(topology, this.props.things)
    if (this.state.loading && Object.keys(topology).length !== 0) {
      this.setState({ loading: false });
    }
    if (this.state.loading) {
      return <Spinner />
    }

    return TreeViewNav
  }
}

const mapStateToProps = function () {
  return {
    selectedDevice: store.getState().selectedDevice
  }
}

export default withRouter(connect(mapStateToProps)(TreeViewNav)) 
