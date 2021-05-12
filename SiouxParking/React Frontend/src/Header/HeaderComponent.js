import React from "react";
import back from "../sioux.png";
import LoginButton from "./../LoginButton";
import LogoutButton from "./../LogoutButton";

class HeaderComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchTerm: "",
    };
    this.setSearchTerm = this.setSearchTerm.bind(this);
  }

  setSearchTerm(event) {
    this.setState({
      searchTerm: event.target.value,
    });
  }

  render() {
    return (
      <header className="header">
        <img src={back} alt="Logo" className="header__logo" />
        <form className="search">
          <input
            type="text"
            className="search__field"
            placeholder="Search for visitors"
            onChange={this.setSearchTerm}
          />
          <button
            className="btn search__btn btn--animated"
            onChange={this.setSearchTerm}
          >
            <svg className="search__icon">
              <use href="/icons.c4b52749.svg"></use>
            </svg>
            <span>Search</span>
          </button>
        </form>

        <nav class="nav">
          <ul class="nav__list">
            <li class="nav__item">
              <LogoutButton />
            </li>
            <li class="nav__item">
              <button class="nav__btn nav__btn--add-visitor" onClick={this.add}>
                <svg class="nav__icon">
                  <use href="/icons.c4b52749.svg"></use>
                </svg>
                <span>New Visitor</span>
              </button>
            </li>

            <li class="nav__item">
              <button class="nav__btn nav__btn--bookmarks">
                <svg class="nav__icon">
                  <use href="/icons.c4b52749.svg"></use>
                </svg>
                <span>Bookmarks</span>
              </button>
              <div class="bookmarks">
                <ul class="bookmarks__list">
                  <div class="message">
                    <div>
                      <svg>
                        <use href="/icons.c4b52749.svg"></use>
                      </svg>
                    </div>
                    <p>No bookmarks yet. Find a visitor and bookmark :)</p>
                  </div>
                </ul>
              </div>
            </li>
          </ul>
        </nav>
      </header>
    );
  }
}

export default HeaderComponent;
