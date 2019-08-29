function getTextAfterColon (text) {
  return /.*?:(.*)/.exec(text)[1]
}

module.exports = {
  getTextAfterColon
}
