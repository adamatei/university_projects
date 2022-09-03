import React from "react";
import Navbar from "./Navbar";
import "./AdminPanel.css";
import {
  BsFillPeopleFill,
  BsSearch,
  BsFillGrid3X3GapFill,
} from "react-icons/bs";
import Avatar from "react-user-avatar";
import ProfileService from "../Services/ProfileService";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class AdminPanel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      profiles: [],
      searchWord: "",
    };
    this.handleSearchWord = this.handleSearchWord.bind(this);
    this.filter = this.filter.bind(this);
    this.handleBlock = this.handleBlock.bind(this);
    this.handleRole = this.handleRole.bind(this);
    this.refresh = this.refresh.bind(this);
    this.delete = this.delete.bind(this);
  }
  componentDidMount() {
    ProfileService.getProfiles().then((res) => {
      this.setState({ profiles: res.data });
    });
  }

  handleSearchWord(event) {
    this.setState({
      searchWord: event.target.value,
    });
  }

  delete(id) {
    ProfileService.deleteProfile(id)
      .then(() => {
        console.log("Profile deleted with id " + id);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  handleBlock(block, profile) {
    let body = {
      id: profile.id,
      intro: profile.intro,
      username: profile.username,
      role: profile.role,
      job: profile.jobTitle,
      education: profile.education,
      isPublic: profile.isPublic,
      isBlocked: block,
      livingCity: profile.livingCity,
      livingCountry: profile.livingCountry,
      originCityy: profile.originCity,
      originCountry: profile.originCountry,
      birthday: profile.birthday,
    };
    console.log(body);

    try {
      ProfileService.updateProfile(body)
        .then(() => {
          ProfileService.getProfiles().then((res) => {
            this.setState({ profiles: res.data });
            profile = res.data;
            //console.log(this.state.profiles);
            confirmAlert({
              customUI: ({ onClose }) => {
                return (
                  <div className="custom-ui">
                    <h1>Account successfully updated!</h1>
                    <p>It might take a while to update</p>
                    <button className="button-1" onClick={onClose}>
                      Close
                    </button>
                  </div>
                );
              },
            });
            this.forceUpdate();
            //window.location.reload(false);
          });
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
  }

  handleRole(provided_role, profile) {
    let body = {
      id: profile.id,
      username: profile.username,
      intro: profile.intro,
      role: provided_role,
      job: profile.jobTitle,
      education: profile.education,
      isPublic: profile.isPublic,
      isBlocked: profile.isBlocked,
      livingCity: profile.livingCity,
      livingCountry: profile.livingCountry,
      originCityy: profile.originCity,
      originCountry: profile.originCountry,
      birthday: profile.birthday,
    };
    console.log(body);

    try {
      ProfileService.updateProfile(body)
        .then(() => {
          ProfileService.getProfiles().then((res) => {
            this.setState({ profiles: res.data });
            profile = res.data;
            console.log(this.state.profiles);
            //window.location.reload(false);
            this.forceUpdate();
            confirmAlert({
              customUI: ({ onClose }) => {
                return (
                  <div className="custom-ui">
                    <h1>Role successfully changed!</h1>
                    <p>It might take a while to update</p>
                    <button className="button-1" onClick={onClose}>
                      Close
                    </button>
                  </div>
                );
              },
            });
          });
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
  }

  filter() {
    const keyword = this.state.searchWord;

    if (keyword !== "") {
      const results = this.state.profiles.filter((user) => {
        return user.username.toLowerCase().startsWith(keyword.toLowerCase());
      });
      return results;
    } else {
      return this.state.profiles;
    }
  }

  refresh() {
    window.location.reload();
  }

  render() {
    return (
      <div id="admin-div">
        <script type="text/javascript" src="myscript.js?ver=12345"></script>
        <Navbar />
        <div className="main-content-admin">
          <header id="header">
            <h2>
              <span>
                <BsFillGrid3X3GapFill />
              </span>{" "}
              Dashboard
            </h2>
            <div className="search-wrapper">
              <span>
                <BsSearch color="grey" />
              </span>
              <input
                type="search"
                placeholder="Search here. . ."
                value={this.state.searchWord}
                onChange={this.handleSearchWord}
              />
            </div>
            <div className="user-wrapper">
              <div>
                <h4></h4>
                <small></small>
              </div>
            </div>
          </header>
        </div>

        <main id="admin-main">
          <div className="cards">
            <div className="card-single">
              <div>
                <h1>{this.state.profiles.length}</h1>
                <span>Total Users</span>
              </div>
              <div>
                <span className="las la-users">
                  <BsFillPeopleFill />
                </span>
              </div>
            </div>
            <div className="card-single">
              <div>
                <h1>
                  {
                    this.state.profiles.filter((p) => p.isPublic == false)
                      .length
                  }
                </h1>
                <span>Private Users</span>
              </div>
              <div>
                <span className="las la-users">
                  {" "}
                  <BsFillPeopleFill />
                </span>
              </div>
            </div>
            <div className="card-single">
              <div>
                <h1>
                  {this.state.profiles.filter((p) => p.isPublic == true).length}
                </h1>
                <span>Public Profiles</span>
              </div>
              <div>
                <span className="las la-users">
                  {" "}
                  <BsFillPeopleFill />
                </span>
              </div>
            </div>
            <div className="card-single">
              <div>
                <h1>
                  {" "}
                  {
                    this.state.profiles.filter((p) => p.isBlocked == true)
                      .length
                  }
                </h1>
                <span>Blocked Profiles</span>
              </div>
              <div>
                <span className="las la-users">
                  {" "}
                  <BsFillPeopleFill />
                </span>
              </div>
            </div>
          </div>
          <div className="recent-grid">
            <div className="projects">
              <div className="card-admin">
                <div className="card-header">
                  <h3>Accounts Overview</h3>
                  <button onClick={this.refresh}>Refresh</button>
                </div>
                <div className="card-body">
                  <div className="table-responsive">
                    <table>
                      <thead>
                        <tr>
                          <td>Username</td>
                          <td>Status</td>
                          <td>Role</td>
                        </tr>
                      </thead>
                      <tbody>
                        <script
                          type="text/javascript"
                          src="myscript.js?ver=12345"
                        ></script>
                        {this.filter().map((p) => (
                          <tr keu={p.id}>
                            <td>{p.username}</td>
                            {p.isBlocked == true && <td>Blocked</td>}
                            {p.isBlocked == false && <td>Not Blocked</td>}
                            <td>
                              {p.role == "user" && (
                                <span className="status pink"></span>
                              )}
                              {p.role != "user" && (
                                <span className="status orange"></span>
                              )}
                              {p.role}
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
                <div className="bard-body">
                  <div className="table-responsive"></div>
                </div>
              </div>
            </div>
            <div className="customers">
              <div className="card-admin">
                <div className="card-header">
                  <h3>Actions</h3>
                  <button onClick={this.refresh}>Refresh</button>
                </div>
                {this.filter().map((p) => (
                  <div className="card-body" key={p.id}>
                    <div className="customer">
                      <div className="info">
                        <Avatar
                          name={p.username}
                          size="40px"
                          width="40px"
                          height="40px"
                          className="info-img"
                        />
                        <div className="contact">
                          <h4>{p.username}</h4>
                          {p.isPublic == false && <small>Private</small>}
                          {p.isPublic == true && <small>Public</small>}
                        </div>
                      </div>
                      <div className="info-actions">
                        {p.role == "user" && (
                          <span onClick={() => this.handleRole("admin", p)}>
                            Make Admin
                          </span>
                        )}
                        {p.role != "user" && (
                          <span onClick={() => this.handleRole("user", p)}>
                            Make User
                          </span>
                        )}
                        {p.isBlocked == false && (
                          <span onClick={() => this.handleBlock(true, p)}>
                            Block
                          </span>
                        )}
                        {p.isBlocked == true && (
                          <span onClick={() => this.handleBlock(false, p)}>
                            Unblock
                          </span>
                        )}
                        <span onClick={() => this.delete(p.id)}>Delete</span>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </main>
      </div>
    );
  }
}

export default AdminPanel;
