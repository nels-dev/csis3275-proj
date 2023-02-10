import axios from "./axios";

const accountService = {
  getAccount: () => axios.get("/api/client/account"),
};

export default accountService;
