import Cookies from "js-cookie";
import jwt_decode from "jwt-decode";
import { runInAction } from "mobx";
import { login } from "./APIRequests";
import UserStore from "./stores/UserStore";

class AuthService {
  async login(email, password) {
    const response = await login(email, password);
    if (response.status === 200) {
      Cookies.set("Authorization", response.data.access);
      runInAction(() => {
        UserStore.refresh = response.data.refresh;
      });
      return response.data;
    } else {
      return null;
    }
  }
  logout() {
    Cookies.remove("Authorization");
  }

  getCurrentUser() {
    let token = Cookies.get("Authorization");
    if (token !== undefined) {
      let decoded = jwt_decode(token);
      return { token: token, user_id: decoded.user_id, role: decoded.role };
    } else {
      return { token: "", user_id: 0, role: "" };
    }
  }
}
export default new AuthService();
