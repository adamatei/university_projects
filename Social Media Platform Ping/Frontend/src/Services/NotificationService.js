import axios from "axios";

const CONCERTAPIBASEURL = "http://localhost:5020/api/notification";

class NotificationService {
  async getNotificationsByUsername(username) {
    return await axios.get(CONCERTAPIBASEURL + "/" + username);
  }
}

export default new NotificationService();
