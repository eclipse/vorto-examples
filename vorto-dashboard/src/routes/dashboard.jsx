import Device from '../views/Device/Device'
import Main from '../views/Main/Main'


const dashboardRoutes = [
  {
    path: '/main',
    name: 'Locates all contained things on a map',
    icon: 'pe-7s-global',
    component: Main
  },
  {
    path: '/device',
    name: 'Show single device',
    icon: 'pe-7s-global',
    component: Device
  },
  { 
    redirect: true, 
    path: '/', 
    to: '/main', 
    name: 'Locate Assets' 
  }
]

export default dashboardRoutes
