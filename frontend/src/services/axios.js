import axios from "axios";
import store from "../store";
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});
axiosInstance.interceptors.request.use((config) => {
  if (store.state.auth.status.loggedIn) {
    config.headers["Authorization"] = `Bearer ${store.state.auth.token}`;
  }
  return config;
});
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status == 401) {
      localStorage.removeItem("authentication");
    }
    console.log("Http Error Response: ", error.response);
    return Promise.reject(error);
  }
);
export default axiosInstance;
