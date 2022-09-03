import AppBar from "@mui/material/AppBar";
import Button from "@mui/material/Button";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import { useHistory } from "react-router-dom";
import AuthService from "../auth.service";
import React, { useState, useEffect } from "react";
import { getUserById } from "../APIRequests";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import IconButton from "@mui/material/IconButton";
import DashboardIcon from "@mui/icons-material/Dashboard";
import InfoIcon from "@mui/icons-material/Info";
import HelpIcon from "@mui/icons-material/Help";
import Tooltip from "@mui/material/Tooltip";

function NavigationBar(props) {
  const [user, setUser] = useState({});
  const [anchorEl, setAnchorEl] = React.useState(null);
  let history = useHistory();

  useEffect(() => {
    const fetchUser = async () => {
      return await getUserById(props.id);
    };
    setUser(fetchUser());
  }, [props.id]);

  function renderInformation() {
    history.push("/information");
  }

  function renderDashboard() {
    if (window.location.pathname !== "/home") {
      history.push("/home");
    }
  }

  function renderAboutUs() {
    history.push("/about");
  }

  function renderLogin() {
    history.push("/login");
  }

  function renderAdmin() {
    history.push("/admin");
    setAnchorEl(null);
  }

  function renderEditGraph() {
    history.push("/graph-edit");
    setAnchorEl(null);
  }

  function renderProfile() {
    history.push("/profile");
    setAnchorEl(null);
  }

  function renderApproval() {
    history.push("/data");
    setAnchorEl(null);
  }
    
  function renderSubmitDataForm() {
    history.push("/submitdata");
    setAnchorEl(null);
  }

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  function logout() {
    AuthService.logout();
    window.location.reload(false);
    setAnchorEl(null);
  }

  function userName() {
    return `${user.first_name} ${user.last_name}`;
  }

  function renderButtons(role) {
    if (role === "") {
      return (
        <Toolbar>
          <Typography variant="h5" component="div" align="left">
            Flooid
          </Typography>
          <Box sx={{ flexGrow: 0 }}>
            <Button color="inherit" onClick={() => renderLogin()}>
              Login
            </Button>
          </Box>
        </Toolbar>
      );
    } else {
      return (
        <Toolbar>
          <Typography variant="h5" component="div" align="left">
            Flooid
          </Typography>
          <Box sx={{ flexGrow: 1 }}>
            <Tooltip title="Dashboard">
              <IconButton color="inherit" onClick={() => renderDashboard()}>
                <DashboardIcon />
              </IconButton>
            </Tooltip>
          </Box>
          <Tooltip title="Information">
            <IconButton color="inherit" onClick={() => renderInformation()}>
              <InfoIcon />
            </IconButton>
          </Tooltip>
          <Tooltip title="About Us">
            <IconButton color="inherit" onClick={() => renderAboutUs()}>
              <HelpIcon />
            </IconButton>
          </Tooltip>
          <Tooltip title={userName()}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleMenu}
              color="inherit"
            >
              <AccountCircle />
            </IconButton>
          </Tooltip>
          <Menu
            id="menu-appbar"
            anchorEl={anchorEl}
            anchorOrigin={{
              vertical: "bottom",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            open={Boolean(anchorEl)}
            onClose={handleClose}
          >
          <MenuItem onClick={renderAdmin}>Admin Panel</MenuItem>
          <MenuItem onClick={renderEditGraph}>Edit Graph Data</MenuItem>
          <MenuItem onClick={renderProfile}>Profile</MenuItem>
          <MenuItem onClick={renderApproval}>Approve</MenuItem>
          <MenuItem onClick={renderSubmitDataForm}>Add data to graph</MenuItem>
          <MenuItem onClick={logout}>Logout</MenuItem>
        </Menu>

      </Toolbar>
      )
    }
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar
        className="navbar"
        position="static"
        style={{ background: "#282c34" }}
      >
        {renderButtons(props.role)}
      </AppBar>
    </Box>
  );
}

export default NavigationBar;
