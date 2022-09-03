import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:5020/api/post";

class PostService {
  async getPostsByUsername(username) {
    return await axios.get(CONCERTAPIBASEURL + "/" + username);
  }

  async getPostById(id) {
    return await axios.get(CONCERTAPIBASEURL + "/id/" + id);
  }

  async createPost(body) {
    return await axios.post(CONCERTAPIBASEURL, body);
  }

  async updatePost(body) {
    return await axios.put(CONCERTAPIBASEURL, body);
  }
}

export default new PostService();
