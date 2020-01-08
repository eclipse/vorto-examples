export function getTextAfterColon (text) {
  if(text && text.includes(":")){
    return /.*?:(.*)/.exec(text)[1]
  }
  return ""
}

export function countDevicesInTopoloy(thingsInTopo, allDevices){
  let result = []
  if (!allDevices) {
    return 0
  }
  let count = 0
  allDevices.forEach(thing => {
    const thingTopo = thing.attributes.topology
    if (!thingTopo ) {
      thingsInTopo.forEach(element => {
        if(thing.thingId.includes(element)){
          if(result.indexOf(thing.thingId) === -1 ) result.push(thing.thingId)
        } 
      })
    }
    if (thingTopo) {
      //counting all referencable devices with topology and excluding suptopologies e.g Zone A
      if (JSON.stringify(thingTopo).includes("org.eclipse.vorto:Referencable:1.0.0") && thingTopo.references.length === 0) {    
        thingsInTopo.forEach(element => {
        if(thing.thingId.includes(element)){
          if(result.indexOf(thing.thingId) === -1 ) result.push(thing.thingId)
        } 
        } 
      )
      }
    }
}) 
return result.length
}

export function getThingsInTopology(topology, selectedDevice) {
  var thingsInTopology = []
  if (!selectedDevice) {
    return []
  }
  Object.entries(topology).map(
    ([key, value]) => {
      // subnodes under root nodes
      if (selectedDevice.includes(key)) {
        // add all direct child elements
        value.subNodes.forEach(element => {
          thingsInTopology.push(element.id)

          if (element.subNodes.length !== 0) {
            element.subNodes.forEach(subelement => {
              thingsInTopology.push(subelement.id)
            })
          }
        });

      } else if(value.subNodes){
        //subnode selected
        value.subNodes.forEach(element => {
          if (selectedDevice.includes(element.id)) {
            if (element.subNodes.length !== 0) {
              element.subNodes.forEach(subelement => {
                thingsInTopology.push(subelement.id)
              })
            }
          }
        })
      }

    }
  )
  return thingsInTopology
}


export function getRepositoryLink (path) {
  if (Array.isArray(path)) {
    return `https://vorto.eclipse.org/#/details/${path[0]}`
  }

  return `https://vorto.eclipse.org/#/details/${path}`
}
