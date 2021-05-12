import React from "react";
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
import LogoutButton from "./LogoutButton";

import { useAuth0 } from "@auth0/auth0-react";
const Profile = () => {
  const { isAuthenticated } = useAuth0();

  return (
    isAuthenticated && (
      <div>
        <Router>
          <div className="App">
            <div class="visitor">
              <Route path="/" exact component={HeaderComponent} />
              <Route path="/" exact component={VisitorListComponent} />

              <Route
                path="/editVisitor/:id"
                exact
                component={EditVisitorComponent}
              />
              <Route path="/addVisitor" exact component={AddVisitorComponent} />
              <div>
                <svg>
                  <use href="/icons.c4b52749.svg"></use>
                </svg>
                <div id="visitor"> </div>
              </div>
              <div class="overlay hidden"></div>
              <div class="add-visitor-window hidden">
                <form class="upload"></form>
              </div>
            </div>
          </div>
          <Route path="/" exact component={FooterComponent} />
        </Router>
      </div>
    )
  );
};
export default Profile;
