import axios from "./axios";

const orderService = {
  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  // should use order.status?
  getOrders: () => axios.get(`/api/client/order`),
};

export default orderService;
