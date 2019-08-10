import Devices from "../views/Devices/Devices";
import DeviceDashboard from "../views/DeviceDashboard/DeviceDashboard"
import Locate from "../views/Locate/Locate"
import Simulator from "../views/Simulator/Simulator";

const SHOW_SIMULATOR = process.env.REACT_APP_SHOW_SIMULATOR || true;
const simulatorRoute = {
  path: "/simulator",
  name: "Simulate things",
  icon: "pe-7s-edit",
  component: Simulator
};

let dashboardRoutes = [
  {
    path: "/devices",
    name: "Browse things",
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
    name: "Locate things",
    icon: "pe-7s-global",
    component: Locate
  },
  { redirect: true, path: "/", to: "/devices", name: "Browse things" }
];

if (SHOW_SIMULATOR) {
  dashboardRoutes.splice(3, 0, simulatorRoute);
}

export default dashboardRoutes;
