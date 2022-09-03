import React from "react";
import "./Profile.css";
import "react-confirm-alert/src/react-confirm-alert.css";
import { useState, useEffect } from "react";
import CardMedia from "@mui/material/CardMedia";
import Labels from "./Labels";
import AuthService from "../../auth.service";
import { getLabelById, getUserById, updateUser} from "../../APIRequests";
import Alert from "@mui/material/Alert";
import Collapse from "@mui/material/Collapse";

function Profile() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [job, setJob] = useState("");
  const [email, setEmail] = useState("");
  const [labels, setLabels] = useState([]);
  const [user, setUser] = useState(null);

  const [open, setOpen] = useState(false);
  const [updateText, setUpdateText] = useState("");

  let auth = AuthService.getCurrentUser();

  function SetUserFields(object) {
    setFirstName(object.first_name);
    setLastName(object.last_name);
    setJob(object.job_title);
    setEmail(object.email);
  }

  useEffect(() => {
    async function getUser() {
      let response = await getUserById(auth.user_id);
      let data = response.data;
      setUser(data);
      SetUserFields(data);
      getLabels(data.topology_labels);
    }

    getUser();
  }, [auth.user_id]);

  const getLabels = (listOfLabelsID) => {
    listOfLabelsID.forEach((label) => {
      getLabelById(label)
        .then((response) => {
          setLabels((oldLabels) => [...oldLabels, response.data]);
        })
        .catch((error) => console.log(`ERROR : ${error}`));
    });
  };

  const handleFirstName = (event) => {
    setFirstName(event.target.value);
  };

  const handleLastName = (event) => {
    setLastName(event.target.value);
  };

  const handleJob = (event) => {
    setJob(event.target.value);
  };

  const handleEmail = (event) => {
    setEmail(event.target.value);
  };

  async function updateInfo() {
    let newObject = {
      first_name: firstName,
      last_name: lastName,
      job_title: job,
      role: user.role,
      email: email,
      password: user.password,
      topology_labels: user.topology_labels,
    };

    let response = await updateUser(auth.user_id, newObject);
    let status = response.status;
    if (status === 200) {
      setUpdateText("User successfully edited");
      setOpen(true);
    } else {
      setUpdateText("Error while editing user");
      setOpen(true);
    }
  }

  return (
    <main>
      <div className="background">
        <div className="containerOne">
          <div className="containerOneBackground"></div>
          <div className="pictureContainer">
            <CardMedia
              component="img"
              height="100"
              className="userImage"
              src="https://w7.pngwing.com/pngs/340/956/png-transparent-profile-user-icon-computer-icons-user-profile-head-ico-miscellaneous-black-desktop-wallpaper.png"
              alt="userImage not found"
              title="lalala"
            />
          </div>
          <Collapse in={open}>
              <Alert>{updateText}</Alert>
            </Collapse>
          <div className="userInfo">
            <h5>First Name</h5>
            <div className="centerText">
              <input
                type="text"
                className="inputText"
                value={firstName}
                onChange={handleFirstName}
              />
            </div>
          </div>
          <div className="userInfo">
            <h5>Last Name</h5>
            <div className="centerText">
              <input
                type="text"
                className="inputText"
                value={lastName}
                onChange={handleLastName}
              />
            </div>
          </div>

          <div className="userInfo">
            <h5>Job</h5>
            <div className="centerText">
              <input
                type="text"
                className="inputText"
                value={job}
                onChange={handleJob}
              />
            </div>
          </div>
          <div className="userInfo">
            <h5>Email</h5>
            <div className="centerText">
              <input
                type="text"
                className="inputText"
                value={email}
                onChange={handleEmail}
              />
            </div>
          </div>
          <div className="userInfo">
            <div className="centerText">
              <button className="btn" onClick={updateInfo}>
                Update
              </button>
            </div>
          </div>
        </div>

        <div className="containerTwo">
          <Labels
            user={user}
            labels={labels}
          />
        </div>
      </div>
    </main>
  );
}
export default Profile;
