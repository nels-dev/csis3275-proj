import axios from "./axios";

const storeService = {
  getSellerProducts: () => axios.get("/api/client/store/products"),
  getSellerProductsByStatus: (status) =>
    axios.get(`/api/client/store/products/${status}`),
};
export default storeService;
