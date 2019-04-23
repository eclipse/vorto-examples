import Devices from "../views/Devices/Devices";
import DeviceDashboard from "../views/DeviceDashboard/DeviceDashboard"
import Locate from "../views/Locate/Locate"

const dashboardRoutes = [
  {
    path: "/devices",
    name: "Devices",
    icon: "pe-7s-usb",
    component: Devices
  },
  {
    path: "/devicedashboard",
    name: "Device Dashboard",
    component: DeviceDashboard,
    hidden: true
  },
  {
    path: "/locate",
    name: "Locate",
    icon: "pe-7s-global",
    component: Locate
  },
  { redirect: true, path: "/", to: "/devices", name: "Devices" }
];

export default dashboardRoutes;
