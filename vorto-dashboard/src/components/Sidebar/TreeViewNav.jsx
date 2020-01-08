import React, {Component} from 'react'
import TreeView from 'react-treeview'

import log from 'loglevel'
import Spinner from '../Spinner/Spinner';
import {getTextAfterColon} from '../../util'
import Actions from '../../actions/'
import {connect} from 'react-redux';
import {withRouter} from 'react-router';


log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const {store} = require('../../store')

const LabelType = {
    DEVICE: "DEVICE",
    LABEL: "LABEL",
    ROOT: "ROOT"
}

const FALLBACK_ROOT = {
    thingId: ":My Devices",
    attributes: {
        topology: {
            definition: "org.eclipse.vorto:Topology:1.0.0"
        },
        thingName: "My Devices",
    }
}

let changingValuesIds = []


class TreeViewNav extends Component {
    state = {
        loading: true,
        topologyState: []
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
                toplessThings.push({[thingId]: []})
            }
        })
        return toplessThings
    }


    buildTopology(things) {
        if (!things) {
            return {}
        }
        const topology = {}
        let totalDevicesCount = 0
        things.forEach(thing => {
            const thingTopo = thing.attributes.topology
            if (!thingTopo) {
                //counting all devices without topology
                totalDevicesCount++
            }
            if (thingTopo) {
                //counting all referencable devices with topology and excluding suptopologies e.g Zone A
                if (JSON.stringify(thingTopo).includes("org.eclipse.vorto:Referencable:1.0.0") && thingTopo.references.length === 0) {
                    totalDevicesCount++
                }
                if (JSON.stringify(thingTopo).includes("org.eclipse.vorto:Topology")) {
                    const rootLabel = getTextAfterColon(thing.thingId)
                    const rootReferences = thing.attributes.topology.references
                    if (rootReferences) {
                        topology[rootLabel] = this.getEntityChilds(things, rootReferences)
                        topology[rootLabel]["selected"] = false
                    } else {
                        if (things.length > 1) {
                            // > 1 because the empty My Devices Topology is already inside
                            topology[rootLabel] = this.getThingsWithoutTopology(things)
                            topology[rootLabel]["selected"] = false
                        }
                    }
                }
            }
        })
        this.dispatchDeviceAmount(totalDevicesCount)
        return topology
    }


    handleSelection(id, origin) {
        var joined = this.state.topologyState
        // unselect all other nodes with different id
        Object.keys(joined).map(key => {
            joined[key].subNodes.forEach(element => {
                    if (element.id !== id || joined.id === id) {
                        element.selected = false
                    } else {
                        element.selected = true
                    }

                    element.subNodes.forEach(unselect => {
                        unselect.selected = false
                    })

                    // if thing clicked unselect all others things with different id
                    element.subNodes.forEach(subElement => {
                        subElement.selected = (subElement.id === id && element.id === origin) ? true : false
                    })

                }
            )
            joined[key].selected = (key === id) ? true : false
        })

        this.setState({
            topologyState: {...joined}
        })

    }

    removeBodyClickDiv() {
        //untoggle nav sidebar
        if (document.getElementById('bodyClick')) {
            document.getElementById('bodyClick').remove();
        }
        document.documentElement.classList.toggle('nav-open')
    }


    handleLabelClick(id, origin, labelType) {
        this.removeBodyClickDiv()
        this.handleSelection(id, origin)
        this.dispatchSelectedDevice(id)

        switch (labelType) {
            case LabelType.DEVICE:
                this.props.history.push('/device')
                break;
            case LabelType.LABEL:
                this.props.history.push('/main')
                break;
            case LabelType.ROOT:
                this.props.history.push('/main')
                break;
            default:
                break;
        }
    }

    dispatchSelectedDevice(id) {
        // save selected thing to redux store
        try {
            store.dispatch(Actions.selectDevice(
                this.props.things.find(element => {
                    return getTextAfterColon(element.thingId) === id
                })))
        } catch (error) {
            console.log("error while storing", error)
        }

    }

    dispatchValueChanges(ids) {
        if (ids.length > 0) {
            store.dispatch(Actions.changingValues(ids))
        }
    }

    dispatchTopologyState(topologyState) {
        if (topologyState) {
            store.dispatch(Actions.updateTopology(topologyState))
        }
    }

    dispatchDeviceAmount(totalDevices) {
        if (totalDevices) {
            store.dispatch(Actions.countDevices(totalDevices))

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
        const topologyState = this.state.topologyState

        Object.keys(topologyState).map(key => {
            if (topologyState[key].subNodes) {

                topologyState[key].subNodes.forEach(element => {
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
        const topologyState = this.state.topologyState
        const propsSelectedDeviceId = getTextAfterColon(this.props.selectedDevice.thingId)
        Object.keys(topologyState).map(key => {
            if (topologyState[key].subNodes !== undefined) {
                //root
                if (key === id && topologyState[key].selected |
                    // if selected in props after e.g. page reload
                    key === propsSelectedDeviceId) {
                    isSelected = true
                }

                //labels
                topologyState[key].subNodes.forEach(element => {

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


    buildTreeView(topology) {

        var treeViewResult = []


        for (let [key] of Object.entries(topology)) {
            // Mapping through root

            const treeViewRootName = key
            const treeViewRoot = topology[treeViewRootName]

            /// root nodes for items with topology
            const rootNode =
                <div className={"clickable-label " + (this.isNodeSelected(treeViewRootName) ? "selected" : "")}
                     onClick={() => {
                         this.handleLabelClick(treeViewRootName, null, LabelType.ROOT)
                     }}>
          <span>
            {treeViewRootName}
          </span>
                </div>

            const selectedRoot = {selected: false, subNodes: []};
            let joined = this.state.topologyState

            //add root of selectedState Array
            if (this.state.loading && Object.keys(topology).length !== 0) {

                joined[treeViewRootName] = selectedRoot
                this.setState({topologyState: {...joined}});
            }

            treeViewResult = [treeViewResult, <TreeView
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
        var joined = this.state.topologyState


        return Object.keys(topology).map((label) => {

            // if it has no child nodes it is a child node/thing
            if (topology[label].length === 0) {

                const selectedThing = {id: label, selected: false, simulating: false, subNodes: []};


                // if we are adding things without topology directly to the root element && element is not already inside
                if (isRootNode && !this.isLabelInside(joined[origin].subNodes, label)) {

                    joined[origin].subNodes.push(selectedThing)
                    this.setState({
                        topologyState: {...joined}
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
                                            topologyState: {...joined}
                                        })
                                    }
                                }
                            })
                        }
                    })
                }

                const thingIcon = this.getThingIcon(label)

                return <div className={"thing " + (this.isNodeSelected(label, origin) ? "selected" : "")}
                            onClick={() => {
                                //clicked device
                                this.handleLabelClick(label, origin, LabelType.DEVICE)
                            }}>
                    <div className={"simulation-indicator " + (this.areValuesChanging(label, origin) ? "visible" : "")}>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                    <div className='thing_icon-container'>
                        <img className='thing_icon'
                             alt="thing-icon"
                             src={thingIcon}/>
                    </div>
                    <span>{label}</span>
                </div>
            }


            //   add labels to selectedState array
            if (isRootNode) {
                if (joined[origin]) {
                    if (!this.isLabelInside(joined[origin].subNodes, label)) {
                        const selectedLabel = {id: label, selected: false, subNodes: []};
                        joined[origin].subNodes.push(selectedLabel)
                        this.setState({
                            topologyState: {...joined}
                        })
                    }
                }
            }


            const labelNode =
                <div
                    className={"clickable-label " + (this.isNodeSelected(label, origin) ? "selected" : "")}
                    onClick={() => {
                        this.handleLabelClick(label, origin, LabelType.LABEL)
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
        var joined = this.state.topologyState
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
        this.setState({topologyState: {...joined}})
    }

    checkForValueChanges() {
        const current = store.getState().devices.devices
        const previous = store.getState().devices.lastState

        for (var i in current) {
            const thingId = getTextAfterColon(current[i].thingId)
            const areEqual = (JSON.stringify(current[i]) === JSON.stringify(previous[i]))
            const hasFeatures = (current[i].features && current[i].features.length !== 0) ? true : false

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

        store.subscribe(() => {
            this.checkForValueChanges()
        })

        const last = this.props.things[this.props.things.length - 1]
        if (last) {
            if (last.thingId !== FALLBACK_ROOT.thingId) {
                this.props.things.push(FALLBACK_ROOT)

            }
        }

        const topology = this.buildTopology(this.props.things)
        const TreeViewNav = this.buildTreeView(topology)
        if (this.state.loading && Object.keys(topology).length !== 0) {
            this.setState({loading: false});
        }
        if (this.state.loading) {
            return <Spinner/>
        }
        this.dispatchTopologyState(this.state.topologyState)

        return TreeViewNav
    }
}

const mapStateToProps = function () {
    return {
        selectedDevice: store.getState().selectedDevice
    }
}

export default withRouter(connect(mapStateToProps)(TreeViewNav)) 
