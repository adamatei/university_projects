import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import "./LoginForm.css";
import { useState } from "react";
import UserStore from "../../stores/UserStore";
import AuthService from "../../auth.service";
import { runInAction } from "mobx";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  async function login() {
    runInAction(() => {
      UserStore.loading = true;
    });
    let response = await AuthService.login(email, password);
    if (response !== null) {
      runInAction(() => {
        let auth = AuthService.getCurrentUser();
        UserStore.token = auth.token;
        UserStore.user_id = auth.user_id;
        UserStore.role = auth.role;
        UserStore.loading = false;
      });
    } else {
      runInAction(() => {
        UserStore.loading = false;
      });
    }
  }
  return (
    <div className="login-main-content">
      <main>
        <div className="login-form" data-testid="login-form">
          <div className="login-form-header">
            <h1>Log in</h1>
          </div>
          <div className="login-form-textbox">
            <TextField
              label="Email"
              variant="standard"
              fullWidth="true"
              value={email}
              onInput={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="login-form-textbox">
            <TextField
              label="Password"
              type="password"
              variant="standard"
              fullWidth="true"
              value={password}
              onInput={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="login-form-button">
            <Button onClick={login}>Login</Button>
          </div>
          <div className="login-form-footer">
            <small>Don't have an account?</small>
            <Link to="/register">
              <small>Register</small>
            </Link>
          </div>
        </div>
      </main>
    </div>
  );
}

export default LoginForm;
