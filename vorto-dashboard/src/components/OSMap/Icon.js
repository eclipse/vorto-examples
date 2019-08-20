import L from 'leaflet'

// Adding custom icons
const regularIcon = new L.Icon({
  iconUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconRetinaUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconSize: new L.Point(60, 75),
  className: 'leaflet-div-icon'
})

export { regularIcon }
