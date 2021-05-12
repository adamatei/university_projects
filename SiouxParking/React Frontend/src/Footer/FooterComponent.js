import React from "react";
import { FaTwitter } from "react-icons/fa";
import { FaFacebook } from "react-icons/fa";
import { FaGoogle } from "react-icons/fa";
import { FaInstagram } from "react-icons/fa";
class FooterComponent extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <footer>
        <div class="row">
          <div class="col span-1-of-2">
            <ul class="footer-class">
              <li>
                <a href="#">About us</a>
              </li>
              <li>
                <a href="#">Blog</a>
              </li>
              <li>
                <a href="#">Press</a>
              </li>
              <li>
                <a href="#">iOS App</a>
              </li>
              <li>
                <a href="#">Android App</a>
              </li>
            </ul>
          </div>

          <div class="col span-1-of-2">
            <ul class="social-links">
              <li>
                <a href="https://www.instagram.com/siouxsource/?hl=en">
                  <FaInstagram class="icn-instagram" />
                </a>
              </li>
              <li>
                <a href="https://www.google.com/search?q=sioux+technologies+google&ei=6jWJYOytNbKAi-gP3Om6qAM&oq=sioux+technologies+google&gs_lcp=Cgdnd3Mtd2l6EAM6BwgAEEcQsAM6BAgAEA06CAgAEAgQBxAeOggIABAIEA0QHlCXFljDI2CpJGgCcAJ4AIABXIgB1gaSAQIxNJgBAKABAaoBB2d3cy13aXrIAQjAAQE&sclient=gws-wiz&ved=0ahUKEwjsytzX2qDwAhUywAIHHdy0DjUQ4dUDCA4&uact=5">
                  <FaGoogle class="icn-google" />
                </a>
              </li>
              <li>
                <a href="https://www.facebook.com/sioux.eu/">
                  <FaFacebook class="icn-facebook" />
                </a>
              </li>
              <li>
                <a href="https://twitter.com/SiouxSource?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor">
                  <FaTwitter class="icn-twitter" />
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="row">
          <p class="par">
            Copyright &copy; 2015 by Sioux. All rights reserved.
          </p>
        </div>
      </footer>
    );
  }
}
export default FooterComponent;
