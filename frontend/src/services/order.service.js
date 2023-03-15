import axios from "./axios";

const orderService = {
  addOrder: (productId) =>
    axios.post(`/api/client/order?productId=${productId}`),

  updateOrder: (orderId, shippmentReference) =>
    axios.patch(
      `/api/client/order?orderId=${orderId}&shippmentReference=${shippmentReference}`
    ),
  getOrder: (productId) =>
    axios.get(`/api/client/order?productId=${productId}`),
  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  // should use order.status? by Nelson
  getOrders: () => axios.get(`/api/client/order`),
};
export default orderService;
