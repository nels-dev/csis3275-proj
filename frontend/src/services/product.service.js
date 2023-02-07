import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/products"),
};

export default productService;
