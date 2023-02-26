import axios from "./axios";

const accountService = {
  getAccount: () => axios.get("/api/client/account"),
  getDepositRequests: (status) =>
    axios.get(`/api/client/depositRequests?status=${status}`),
  submitDepositRequest: (form) =>
    axios.post(`/api/client/depositRequests`, form),
  getWithdrawalRequests: (status) =>
    axios.get(`/api/client/withdrawalRequests?status=${status}`),
  submitWithdrawalRequest: (form) =>
    axios.post(`/api/client/withdrawalRequests`, form),
};

export default accountService;
