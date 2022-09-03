import axios from "axios";
import UserStore from "./stores/UserStore";
import Cookies from "js-cookie";

const headers = {
  Accept: "application/json",
  "Content-Type": "application/json",
  "Access-Control-Allow-Origin": "*",
};

axios.interceptors.request.use(function (config) {
  const token = Cookies.get("Authorization");
  config.headers.Authorization = "Bearer " + token;

  return config;
});

axios.interceptors.response.use(null, (error) => {
  if (
    error.config &&
    error.response?.status === 401 &&
    !error.config.__isRetry
  ) {
    return new Promise((resolve, reject) => {
      refreshToken(axios, error.config)
        .then((result) => {
          resolve(result);
        })
        .catch((err) => {
          reject(err);
        });
    });
  }
  return Promise.reject(error);
});

const refreshToken = (axios, config) => {
  return new Promise((resolve, reject) => {
    axios
      .post(process.env.REACT_APP_GATEWAY + "token/refresh/", {
        refresh: UserStore.refresh,
      })
      .then((res) => {
        Cookies.set("Authorization", res.data.access);
        config.headers.Authorization = res.data.token;
        axios
          .request(config)
          .then((result) => {
            return resolve(result);
          })
          .catch((err) => {
            console.log(err);
            return reject(err);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  });
};

export const login = (email, password) => {
  return axios.post(
    process.env.REACT_APP_GATEWAY + "login/",
    { email: email, password: password },
    { headers }
  );
};

export const getAllUsers = () => {
  return axios.get(process.env.REACT_APP_GATEWAY + "users/", { headers });
};

export const getUserById = (userId) => {
  let url = process.env.REACT_APP_GATEWAY + "users/" + userId + "/";
  return axios.get(url, { headers });
};

export const createUser = (user) => {
  return axios.post(process.env.REACT_APP_GATEWAY + "users/", user, {
    headers,
  });
};

export const updateUser = (userId, user) => {
  let url = process.env.REACT_APP_GATEWAY + "users/" + userId + "/";
  return axios.put(url, user, { headers });
};

export const deleteUser = (userId) => {
  let url = process.env.REACT_APP_GATEWAY + "users/" + userId + "/";
  return axios.delete(url, { headers });
};

export const getLabelById = (labelId) => {
  let url = process.env.REACT_APP_GATEWAY + "labels/" + labelId + "/";
  return axios.get(url, { headers });
};

export const getLabelSearch = (lookedUpLabel) => {
  let url =
    process.env.REACT_APP_GATEWAY + "labels/search/" + lookedUpLabel + "/";
  return axios.get(url, { headers });
};

export const getAllPendingData = () => {
  let url = process.env.REACT_APP_GATEWAY + "data/";
  return axios.get(url, { headers });
};

export const approvePendingData = (data_id, data) => {
  let url = process.env.REACT_APP_GATEWAY + "data/approve/" + data_id + "/";
  console.log(data);
  return axios.put(url, data, { headers });
};

export const getNodesByName = (nodeName) => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/nodes/" + nodeName + "/";
  return axios.get(url, { headers });
};

export const getGraphNodes = () => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/nodes/";
  return axios.get(url, { headers });
};

export const getGraphConnections = () => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/relationships/";
  return axios.get(url, { headers });
};

export const getNodes = () => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/nodes/";
  return axios.get(url, { headers });
};

export const setNode = (data) => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/nodes/";
  return axios.put(url, data, { headers });
};

export const deleteNode = (id) => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/nodes/";
  return axios.delete(url, {data: {"id": id}}, { headers });
};

export const createData = (submittedData) => {
  let url = process.env.REACT_APP_GATEWAY + "data/";
  return axios.post(url, submittedData, { headers });
};

export const addLink = (link) => {
  let url = process.env.REACT_APP_GATEWAY + "urls/";
  return axios.post(url, link, { headers });
};

export const getRelantionshipColors = () => {
  let url = process.env.REACT_APP_GATEWAY + "graphs/relationships/types/";
  return axios.get(url, { headers });
};
