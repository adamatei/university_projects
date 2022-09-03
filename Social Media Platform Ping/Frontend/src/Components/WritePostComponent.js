import React from "react";
import "./MainPageComponent.css";
import Avatar from "react-user-avatar";
import TagsInput from "react-tagsinput";
import "react-tagsinput/react-tagsinput.css";
import Select from "react-select";
import FollowService from "../Services/FollowService";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";
import PostService from "../Services/PostService";

class WritePostComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchWord: "",
      firstName: "Axl",
      lastName: "Rose",
      username: localStorage.getItem("username"),
      pings: [],
      text: "",
      followers: [],
      following: [],
      tags: [],
      trends: [],
      mentions: [],
      mentionsData: [],
      hashtags: [],
    };
    this.handleChangeTags = this.handleChangeTags.bind(this);
    this.handleChangeMentions = this.handleChangeMentions.bind(this);
    this.handleText = this.handleText.bind(this);
    this.createPost = this.createPost.bind(this);
  }

  handleText(event) {
    this.setState({
      text: event.target.value,
    });
  }

  handleChangeTags(hashtags) {
    this.setState({ hashtags });
  }

  handleChangeMentions(mentions) {
    this.setState({ mentions });
  }

  createPost() {
    if (this.state.text.length != 0) {
      let ping = {
        createdBy: this.state.username,
        createdOn: new Date(Date().toLocaleString()),
        text: this.state.text,
        tags: this.addTags(),
      };
      console.log(ping);
      PostService.createPost(ping)
        .then((res) => {
          window.location.reload(false);
          confirmAlert({
            customUI: ({ onClose }) => {
              return (
                <div className="custom-ui">
                  <h1>Post successfully created!!</h1>
                  <button className="button-1" onClick={onClose}>
                    Close
                  </button>
                </div>
              );
            },
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
    } else {
      confirmAlert({
        customUI: ({ onClose }) => {
          return (
            <div className="custom-ui">
              <h1>A post should not be empty!</h1>
              <p>Please fill in the post text</p>
              <button className="button-1" onClick={onClose}>
                Close
              </button>
            </div>
          );
        },
      });
    }
  }

  addTags() {
    let postMentions = this.state.mentions.map((m) => ({
      content: m.value,
      type: "mention",
    }));
    console.log(postMentions);
    let postHashtags = this.state.hashtags.map((h) => ({
      content: h,
      type: "hashtag",
    }));
    console.log(postHashtags);
    let postTags = [].concat(postMentions, postHashtags);
    console.log(postTags);
    return postTags;
  }

  componentDidMount() {
    FollowService.getFolowing(this.state.username).then((res) => {
      this.setState({ following: res.data });
      let mentionsData = res.data.map((usrn) => ({
        value: usrn,
        label: usrn,
      }));
      this.setState({ mentionsData: mentionsData });
    });
  }

  render() {
    return (
      <div className="write-post-container">
        <div className="user-profile">
          <div className="user-profile-img">
            <Avatar size="40" name={this.state.username} src="" />
          </div>

          <div>
            <p>{this.state.username}</p>
            <small>Public</small>
            <p></p>
          </div>
        </div>

        <div className="post-input-container">
          <textarea
            rows="3"
            placeholder={"What's on your mind?"}
            onChange={this.handleText}
          ></textarea>
          <div className="add-post-links">
            <a>Hashtags</a>
            <TagsInput
              color="blue"
              size="20"
              value={this.state.hashtags}
              onChange={this.handleChangeTags}
            />
            <br />
            <a>Mention</a>
            <Select
              isMulti
              name="colors"
              options={this.state.mentionsData}
              className="basic-multi-select"
              classNamePrefix="select"
              onChange={this.handleChangeMentions}
            />
          </div>
          <button type="button" id="post-btn" onClick={this.createPost}>
            Post it
          </button>
        </div>
      </div>
    );
  }
}

export default WritePostComponent;
