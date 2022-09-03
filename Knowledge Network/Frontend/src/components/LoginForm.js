import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { useHistory } from "react-router-dom";
import { useState } from "react";
import UserStore from "../stores/UserStore";
import AuthService from "../auth.service";
import { runInAction } from "mobx";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  let history = useHistory();
  function renderRegister() {
    history.push("/register");
  }

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
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Grid padding={20}>
        <div className="loginForm">
          <Typography>Login</Typography>
          <TextField
            label="Email"
            value={email}
            onInput={(e) => setEmail(e.target.value)}
          />
          <TextField
            label="Password"
            type="password"
            value={password}
            onInput={(e) => setPassword(e.target.value)}
          />
          <Button onClick={login}>Log in</Button>
          <div
            style={{ display: "flex", marginBottom: "30px", marginTop: "30px" }}
          >
            <p> Don't have an account?</p>
            <button className="button" onClick={() => renderRegister()}>
              Register
            </button>
          </div>
        </div>
      </Grid>
    </div>
  );
}

export default LoginForm;
