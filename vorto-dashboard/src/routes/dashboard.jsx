import Devices from "../views/Devices/Devices";
import DeviceDashboard from "../views/DeviceDashboard/DeviceDashboard"
import Locate from "../views/Locate/Locate"
import Simulator from "../views/Simulator/Simulator";

const SHOW_SIMULATOR = process.env.REACT_APP_SHOW_SIMULATOR || false;
const simulatorRoute = {
  path: "/simulator",
  name: "Simulate Data",
  icon: "pe-7s-edit",
  component: Simulator
};

let dashboardRoutes = [
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

if (SHOW_SIMULATOR) {
  dashboardRoutes.splice(3, 0, simulatorRoute);
}

export default dashboardRoutes;
