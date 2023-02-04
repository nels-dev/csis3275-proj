import axios from "./axios";

const userService = {
  getCurrentClientUser: () => axios.get("/api/client/user"),

  register: (data) => axios.post("/api/register", data),
};

export default userService;
