import React from "react";
import "./MainPageComponent.css";
import "./Profile.css";
import Avatar from "react-user-avatar";
import job from "../Images/profile-job.png";
import study from "../Images/profile-study.png";
import birthday from "../Images/birthday.png";
import home from "../Images/profile-home.png";
import origin from "../Images/profile-location.png";
import moment from "moment";
import ProfileService from "../Services/ProfileService";

class ProfileDetails extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchWord: "",
      pings: [],
      followers: [],
      following: this.props.following,
      trends: [],
      profile: this.props.profile,
    };
  }
  componentDidMount() {
    ProfileService.getProfile(this.state.profile.username).then((res) => {
      this.setState({ profile: res.data });
      console.log(res.data);
    });
  }

  render() {
    return (
      <div>
        <script type="text/javascript" src="myscript.js?ver=12345"></script>
        <div className="profile-intro">
          <h3>Intro</h3>
          <p className="intro-text">{this.state.profile.intro}</p>
          <hr />
          <ul>
            <li>
              <img src={job} />
              Works as {this.state.profile.job}
            </li>
            <li>
              <img src={study} />
              Studied at {this.state.profile.education}
            </li>
            <li>
              <img src={birthday} />
              Born in {moment(this.state.profile.birthday).format("DD")}
              {"/"}
              {moment(this.state.profile.birthday).format("MM")}
              {"/"}
              {moment(this.state.profile.birthday).format("YYYY")}
            </li>
            <li>
              <img src={home} />
              Lives in {this.state.profile.livingCity},{" "}
              {this.state.profile.livingCountry}
            </li>
            <li>
              <img src={origin} />
              From {this.state.profile.originCityy},{" "}
              {this.state.profile.originCountry}
            </li>
          </ul>
        </div>

        <div className="profile-intro">
          <div className="title-box">
            <h3>Requests</h3>
            <a href="#">All requests</a>
          </div>
          <div className="photo-box">
            <div>
              <img src="" />
            </div>
          </div>
        </div>
        <div className="profile-intro">
          <div className="title-box">
            <h3>Friends</h3>
            <a href="#">All Friends</a>
          </div>
          <div className="photo-box">
            {this.state.following.slice(0, 6).map((f) => (
              <Avatar size="40" name={f} src="" className="fr-image" />
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default ProfileDetails;
