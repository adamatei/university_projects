import React from "react";
import { useState, useEffect } from "react";
import "./LoginComponent.css";
import Navbar from "./Navbar";
import "./MainPageComponent.css";
import "./Profile.css";
import { useHistory } from "react-router-dom";
import job from "../Images/profile-job.png";
import study from "../Images/profile-study.png";
import birth from "../Images/birthday.png";
import home from "../Images/profile-home.png";
import origin from "../Images/profile-location.png";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import ProfileService from "../Services/ProfileService";
import { BsEyeFill, BsEyeSlashFill } from "react-icons/bs";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

function EditPage() {
  let history = useHistory();
  const [intro, setIntro] = useState(
    "This is the old intro but just a part of it :)"
  );
  const [jobTitle, setJobTitle] = useState("");
  const [profile, setProfile] = useState("");

  const [birthday, setBirthday] = useState(new Date());

  const [education, setEducation] = useState("");

  const [livingCountry, setLivingCountry] = useState("");

  const [isPublic, setIsPublic] = useState(true);

  const [originCity, setOriginCity] = useState("");

  const [originCountry, setOriginCountry] = useState("");

  const [livingCity, setLivingCity] = useState("");

  const handleIntro = (event) => {
    setIntro(event.target.value);
  };

  const handleJobTitle = (event) => {
    setJobTitle(event.target.value);
  };

  const handleEducation = (event) => {
    setEducation(event.target.value);
  };

  const handleLivingCountry = (event) => {
    setLivingCountry(event.target.value);
  };

  const handleLivingCity = (event) => {
    setLivingCity(event.target.value);
  };

  const handleOriginCountry = (event) => {
    setOriginCountry(event.target.value);
  };

  const handleOriginCity = (event) => {
    setOriginCity(event.target.value);
  };

  const handleIsPublic = () => {
    let value = !isPublic;
    setIsPublic(value);
  };

  const update = (e) => {
    e.preventDefault();
    let body = {
      id: profile.id,
      username: localStorage.getItem("username"),
      intro: intro,
      role: profile.role,
      job: jobTitle,
      education: education,
      isPublic: isPublic,
      isBlocked: profile.isBlocked,
      livingCity: livingCity,
      livingCountry: livingCountry,
      originCityy: originCity,
      originCountry: originCountry,
      birthday: birthday,
    };
    console.log(body);

    try {
      ProfileService.updateProfile(body)
        .then(() => {
          history.push("/profile/" + localStorage.getItem("username"));
          window.location.reload(false);
        })
        .catch((error) => {
          console.log(error);
          confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className="custom-ui">
                  <h1>Something went wrong!</h1>
                  <p>Please try again</p>
                  <button className="button-1" onClick={onClose}>
                    Close
                  </button>
                </div>
              );
            },
          });
        });
    } catch (error) {
      confirmAlert({
        customUI: ({ onClose }) => {
          return (
            <div className="custom-ui">
              <h1>Something went wrong!</h1>
              <p>Please try again</p>
              <button className="button-1" onClick={onClose}>
                Close
              </button>
            </div>
          );
        },
      });
    }
  };

  useEffect(() => {
    ProfileService.getProfile(localStorage.getItem("username")).then((res) => {
      setProfile(res.data);
      setIntro(res.data.intro);
      if (new Date(res.data.birthday).getFullYear() == "0001") {
        setBirthday(null);
        //console.log("Date has year 0001");
      } else {
        setBirthday(new Date(res.data.birthday));
        //console.log("Date has value");
      }
      setEducation(res.data.education);
      setIsPublic(res.data.isPublic);
      setJobTitle(res.data.job);
      setLivingCity(res.data.livingCity);
      setLivingCountry(res.data.livingCountry);
      setOriginCity(res.data.originCityy);
      setOriginCountry(res.data.originCountry);
      console.log(res.data.isPublic);
    });
  }, []);

  return (
    <div>
      <Navbar />
      <div className="container">
        <div className="box">
          <form className="login-form">
            <div className="field">
              <h3>Intro</h3>
              <input
                id="intro"
                type="text"
                value={intro}
                onChange={handleIntro}
              />
              <label for="intro">Intro</label>
            </div>
          </form>
        </div>
        <div className="box">
          <div className="heading">
            <picture id="login_logo"></picture>
          </div>

          <form className="login-form">
            <div className="field">
              <h3>
                <img src={job} width="20px" height="20px" /> Job Title
              </h3>

              <input
                id="job"
                type="name"
                value={jobTitle}
                onChange={handleJobTitle}
              />
              <label for="job">Job</label>
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={birth} width="20px" height="20px" /> Birthday
              </h3>

              <DatePicker
                selected={birthday}
                value={birthday}
                onChange={setBirthday}
                className="form-control"
                name="dateTime"
                dateFormat="yyyy/MM/dd"
              />
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={study} width="20px" height="20px" /> Education
              </h3>

              <input
                id="education"
                type="text"
                value={education}
                onChange={handleEducation}
              />
              <label for="education">Education</label>
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={home} width="20px" height="20px" /> Living Country
              </h3>

              <input
                id="livingCountry"
                type="text"
                value={livingCountry}
                onChange={handleLivingCountry}
              />
              <label for="livingCountry">Living Country</label>
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={home} width="20px" height="20px" /> Living City
              </h3>

              <input
                id="livingCity"
                type="text"
                value={livingCity}
                onChange={handleLivingCity}
              />
              <label for="livingCity">Living City</label>
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={origin} width="20px" height="20px" /> Origin Country
              </h3>

              <input
                id="originCountry"
                type="text"
                value={originCountry}
                onChange={handleOriginCountry}
              />
              <label for="livingCountry">Origin Country</label>
            </div>
            <br />
            <div className="field">
              <h3>
                <img src={origin} width="20px" height="20px" /> Origin City
              </h3>

              <input
                id="originCity"
                type="text"
                value={originCity}
                onChange={handleOriginCity}
              />
              <label for="originCity">Origin City</label>
            </div>

            <div className="separator">
              <div className="line"></div>
              <p>Privacy</p>
              <div className="line"></div>
            </div>
            <div className="other">
              <button className="fb-login-btn" type="button">
                {"    "}
                {isPublic == true && (
                  <BsEyeFill size="17px" onClick={handleIsPublic} />
                )}
                {isPublic == false && (
                  <BsEyeSlashFill size="17px" onClick={handleIsPublic} />
                )}
                {"    "}
                {isPublic == true && <span className="">Public Account</span>}
                {isPublic == false && <span className="">Private Account</span>}
              </button>
              {isPublic == true && (
                <a className="forgot-password">
                  Everyone can view your account
                </a>
              )}
              {isPublic == false && (
                <a className="forgot-password">
                  Only people following you can view your account
                </a>
              )}
            </div>
            <br />
            <br />
            <button className="login-button" title="login" onClick={update}>
              Update
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default EditPage;
