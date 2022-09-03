import "./App.css";
import React, { useState, useEffect } from "react";
import LoginComponent from "./Components/LoginComponent";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import MainPageComponent from "./Components/MainPageComponent";
import Profile from "./Components/Profile";
import RegisterComponent from "./Components/RegisterComponent";
import EditPage from "./Components/EditPage";
import AdminPanel from "./Components/AdminPanel";

function App() {
  return (
    <div>
      <Router>
        <div className="App">
          <Switch>
            <Route path="/ping.com" exact component={LoginComponent} />
            <Route path="/edit/:username" exact component={EditPage} />
            <Route path="/ping/register" exact component={RegisterComponent} />
            <Route path="/admin" exact component={AdminPanel} />
            <Route
              path="/dashboard/:username"
              exact
              component={MainPageComponent}
            />
            <Route path="/profile/:username" exact component={Profile} />
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
