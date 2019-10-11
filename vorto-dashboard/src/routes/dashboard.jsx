import Main from '../views/Main/Main'
import Device from '../views/Device/Device'


const dashboardRoutes = [
  {
    path: '/main',
    name: 'Locate things',
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
