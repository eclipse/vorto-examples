function getTextAfterColon (text) {
  if(text){
    return /.*?:(.*)/.exec(text)[1]
  }
  return ""
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