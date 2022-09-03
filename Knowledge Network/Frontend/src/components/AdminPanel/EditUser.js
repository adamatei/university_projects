import React from "react";
import "./EditUser.css";
import "react-confirm-alert/src/react-confirm-alert.css";
import { useParams } from "react-router-dom";
import { getUserById, updateUser } from "../../APIRequests";
import { useState, useEffect } from "react";
import Alert from "@mui/material/Alert";
import Collapse from "@mui/material/Collapse";

function EditUser() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [job, setJob] = useState("");
  const [role, setRole] = useState("");
  const [email, setEmail] = useState("");
  const [user, setUser] = useState(null);

  const [open, setOpen] = useState(false);
  const [updateText, setUpdateText] = useState("");
  const { id } = useParams();

  function SetUserFields(object) {
    setFirstName(object.first_name);
    setLastName(object.last_name);
    setJob(object.job_title);
    setRole(object.role);
    setEmail(object.email);
  }

  useEffect(() => {
    async function getUser() {
      let response = await getUserById(id);
      let data = response.data;
      setUser(data);
      SetUserFields(data);
    }

    getUser();
  }, [id]);

  async function updateInfo() {
    let newObject = {
      first_name: firstName,
      last_name: lastName,
      job_title: job,
      role: role,
      email: email,
      password: user.password,
      topology_labels: user.topology_labels,
    };

    let response = await updateUser(id, newObject);
    let status = response.status;
    if (status === 200) {
      setUpdateText("User successfully edited");
      setOpen(true);
    } else {
      setUpdateText("Error while editing user");
      setOpen(true);
    }
  }

  const handleFirstName = (event) => {
    setFirstName(event.target.value);
  };

  const handleLastName = (event) => {
    setLastName(event.target.value);
  };

  const handleJob = (event) => {
    setJob(event.target.value);
  };

  const handleRole = (event) => {
    setRole(event.target.value);
  };

  const handleEmail = (event) => {
    setEmail(event.target.value);
  };

  return (
    <main>
      <div className="container">
        <div className="leftbox"></div>
        <div className="rightbox">
          <div className="profile tabShow">
            <br />
            <Collapse in={open}>
              <Alert>{updateText}</Alert>
            </Collapse>
            <h1 id="h1_edit">Personal Info</h1>
            <h2 className="h2_edit">First Name</h2>
            <input
              type="text"
              className="input"
              value={firstName}
              onChange={handleFirstName}
            />
            <h2 className="h2_edit">Last Name</h2>
            <input
              type="text"
              className="input"
              value={lastName}
              onChange={handleLastName}
            />
            <h2 className="h2_edit">Job</h2>
            <input
              type="text"
              className="input"
              value={job}
              onChange={handleJob}
            />
            <h2 className="h2_edit">Role</h2>
            <input
              type="text"
              className="input"
              value={role}
              onChange={handleRole}
            />
            <h2 className="h2_edit">Email</h2>
            <input
              type="text"
              className="input"
              value={email}
              onChange={handleEmail}
            />
            <button className="btn" onClick={updateInfo}>
              Update
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}
export default EditUser;
