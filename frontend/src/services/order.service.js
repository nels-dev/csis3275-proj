import axios from "./axios";

const orderService = {
  addOrder: (productId) => axios.post(`/api/client/order`, { productId }),
};

export default orderService;
