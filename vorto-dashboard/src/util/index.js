function checkDeviceForQuery(device, { searching, query }) {
  if (!searching || !query) {
    return true
  }

  const [serachKeyword, searchQuery] = query.split(":").map(elem => elem.trim())

  /* TODO add more filtering options
    - gt:temperature 5
    - lt:battery 50
    - ...
  */
  switch (serachKeyword) {
    case "has":
      return Object.keys(device.features)
        .some(feature => device.features[feature].definition
          .some(def => def.toLowerCase().includes(searchQuery)));
    default:
      return device.attributes.definition
        .toLowerCase()
        .includes(query)
  }
}

function getRepositoryLink(path) {
  return `https://vorto.eclipse.org/#/details/${path}`
}

export {
  checkDeviceForQuery,
  getRepositoryLink
}