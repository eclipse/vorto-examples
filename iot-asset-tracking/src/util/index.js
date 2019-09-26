function getTextAfterColon (text) {
  return /.*?:(.*)/.exec(text)[1]
}



function getRepositoryLink (path) {
  if (Array.isArray(path)) {
    return `https://vorto.eclipse.org/#/details/${path[0]}`
  }

  return `https://vorto.eclipse.org/#/details/${path}`
}


module.exports = {
  getTextAfterColon,
  getRepositoryLink
}