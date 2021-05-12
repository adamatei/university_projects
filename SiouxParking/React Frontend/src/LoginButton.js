import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

const LoginButton = () => {
  const { loginWithRedirect } = useAuth0();
  const { isAuthenticated } = useAuth0();
  return (
    !isAuthenticated && (
      <div>
        <header>
          <h1>Please, login to get access </h1>
        </header>
        <button className="login-button " onClick={() => loginWithRedirect()}>
          Log in
        </button>
      </div>
    )
  );
};
export default LoginButton;
