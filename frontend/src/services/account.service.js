import axios from "./axios";

const accountService = {
  getAccount: () => axios.get("/api/client/account"),
  getDepositRequests: (status) =>
    axios.get(`/api/client/depositRequests?status=${status}`),
  getDepositRequestsForAdmin: (status) =>
    axios.get(`/api/admin/depositRequests?status=${status}`),
  submitDepositRequest: (form) =>
    axios.post(`/api/client/depositRequests`, form),
  getWithdrawalRequests: (status) =>
    axios.get(`/api/client/withdrawalRequests?status=${status}`),
  getWithdrawalRequestsForAdmin: (status) =>
    axios.get(`/api/admin/withdrawalRequests?status=${status}`),
  submitWithdrawalRequest: (form) =>
    axios.post(`/api/client/withdrawalRequests`, form),
  fulfillDepositRequest: (id) =>
    axios.patch(`/api/admin/depositRequests/${id}/status`, {
      status: "FULFILLED",
    }),
  fulfillWithdrawalRequest: (id) =>
    axios.patch(`/api/admin/withdrawalRequests/${id}/status`, {
      status: "FULFILLED",
    }),
  rejectDepositRequest: (id, reason) =>
    axios.patch(`/api/admin/depositRequests/${id}/status`, {
      status: "REJECTED",
      rejectReason: reason,
    }),
  rejectWithdrawalRequest: (id, reason) =>
    axios.patch(`/api/admin/withdrawalRequests/${id}/status`, {
      status: "REJECTED",
      rejectReason: reason,
    }),
};

export default accountService;
