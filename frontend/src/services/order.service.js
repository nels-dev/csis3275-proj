import axios from "./axios";

const orderService = {
  updateOrder: (orderId, shippmentReference) =>
    axios.patch(
      `/api/client/order?orderId=${orderId}&shippmentReference=${shippmentReference}`
    ),

  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  // should use order.status? by Nelson
  getOrders: () => axios.get(`/api/client/order`),
};
export default orderService;
