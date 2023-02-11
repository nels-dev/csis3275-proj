import axios from "./axios";

const storeService = {
  getSellerProducts: () => axios.get("/api/client/store/products"),
};

export default storeService;
