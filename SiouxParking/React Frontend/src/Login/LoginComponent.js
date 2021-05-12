import React from "react";
import "./LoginComponent.css";
class LoginComponent extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <div class="login-form">
        <h2 class="heading-secondary ma-bt-lg">Log into your account</h2>
        <form class="form form--login">
          <div class="form__group"></div>
          <label class="form__label" for="email">
            Email address
          </label>
          <input
            class="form__input"
            id="email"
            type="email"
            placeholder="you@example.com"
            required="required"
          ></input>
          <div class="form__group ma-bt-md"></div>
          <label class="form__label" for="password">
            Password
          </label>
          <input
            class="form__input"
            id="password"
            type="password"
            placeholder="••••••••"
            required="required"
            minlength="8"
          ></input>
          <div class="form__group">
            <button class="btn btn--green">Login</button>
          </div>
        </form>
      </div>
    );
  }
}
export default LoginComponent;
