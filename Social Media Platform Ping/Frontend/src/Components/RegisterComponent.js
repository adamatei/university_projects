import React from "react";
import "./LoginComponent.css";
import logo from "../logo.png";
import ParticlesBg from "particles-bg";
import AuthenticationService from "../Services/AuthenticationService";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class RegisterComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };

    this.handleUsername = this.handleUsername.bind(this);
    this.handlePassword = this.handlePassword.bind(this);
    this.register = this.register.bind(this);
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

  register(e) {
    e.preventDefault();
    if (this.state.username.trim() && this.state.password.trim()) {
      let user = {
        username: this.state.username,
        password: this.state.password,
      };
      AuthenticationService.register(user)
        .then((res) => {
          if (res.data == false) {
            confirmAlert({
              customUI: ({ onClose }) => {
                return (
                  <div className="custom-ui">
                    <h1>Username already exists! </h1>
                    <p>Please try again</p>
                    <button className="button-1" onClick={onClose}>
                      Close
                    </button>
                  </div>
                );
              },
            });
          } else {
            this.props.history.push("/ping.com");
            window.location.reload(false);
          }
        })
        .catch((error) => {
          confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className="custom-ui">
                  <h1>Something went wrong! </h1>
                  <p>Please try again</p>
                  <button className="button-1" onClick={onClose}>
                    Close
                  </button>
                </div>
              );
            },
          });
          return error;
        });
    } else {
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
            <button
              className="login-button"
              title="login"
              onClick={this.register}
            >
              Register
            </button>
            <div className="separator">
              <div className="line"></div>
              <p>Below</p>
              <div className="line"></div>
            </div>
          </form>
        </div>
        <div className="box">
          <button className="fb-login-btn" type="button">
            <span className="">
              By signing up, you agree to our Terms. Learn how we collect, use
              and share your data in our Data Policy and how we use cookies and
              similar technology in our Cookies Policy.
            </span>
          </button>
          <a
            className="forgot-password"
            href="https://help.instagram.com/581066165581870"
          >
            {" "}
            Learn more here.
          </a>
        </div>
        <div className="box">
          <p>
            Already have an account?{" "}
            <a className="signup" href="http://localhost:3000/ping.com">
              Log in
            </a>
          </p>
        </div>
        <ParticlesBg type="circle" bg={true} />
      </div>
    );
  }
}

export default RegisterComponent;
