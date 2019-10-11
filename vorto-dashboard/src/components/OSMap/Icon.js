import L from 'leaflet'

const workerIcon = new L.Icon({
  iconUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconRetinaUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconSize: new L.Point(60, 75),
  className: 'leaflet-div-icon'
})

const machineIcon = new L.Icon({
  iconUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconRetinaUrl: 'https://image.flaticon.com/icons/svg/33/33622.svg',
  iconSize: new L.Point(60, 75),
  className: 'leaflet-div-icon'
})

export {
  workerIcon,
  machineIcon
}
