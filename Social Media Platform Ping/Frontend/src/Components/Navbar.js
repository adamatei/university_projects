import React from "react";
import "./MainPageComponent.css";
import logo from "../logo_transp.png";
import feedback from "../Images/feedback.png";
import logout from "../Images/logout.png";
import arrow from "../Images/arrow.png";
import notification from "../Images/notification.png";
import inbox from "../Images/inbox.png";
import Avatar from "react-user-avatar";
import {
  BsFillPeopleFill,
  BsSearch,
  BsFillPersonLinesFill,
} from "react-icons/bs";
import Notifications from "react-notifications-menu";
import NotificationService from "../Services/NotificationService";
import ProfileService from "../Services/ProfileService";
import moment from "moment";

class Navbar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchWord: "",
      trends: [],
      notifications: [],
      users: [],
      data: [],
      role: "",
      profileUrl:
        "http://localhost:3000/profile/" + localStorage.getItem("username"),
      dashBoardUrl:
        "http://localhost:3000/dashboard/" + localStorage.getItem("username"),
    };
    this.handleSearch = this.handleSearch.bind(this);
    this.filter = this.filter.bind(this);
    this.buildProfileUrl = this.buildProfileUrl.bind(this);
  }

  handleSearch(event) {
    this.setState({
      searchWord: event.target.value,
    });
  }

  filter() {
    const keyword = this.state.searchWord;

    if (keyword !== "") {
      const results = this.state.users.filter((user) => {
        return user.username.toLowerCase().startsWith(keyword.toLowerCase());
      });
      return results;
    } else {
      return [];
    }
  }

  componentDidMount() {
    NotificationService.getNotificationsByUsername(
      localStorage.getItem("username")
    ).then((res) => {
      this.setState({ notification: res.data });
      let tempList = [];
      res.data.map((n) =>
        this.state.data.push({
          image:
            "https://icon-library.com/images/notifications-icon-png/notifications-icon-png-28.jpg",
          message: n.content + n.id,
          detailPage: "",
          receivedTime: moment(n.createdOn).format("DD/MM/yyyy HH:mm"),
        })
      );
    });
    //console.log(this.state.data);

    ProfileService.getProfiles().then((res) => {
      this.setState({
        users: res.data.filter(
          (item) => item.username !== localStorage.getItem("username")
        ),
      });

      //console.log(res.data);
    });

    ProfileService.getProfile(localStorage.getItem("username")).then((res) => {
      this.setState({
        role: res.data.role,
      });
    });
  }

  buildProfileUrl(username) {
    return "http://localhost:3000/profile/" + username;
  }

  render() {
    return (
      <nav>
        <div className="nav-left">
          <img className="logo" src={logo} id="logo_nav" />
          <ul>
            {this.state.role == "admin" && (
              <li>
                <a href="http://localhost:3000/admin">
                  <BsFillPeopleFill color="white" size="25px" />
                </a>
              </li>
            )}
            <li>
              <Notifications
                className="notification-icon"
                icon={notification}
                data={this.state.data}
                header={{
                  title: "Notifications",
                  option: {
                    text: "View All",
                    onClick: () => console.log("Clicked"),
                  },
                }}
                markAsRead={(data) => {
                  console.log(data);
                }}
              />
            </li>
            <li>
              <a href={this.state.dashBoardUrl}>
                <img className="notification-icon" src={inbox} />
              </a>
            </li>
          </ul>
        </div>
        <div className="nav-right">
          <div className="search-box">
            <div className="search-box-img">
              <BsSearch color="white" />
            </div>
            <input
              id="SearchInput"
              type="text"
              placeholder="Search for pings"
              value={this.state.searchWord}
              onChange={this.handleSearch}
            />
            <ul id="SearchResult" className="SearchResult">
              {this.filter().map((u) => (
                <li>
                  <BsFillPersonLinesFill color="#afa6a6" />
                  <a href={this.buildProfileUrl(u.username)}>
                    {u.username}
                  </a>{" "}
                </li>
              ))}
            </ul>
          </div>

          <div className="nav-user-icon">
            <div className="dropdown">
              <div className="nav-user-icon-img online">
                <Avatar
                  size="40"
                  name={localStorage.getItem("username")}
                  className="dropdown"
                  src=""
                />
              </div>

              <div className="settings-menu">
                <div className="settings-menu-inner">
                  <div className="user-profile">
                    <div className="user-profile-img">
                      <Avatar
                        size="40"
                        name={localStorage.getItem("username")}
                        src=""
                      />
                    </div>

                    <div>
                      <p>{localStorage.getItem("username")}</p>
                      <a href={this.state.profileUrl}>See your profile</a>
                      <p></p>
                    </div>
                  </div>
                  <hr></hr>
                  <div className="user-profile">
                    <div className="user-profile-img">
                      <img src={feedback}></img>
                    </div>

                    <div>
                      <p>Give Feedback</p>
                      <a>Help us to improve the new design</a>
                      <p></p>
                    </div>
                  </div>
                  <hr></hr>
                  <div className="settings-links">
                    <img src={logout} className="settings-icon"></img>
                    <a href="http://localhost:3000/ping.com">Logout</a>
                    <img src={arrow} className="settings-arrow" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
    );
  }
}

export default Navbar;
