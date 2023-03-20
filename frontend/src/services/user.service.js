import axios from "./axios";

const userService = {
  getCurrentClientUser: () => axios.get("/api/client/user"),

  saveUserProfile: (profile) => axios.put("/api/client/user", profile),

  register: (data) => axios.post("/api/register", data),
};

export default userService;
