import axios from "./axios";

const orderService = {
  updateOrder: (productId, shipmentReference, status) =>
    axios.patch(
      `/api/client/order?productId=${productId}&shipmentReference=${shipmentReference}&status=${status}`
    ),

  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  getOrders: () => axios.get(`/api/client/order`),

  updateOrderStatus: (id, status) =>
    axios.put(`/api/client/order/${id}/status`, { status }),

  getOrderByProductIdAndStatus: (status, productId) =>
    axios.get(`/api/client/order?status=${status}&productId=${productId}`),
};
export default orderService;
