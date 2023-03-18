import axios from "./axios";

const orderService = {
  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  getOrders: () => axios.get(`/api/client/order`),

  getOrdersByProductId: (productId) =>
    axios.get(`/api/client/order?productId=${productId}`),

  updateOrderStatus: (id, status) =>
    axios.put(`/api/client/order/${id}/status`, { status }),

  updateOrderStatusWithShipmentReference: (id, status, shipmentReference) =>
    axios.put(`/api/client/order/${id}/status`, { status, shipmentReference }),
};
export default orderService;
