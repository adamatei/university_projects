import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
const LogoutButton = () => {
  const { logout } = useAuth0();
  const { isAuthenticated } = useAuth0();
  return (
    isAuthenticated && (
      <button className="logout" onClick={() => logout()}>
        <span>Log out </span>
      </button>
    )
  );
};
export default LogoutButton;
