import axios from "./axios";

const orderService = {
  addOrder: (Id) => axios.post(`/api/client/order?id=${Id}`),
};

export default orderService;
