import axios from "./axios";

const orderService = {
  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),

  getOrders: () => axios.get(`/api/client/order`),

  updateOrderStatus: (id, status) =>
    axios.put(`/api/client/order/${id}/status`, { status }),
};

export default orderService;
