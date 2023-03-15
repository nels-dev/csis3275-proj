import axios from "./axios";

const orderService = {
  updateOrder: (productId, shippmentReference, status) =>
    axios.patch(
      `/api/client/order?productId=${productId}&shippmentReference=${shippmentReference}&status=${status}`
    ),

  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  getOrders: () => axios.get(`/api/client/order`),

  getOrderByProductIdAndStatus: (status, productId) =>
    axios.get(`/api/client/order?status=${status}&productId=${productId}`),
};
export default orderService;
