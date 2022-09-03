import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:5020/api/Authentication";

class AuthenticationService {
  async authentication(request) {
    return await axios.post(CONCERTAPIBASEURL + "/authenticate", request);
  }

  async register(request) {
    return await axios.post(CONCERTAPIBASEURL + "/register", request);
  }
}

export default new AuthenticationService();
