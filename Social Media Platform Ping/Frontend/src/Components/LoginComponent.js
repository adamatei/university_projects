import React from "react";
import "./LoginComponent.css";
import logo from "../logo.png";
import AuthenticationService from "../Services/AuthenticationService";
import ParticlesBg from "particles-bg";
import "react-notifications/lib/notifications.css";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class LoginComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };
    this.handleUsername = this.handleUsername.bind(this);
    this.handlePassword = this.handlePassword.bind(this);
    this.login = this.login.bind(this);
  }

  handleUsername(event) {
    this.setState({
      username: event.target.value,
    });
  }

  handlePassword(event) {
    this.setState({
      password: event.target.value,
    });
  }

  componentDidMount() {
    localStorage.clear();
  }

  login(e) {
    e.preventDefault();
    console.log("Username " + this.state.username);
    console.log("Password " + this.state.password);
    if (this.state.username.trim() && this.state.password.trim()) {
      let request = {
        username: this.state.username,
        password: this.state.password,
      };
      try {
        AuthenticationService.authentication(request)
          .then((res) => {
            localStorage.setItem("token", res.data);
            localStorage.setItem("username", this.state.username);
            this.props.history.push("/dashboard/" + this.state.username);
            window.location.reload(false);
          })
          .catch((error) => {
            console.log(error);
            confirmAlert({
              customUI: ({ onClose }) => {
                return (
                  <div className="custom-ui">
                    <h1>Something went wrong!</h1>
                    <p>Please try again</p>
                    <button className="button-1" onClick={onClose}>
                      Close
                    </button>
                  </div>
                );
              },
            });
          });
      } catch (error) {
        confirmAlert({
          customUI: ({ onClose }) => {
            return (
              <div className="custom-ui">
                <h1>Something went wrong!</h1>
                <p>Please try again</p>
                <button className="button-1" onClick={onClose}>
                  Close
                </button>
              </div>
            );
          },
        });
      }
    } else {
      console.log("Were null");
      confirmAlert({
        customUI: ({ onClose }) => {
          return (
            <div className="custom-ui">
              <h1>At least one field is empty! </h1>
              <p>Please fill in again</p>
              <button className="button-1" onClick={onClose}>
                Close
              </button>
            </div>
          );
        },
      });
    }
  }
  render() {
    return (
      <div className="container">
        <div className="box">
          <div className="heading">
            <picture id="login_logo">
              <img src={logo} />
            </picture>
          </div>
          <form className="login-form">
            <div className="field">
              <input
                id="username"
                type="name"
                value={this.state.username}
                onChange={this.handleUsername}
              />
              <label for="username">Phone number, username, or email</label>
            </div>
            <div className="field">
              <input
                id="password"
                type="password"
                value={this.state.password}
                onChange={this.handlePassword}
              />
              <label for="password">Password</label>
            </div>
            <button className="login-button" title="login" onClick={this.login}>
              Log In
            </button>
            <div className="separator">
              <div className="line"></div>
              <p>OR</p>
              <div className="line"></div>
            </div>
            <div className="other">
              <button className="fb-login-btn" type="button">
                <i className="fa fa-facebook-official fb-icon"></i>
                <span className="">Log in with Facebook</span>
              </button>
              <a className="forgot-password" href="#">
                Forgot password?
              </a>
            </div>
          </form>
        </div>
        <div className="box">
          <p>
            Don't have an account?{" "}
            <a className="signup" href="http://localhost:3000/ping/register">
              Sign Up
            </a>
          </p>
        </div>
        <ParticlesBg type="circle" bg={true} />
      </div>
    );
  }
}

export default LoginComponent;
