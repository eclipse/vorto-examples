import Locate from '../views/Locate/Locate'

const dashboardRoutes = [
  {
    path: '/locate',
    name: 'Locate things',
    icon: 'pe-7s-global',
    component: Locate
  },
  { redirect: true, path: '/', to: '/locate', name: 'Locate Assets' }
]

export default dashboardRoutes
