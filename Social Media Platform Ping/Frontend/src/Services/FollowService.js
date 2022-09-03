import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:5020/api/follow";

class FollowService {
  async getFolowing(username) {
    return await axios.get(CONCERTAPIBASEURL + "/followed/" + username);
  }

  async getFollower(username) {
    return await axios.get(CONCERTAPIBASEURL + "/followers/" + username);
  }

  async getRequests(username) {
    return await axios.get(CONCERTAPIBASEURL + "/requests/" + username);
  }

  async createRequest(body) {
    return await axios.post(CONCERTAPIBASEURL, body);
  }

  async updateRequest(body) {
    return await axios.put(CONCERTAPIBASEURL, body);
  }
}

export default new FollowService();
