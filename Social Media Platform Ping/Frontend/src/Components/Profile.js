import React from "react";
import "./MainPageComponent.css";
import Navbar from "./Navbar";
import "./Profile.css";
import Avatar from "react-user-avatar";
import SquareAvatar from "react-avatar";
import cover from "../cover.png";
import addFriends from "../Images/add-friends.png";
import message from "../Images/message.png";
import more from "../Images/more.png";
import arrow from "../Images/arrow.png";
import feeling from "../Images/feeling.png";
import WritePostComponent from "./WritePostComponent";
import PostComponent from "./PostComponent";
import ProfileDetails from "./ProfileDetails";
import ProfileService from "../Services/ProfileService";
import PostService from "../Services/PostService";
import FollowService from "../Services/FollowService";
import { BsEyeSlashFill } from "react-icons/bs";
import job from "../Images/profile-job.png";
import study from "../Images/profile-study.png";
import birthday from "../Images/birthday.png";
import home from "../Images/profile-home.png";
import origin from "../Images/profile-location.png";
import moment from "moment";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class Profile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchWord: "",
      profile: "",
      posts: [],
      requests: [],
      followers: [],
      following: [],
      looged_following: [],
      username: this.props.match.params.username,
    };
    this.edit = this.edit.bind(this);
    this.isFollowing = this.isFollowing.bind(this);
    this.createRequest = this.createRequest.bind(this);
    this.acceptRequest = this.acceptRequest.bind(this);
    this.declineRequest = this.declineRequest.bind(this);
    this.delete = this.delete.bind(this);
  }

  componentDidMount() {
    //retrieve user's followings
    FollowService.getFolowing(this.state.username).then((res) => {
      this.setState({ following: res.data });
    });

    //retrieve logged in user's followings
    FollowService.getFolowing(localStorage.getItem("username")).then((res) => {
      this.setState({ looged_following: res.data });
    });

    //retrieve user's followings
    FollowService.getFollower(this.state.username).then((res) => {
      this.setState({ followers: res.data });
    });

    //retrieve the incoming requests
    FollowService.getRequests(localStorage.getItem("username")).then((res) => {
      this.setState({ requests: res.data });
    });

    //retrieve user's posts
    PostService.getPostsByUsername(this.state.username).then((res) => {
      this.setState({ posts: res.data });
    });

    //retrieve user's profile
    ProfileService.getProfile(this.state.username).then((res) => {
      this.setState({ profile: res.data });
      console.log(res.data);
    });
  }

  delete(e) {
    e.preventDefault();
    ProfileService.deleteProfile(this.state.profile.id)
      .then(() => {
        console.log("Profile deleted with id " + this.state.profile.id);
        this.props.history.push("/ping.com");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  edit(e) {
    e.preventDefault();
    this.props.history.push("/edit/" + this.state.username);
    // window.location.reload(false);
  }

  isFollowing() {
    if (this.state.looged_following.some((v) => v == this.state.username)) {
      return true;
    } else {
      return false;
    }
  }

  acceptRequest(r) {
    console.log("Request accepted!");
    let body = {
      id: r.id,
      followed: r.followed,
      follower: r.follower,
      isAccepted: true,
      isPending: false,
    };
    FollowService.updateRequest(body).then((res) => {
      console.log("Request accepted!");
      let temp = this.state.requests.filter((req) => req.id != r.id);
      this.setState({ requests: temp });
      confirmAlert({
        customUI: ({ onClose }) => {
          return (
            <div className="custom-ui">
              <h1>{r.follower} started following you!</h1>
              <p>Request successfully accepted!</p>
              <button className="button-1" onClick={onClose}>
                Close
              </button>
            </div>
          );
        },
      });
    });
  }

  declineRequest(r) {
    let body = {
      id: r.id,
      followed: r.followed,
      follower: r.follower,
      isAccepted: false,
      isPending: false,
    };
    FollowService.updateRequest(body).then((res) => {
      console.log("Request declined!");
      let temp = this.state.requests.filter((req) => req.id != r.id);
      this.setState({ requests: temp });
      confirmAlert({
        customUI: ({ onClose }) => {
          return (
            <div className="custom-ui">
              <h1>{r.follower} is not following you!</h1>
              <p>Request successfully declined!</p>
              <button className="button-1" onClick={onClose}>
                Close
              </button>
            </div>
          );
        },
      });
    });
  }

  createRequest() {
    let request = {
      follower: localStorage.getItem("username"),
      followed: this.state.username,
      isAccepted: false,
      isPending: true,
    };
    try {
      FollowService.createRequest(request).then((res) => {
        console.log(res.data);
        console.log("Created requests");
        confirmAlert({
          customUI: ({ onClose }) => {
            return (
              <div className="custom-ui">
                <h1>Request successfully sent!</h1>
                <p>Wait for a response</p>
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
  }

  render() {
    return (
      <div id="main_page_all">
        <body id="main_page_body">
          <Navbar />
          <script type="text/javascript" src="myscript.js?ver=12345"></script>
          <div className="profile-container">
            <img className="cover-img" src={cover} />
            <div className="profile-details">
              <div className="pd-left">
                <div className="pd-row">
                  <Avatar
                    size="100"
                    name={this.state.username}
                    src=""
                    className="pd-image"
                  />
                  <div className="pd-overview">
                    <h3>{this.state.username}</h3>
                    <p>{this.state.following.length} Friends - following</p>
                    {this.state.following.slice(0, 4).map((following) => (
                      <SquareAvatar
                        size="25"
                        name={following}
                        src=""
                        className="fr-image"
                      />
                    ))}
                  </div>
                </div>
              </div>
              {this.state.username == localStorage.getItem("username") && (
                <div className="pd-right">
                  <button type="button">
                    <img src={arrow} />
                    {this.state.profile.isPublic == true && <>Public</>}
                    {this.state.profile.isPublic == false && <>Private</>}
                  </button>
                  <button type="button" onClick={this.edit}>
                    <img src={message} />
                    Edit
                  </button>
                  <br />
                  <a href="" id="dropdown">
                    <img src={more} />
                    <div class="dropdown-content">
                      <p onClick={this.delete}>Delete Account</p>
                    </div>
                  </a>
                </div>
              )}
              {this.state.username != localStorage.getItem("username") &&
                this.isFollowing() == true && (
                  <div className="pd-right">
                    <button type="button">
                      <img src={arrow} />
                      {this.state.profile.isPublic == true && <>Public</>}
                      {this.state.profile.isPublic == false && <>Private</>}
                    </button>
                    <button type="button">
                      <img src={feeling} />
                      Friends
                    </button>
                    <br />
                    <a href="">
                      <img src={more} />
                    </a>
                  </div>
                )}
              {this.state.username != localStorage.getItem("username") &&
                this.isFollowing() == false && (
                  <div className="pd-right">
                    <button type="button">
                      <img src={arrow} />
                      {this.state.profile.isPublic == true && <>Public</>}
                      {this.state.profile.isPublic == false && <>Private</>}
                    </button>
                    <button type="button" onClick={this.createRequest}>
                      <img src={addFriends} />
                      Follow
                    </button>
                    <br />
                    <a href="">
                      <img src={more} />
                    </a>
                  </div>
                )}
            </div>
            {(this.state.profile.isPublic == true ||
              this.state.looged_following.filter(
                (p) => p == this.state.username
              ).length > 0 ||
              this.state.username == localStorage.getItem("username")) && (
              <div className="profile-info">
                <div className="info-col">
                  {/*<ProfileDetails
                    following={this.state.following}
                    profile={this.state.profile}
              />*/}
                  <div>
                    <script
                      type="text/javascript"
                      src="myscript.js?ver=12345"
                    ></script>
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
                          Born in{" "}
                          {moment(this.state.profile.birthday).format("DD")}
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
                    {this.state.username ==
                      localStorage.getItem("username") && (
                      <div className="profile-intro">
                        <div className="title-box">
                          <h3>Requests</h3>
                          <br />
                          <a href="">All requests</a>
                        </div>
                        {this.state.requests.length == 0 && (
                          <p text-align={"centre"}>No requests</p>
                        )}
                        {this.state.requests.map((r) => (
                          <div className="request" key={r.id}>
                            <div className="info">
                              <Avatar
                                size="40"
                                name={r.follower}
                                className="profile-photo"
                              />

                              <div>
                                <h5>{r.follower}</h5>
                                <p className="text-muted"> wants to connect</p>
                              </div>
                            </div>
                            <div className="action">
                              <button
                                onClick={() => this.acceptRequest(r)}
                                className="buton buton-primary"
                              >
                                Accept
                              </button>
                              <button
                                onClick={() => this.declineRequest(r)}
                                className="buton"
                              >
                                Decline
                              </button>
                            </div>
                          </div>
                        ))}
                        <br />
                      </div>
                    )}
                    <div className="profile-intro">
                      <div className="title-box">
                        <h3>Friends</h3>
                        <a href="#">All Friends</a>
                      </div>
                      <div className="photo-box">
                        {this.state.following.slice(0, 6).map((f) => (
                          <Avatar
                            size="40"
                            name={f}
                            src=""
                            className="fr-image"
                          />
                        ))}
                      </div>
                    </div>
                  </div>
                </div>
                {this.state.username == localStorage.getItem("username") && (
                  <div className="post-col">
                    <WritePostComponent />
                    {this.state.posts.map((post) => (
                      <PostComponent id={post.id} post={post} key={post.id} />
                    ))}
                    {this.state.posts.length == 0 && (
                      <>
                        <br /> <br />
                        <button type="button" className="load-more-btn">
                          No posts yet
                        </button>
                      </>
                    )}
                  </div>
                )}
                {this.state.username != localStorage.getItem("username") && (
                  <div className="post-col">
                    {this.state.posts.map((post) => (
                      <PostComponent id={post.id} post={post} />
                    ))}
                    {this.state.posts.length == 0 && (
                      <>
                        <br /> <br />
                        <button type="button" className="load-more-btn">
                          No posts yet
                        </button>
                      </>
                    )}
                  </div>
                )}
              </div>
            )}
            {this.state.profile.isPublic == false &&
              this.isFollowing() == false &&
              this.state.username != localStorage.getItem("username") && (
                <>
                  <br /> <br />
                  <button type="button" className="load-more-btn">
                    <BsEyeSlashFill /> Private
                  </button>
                  <br /> <br />
                  <br /> <br />
                </>
              )}
          </div>
        </body>
      </div>
    );
  }
}

export default Profile;
