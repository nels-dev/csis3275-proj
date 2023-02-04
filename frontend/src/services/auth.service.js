import axios from "./axios";
const authService = {
  login: (loginRequest) => {
    return axios
      .post("/api/login", {
        email: loginRequest.email,
        password: loginRequest.password,
      })
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem("authentication", JSON.stringify(response.data));
        }
        return response.data;
      });
  },
  logout: () => localStorage.removeItem("authentication"),
};

export default authService;
