import axios from "./axios";

const orderService = {
  updateOrder: (orderId, shippmentReference, status) =>
    axios.patch(
      `/api/client/order?orderId=${orderId}&shippmentReference=${shippmentReference}&status=${status}`
    ),

  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  getOrders: () => axios.get(`/api/client/order`),

  getOrderByProductIdAndStatus: (productId, status) =>
    axios.get(`/api/client/order?orderId=${productId}&status=${status}`),
};
export default orderService;
