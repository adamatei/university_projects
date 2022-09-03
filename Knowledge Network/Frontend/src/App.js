import "./App.css";
import React, { useEffect } from "react";
import Dashboard from "./components/Dashboard/Dashboard";
import Information from "./components/Information";
import About from "./components/About";
import LoginForm from "./components/LoginForm/LoginForm";
import RegistrationForm from "./components/RegisterForm/RegistrationForm";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import NavigationBar from "./components/NavigationBar";
import AdminPanel from "./components/AdminPanel/AdminPanel";
import ViewUser from "./components/AdminPanel/ViewUser";
import EditUser from "./components/AdminPanel/EditUser";
import Profile from "./components/User/Profile";
import AuthService from "./auth.service";
import { runInAction } from "mobx";
import { observer } from "mobx-react";
import UserStore from "./stores/UserStore";
import GraphEdit from "./components/GraphEdit/GraphEdit";
import UsersOverview from "./components/AdminPanel/UsersOverview";
import DataDashboard from "./components/DataDashboard/DataDashboard";
import SubmitDataForm from "./components/SubmitDataForm/SubmitDataForm"

function App() {
  useEffect(() => {
    //one way to change the whole page's background color, might not be the best solution
    document.body.style.backgroundColor = "gray";

    let auth = AuthService.getCurrentUser();

    runInAction(() => {
      UserStore.token = auth.token;
      UserStore.user_id = auth.user_id;
      UserStore.role = auth.role;
    });
  });

  function renderRoutes(role) {
    switch (role) {
      case "":
        return (
          <Switch>
            <Route path="/login" exact component={LoginForm} />
            <Route path="/register" exact component={RegistrationForm} />
          </Switch>
        );
      default:
        return (
          <Switch>
            <Route path="/admin" exact component={AdminPanel} />
            <Route path="/edit/user/:id" exact component={EditUser} />
            <Route path="/user/:id" exact component={ViewUser} />
            <Route path="/home" exact component={Dashboard} />
            <Route path="/information" exact component={Information} />
            <Route path="/graph-edit" exact component={GraphEdit} />
            <Route path="/about" exact component={About} />
            <Route path="/profile" exact component={Profile} />
            <Route path="/usersoverview" exact component={UsersOverview} />
            <Route path="/data" exact component={DataDashboard} />
            <Route path="/submitdata" exact component={SubmitDataForm} />
          </Switch>
        );
    }
  }

  function renderApp() {
    if (UserStore.loading) {
      return (
        <div className="app">
          <p style={{ fontSize: "3rem", textAlign: "center" }}>
            Loading, please wait...
          </p>
        </div>
      );
    }

    return (
      <div className="app">
        <Router>
          <NavigationBar role={UserStore.role} id={UserStore.user_id} />
          {renderRoutes(UserStore.role)}
        </Router>
      </div>
    );
  }

  return renderApp();
}

export default observer(App);
