import "./App.css";
import back from "./correctBack.png";
import VisitorListComponent from "./Visitor/VisitorListComponent";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditVisitorComponent from "./Visitor/EditVisitorComponent";
import AddVisitorComponent from "./Visitor/AddVisitorComponent";
import HeaderComponent from "./Header/HeaderComponent";
import FooterComponent from "./Footer/FooterComponent";
import LoginComponent from "./Login/LoginComponent";
import LoginButton from "./LoginButton";
import { useAuth0 } from "@auth0/auth0-react";
import LogoutButton from "./LogoutButton";
import Profile from "./Profile";

function App() {
  const { isAuthenticated } = useAuth0();
  return (
    <div>
      <Router>
        <div className="App">
          <div class="visitor">
            <LoginButton />

            <Profile />
          </div>
        </div>
      </Router>
    </div>
  );
}
export default App;
