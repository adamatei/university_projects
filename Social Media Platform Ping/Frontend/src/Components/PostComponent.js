import React from "react";
import "./MainPageComponent.css";
import Avatar from "react-user-avatar";
import { BsThreeDotsVertical } from "react-icons/bs";
import like from "../Images/like.png";
import likeBlue from "../Images/like-blue.png";
import moment from "moment";
import PostService from "../Services/PostService";

class PostComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hashtags: [],
      mentions: [],
      likes: [],
      id: this.props.post.id,
      post: this.props.post,
      key: this.props.key,
    };
    this.viewUser = this.viewUser.bind(this);
    this.isLiked = this.isLiked.bind(this);
    this.like = this.like.bind(this);
  }

  componentDidMount() {
    this.setState({
      hashtags: this.state.post.tags.filter((tag) => {
        return tag.type.toLowerCase().startsWith("hashtag");
      }),
    });
    this.setState({
      mentions: this.state.post.tags.filter((tag) => {
        return tag.type.toLowerCase().startsWith("mention");
      }),
    });
    this.setState({
      likes: this.state.post.tags.filter((tag) => {
        return tag.type.toLowerCase().startsWith("like");
      }),
    });
  }

  viewUser(username) {
    return "http://localhost:3000/profile/" + username;
  }

  isLiked() {
    if (
      this.state.likes.some(
        (v) => v.content == localStorage.getItem("username")
      )
    ) {
      return true;
    } else {
      return false;
    }
  }

  like() {
    console.log("Picture liked");
    let like = { type: "like", content: localStorage.getItem("username") };
    this.state.post.tags.push(like);

    PostService.updatePost(this.state.post).then((res) =>
      this.setState({
        likes: this.state.post.tags.filter((tag) => {
          return tag.type.toLowerCase().startsWith("like");
        }),
      })
    );
  }

  render() {
    return (
      <div className="post-container" id="profile-post-container">
        <script type="text/javascript" src="myscript.js?ver=12345"></script>
        <div className="post-row">
          <div className="user-profile">
            <div className="user-profile-img">
              <Avatar size="40" name={this.state.post.createdBy} src="" />
            </div>
            <div>
              <p>{this.state.post.createdBy}</p>
              <span>
                {moment(this.state.post.createdOn).format("MMMM")},{" "}
                {moment(this.state.post.createdOn).format("DD")}{" "}
                {moment(this.state.post.createdOn).format("YYYY")},
                {moment(this.state.post.createdOn).format("HH:mm")}
              </span>
              <p></p>
            </div>
          </div>
          <a>
            <BsThreeDotsVertical />
          </a>
        </div>

        <p className="post-text">
          {this.state.mentions.map((mention) => (
            <a href={this.viewUser(mention.content)}>@{mention.content} </a>
          ))}{" "}
          {this.state.post.text}{" "}
          {this.state.hashtags.map((hashtag) => (
            <a>#{hashtag.content} </a>
          ))}
        </p>
        <div className="post-row">
          <div className="activity-icons">
            <div>
              {this.isLiked() == false && (
                <img onClick={this.like} src={like}></img>
              )}
              {this.isLiked() == true && <img src={likeBlue}></img>}
              {this.state.likes.length}
            </div>
          </div>
          <div className="post-profile-icon"></div>
        </div>
      </div>
    );
  }
}

export default PostComponent;
