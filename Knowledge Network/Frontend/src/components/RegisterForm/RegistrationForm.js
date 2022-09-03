import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import { useState } from "react";
import "./RegistrationForm.css";
import { useHistory } from "react-router-dom";
import UserStore from "../../stores/UserStore";
import { runInAction } from "mobx";

function RegistrationForm() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [job, setJob] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const [passwordsMatch, setMatch] = useState(true);
  let history = useHistory();

  function checkPassword(value) {
    setPassword(value);
    setMatch(value === repeatPassword);
  }

  function checkRepeatPassword(value) {
    setRepeatPassword(value);
    setMatch(password === value);
  }

  async function register() {
    runInAction(() => {
      UserStore.loading = true;
    });
    if (passwordsMatch) {
      const requestOptions = {
        method: "POST",
        mode: "cors",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          first_name: firstName,
          last_name: lastName,
          email: email,
          job: job,
          password: password,
        }),
      };
      const response = await fetch(
        "http://localhost:5000/users/",
        requestOptions
      );
      if (response.ok) {
        runInAction(() => {
          UserStore.loading = false;
        });
        history.push("/login");
      }
    } else {
      runInAction(() => {
        UserStore.loading = false;
      });
    }
  }

  return (
    <div className="register-main-content">
      <main>
        <div className="register-form" data-testid="register-form">
          <div className="register-form-header">
            <h1>Register</h1>
          </div>
          <div className="register-form-textboxes">
            <div className="register-form-textbox-row">
              <TextField
                label="First Name"
                variant="standard"
                fullWidth={true}
                value={firstName}
                onInput={(e) => setFirstName(e.target.value)}
              />
              <TextField
                label="Last Name"
                variant="standard"
                fullWidth={true}
                value={lastName}
                onInput={(e) => setLastName(e.target.value)}
              />
            </div>
            <br />
            <div className="register-form-textbox-row">
              <TextField
                label="Email"
                variant="standard"
                fullWidth={true}
                value={email}
                onInput={(e) => setEmail(e.target.value)}
              />
              <TextField
                label="Job"
                variant="standard"
                fullWidth={true}
                value={job}
                onInput={(e) => setJob(e.target.value)}
              />
            </div>
            <br />
            <div className="register-form-textbox-row">
              <TextField
                label="Password"
                variant="standard"
                type="password"
                fullWidth={true}
                value={password}
                onInput={(e) => checkPassword(e.target.value)}
              />
              <TextField
                label="Repeat Password"
                variant="standard"
                type="password"
                fullWidth={true}
                value={repeatPassword}
                onInput={(e) => checkRepeatPassword(e.target.value)}
              />
              <div className="register-form-warning-container">
                {!passwordsMatch && (
                  <small
                    className="register-form-warning"
                    data-testid="warning-text-register"
                  >
                    Passwords don't match!
                  </small>
                )}
              </div>
            </div>
          </div>
          <div className="register-form-button">
            <Button onClick={register}>Register</Button>
          </div>
          <div className="register-form-footer">
            <small>Already have an account?</small>
            <Link to="/login">
              <small>Log in</small>
            </Link>
          </div>
        </div>
      </main>
    </div>
  );
}

export default RegistrationForm;
