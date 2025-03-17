import { BrowserRouter as Router, useLocation } from "react-router-dom";
import AppUser from "./user/User";
import AdminApp from "./admin/Admin";

const AppMainContent = () => {
  const location = useLocation();
  return location.pathname.startsWith("/admin") ? <AdminApp /> : <AppUser />;
};

const AppMain = () => (
  <Router>
    <AppMainContent />
  </Router>
);

export default AppMain;
