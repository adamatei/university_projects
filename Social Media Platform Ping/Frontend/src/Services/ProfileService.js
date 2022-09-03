import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:5020/api/profile";

class ProfileService {
  async getProfile(username) {
    return await axios.get(CONCERTAPIBASEURL + "/" + username);
  }

  async updateProfile(profile) {
    return await axios.put(CONCERTAPIBASEURL, profile);
  }

  async getProfiles() {
    return await axios.get(CONCERTAPIBASEURL);
  }

  async deleteProfile(id) {
    return await axios.delete(CONCERTAPIBASEURL + "/" + id);
  }
}

export default new ProfileService();
