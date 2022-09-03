import React from "react";
import "./MainPageComponent.css";
import shortcut1 from "../Shortcuts/shortcut-1.png";
import shortcut2 from "../Shortcuts/shortcut-2.png";
import shortcut3 from "../Shortcuts/shortcut-3.png";
import shortcut4 from "../Shortcuts/shortcut-4.png";
import Avatar from "react-user-avatar";
import Image from "react-random-image";
import {
  BsFacebook,
  BsInstagram,
  BsTwitter,
  BsSnapchat,
  BsLinkedin,
  BsFillGiftFill,
} from "react-icons/bs";
import Navbar from "./Navbar";
import WritePostComponent from "./WritePostComponent";
import PostComponent from "./PostComponent";
import ProfileService from "../Services/ProfileService";
import FollowService from "../Services/FollowService";
import PostService from "../Services/PostService";
import moment from "moment";

class MainPageComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchWord: "",
      profile: "",
      posts: [],
      followers: [],
      following: [],
      trends: [],
      birthdays: [],
      username: this.props.match.params.username,
      token: localStorage.getItem("token"),
    };
    this.getPosts = this.getPosts.bind(this);
    this.getFollowingBirthdays = this.getFollowingBirthdays.bind(this);
  }

  componentDidMount() {
    //retrieve user's profile
    ProfileService.getProfile(this.state.username).then((res) => {
      this.setState({ profile: res.data });
    });

    //retrieve user's followings' posts
    FollowService.getFolowing(this.state.username).then((res) => {
      this.setState({ following: res.data });

      res.data.map((f) =>
        PostService.getPostsByUsername(f).then((res) => {
          res.data.map((p) => {
            let initial = this.state.posts.concat(p);
            this.setState({ posts: initial });
          });
        })
      );
      // this.setState({ posts: initial });
    });

    let profiles = [];
    ProfileService.getProfiles().then((res) => {
      res.data.map((p) => {
        if (this.state.following.includes(p.username)) {
          profiles.push(p);
        }
      });
    });
    this.setState({ birthdays: profiles });
  }

  getFollowingBirthdays() {
    let profiles = [];
    ProfileService.getProfiles().then((res) => {
      res.data.map((p) => {
        if (this.state.following.includes(p.username)) {
          profiles.push(p);
        }
      });
    });
    console.log(profiles);
    this.setState({ birthdays: profiles });
    return profiles;
  }

  getPosts() {
    let initial = [];
    this.state.following.map((f) =>
      PostService.getPostsByUsername(f).then((res) => {
        res.data.map((p) => {
          initial.push(p);
        });
      })
    );

    return initial;
  }

  render() {
    return (
      <div id="main_page_all">
        <body id="main_page_body">
          <Navbar />
          <script type="text/javascript" src="myscript.js?ver=12345"></script>
          <div className="main-page-container">
            <div className="left-sidebar">
              <div className="imp-links">
                <a href="https://www.facebook.com/login/">
                  <div id="social-icon">
                    <BsFacebook size="25" color="#3b5998" />{" "}
                  </div>
                  Facebook
                </a>
                <a href="https://www.instagram.com/">
                  <div id="social-icon">
                    <BsInstagram size="25" color="#bc2a8d" />
                  </div>
                  Instagram
                </a>
                <a href="https://twitter.com/i/flow/login">
                  {" "}
                  <div id="social-icon">
                    <BsTwitter size="25" color="#00acee" />
                  </div>
                  Twitter
                </a>
                <a href="https://www.snapchat.com/">
                  {" "}
                  <div id="social-icon">
                    <BsSnapchat size="25" color="	#FFFC00" />
                  </div>
                  Snapchat
                </a>
                <a href="https://www.linkedin.com/">
                  {" "}
                  <div id="social-icon">
                    <BsLinkedin size="25" color="	#0077b5" />
                  </div>
                  LinkeIn
                </a>
                <a>Ping the whole world!</a>
              </div>
              <div className="shortcut-links">
                <p>Your Shortcuts </p>
                <a href="">
                  <img src={shortcut1} />
                  {this.state.posts.length} Pings
                </a>
                <a href="">
                  <img src={shortcut2} />
                  {this.state.trends.length} Trends
                </a>
                <a href="">
                  <img src={shortcut3} />
                  {this.state.followers.length} Followers
                </a>
                <a href="">
                  <img src={shortcut4} />
                  {this.state.following.length} Following
                </a>
              </div>
            </div>
            <div className="main-content">
              <WritePostComponent />
              {console.log(this.state.posts)}
              {this.state.posts.map((post) => (
                <PostComponent id={post.id} post={post} key={post.id} />
              ))}
              <br />
              {this.state.posts.length <= 5 && (
                <>
                  <button type="button" className="load-more-btn">
                    No more posts yet
                  </button>
                </>
              )}
              {this.state.posts.length > 5 && (
                <button type="button" className="load-more-btn">
                  Load More
                </button>
              )}
            </div>
            <div className="right-sidebar">
              <div className="sidebar-title">
                <h4>Events</h4>
                <a href="#">Upcoming</a>
              </div>

              {this.state.birthdays.length > 0 ? (
                this.state.birthdays.slice(0, 2).map((fb) => (
                  <div className="event" key={fb.id}>
                    <div className="left-event">
                      <h3>{moment(fb.birthday).format("DD")}</h3>
                      <span>{moment(fb.birthday).format("MMMM")}</span>
                    </div>
                    <div className="right-event">
                      <h4>{fb.username}</h4>
                      <p>
                        <BsFillGiftFill /> Reminder
                      </p>
                      <a href="">Birthday</a>
                    </div>
                  </div>
                ))
              ) : (
                <div className="event"> No friends added</div>
              )}

              <div className="sidebar-title">
                <h4>Advertisement</h4>
                <a href="#">Close</a>
              </div>
              <div className="sidebar-ads">
                <Image width={400} height={200} />
              </div>
              <div className="sidebar-title">
                <h4>Currently Following</h4>
                <a href="#">Hide List</a>
              </div>
              {this.state.following.slice(0, 3).map((following) => (
                <div className="online-list">
                  <div className="online-follow">
                    <Avatar size="50" name={following} src="" />
                  </div>
                  <p>{following}</p>
                </div>
              ))}
              {this.state.following.length < 1 && (
                <div className="online-list">
                  <div className="online-follow"></div>
                  <p>You are not following anyone at the moment</p>
                </div>
              )}
            </div>
          </div>
        </body>
        <script></script>
      </div>
    );
  }
}

export default MainPageComponent;
