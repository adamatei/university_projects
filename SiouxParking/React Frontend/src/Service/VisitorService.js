import axios from "axios";
const CONCERTAPIBASEURL = "http://localhost:8080/visitors";
class VisitorService {
  getVisitors() {
    return axios.get(CONCERTAPIBASEURL);
  }

  createVisitor(visitor) {
    return axios.post(CONCERTAPIBASEURL, visitor);
  }

  getVisitor(visitorId) {
    return axios.get(CONCERTAPIBASEURL + "/" + visitorId);
  }

  updateVisitor(visitorId, visitor) {
    return axios.put(CONCERTAPIBASEURL + "/" + visitorId, visitor);
  }

  deleteVisitor(visitorId) {
    return axios.delete(CONCERTAPIBASEURL + "/" + visitorId);
  }
}

export default new VisitorService();
