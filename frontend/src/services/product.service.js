import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/client/products"),
  getLatestProducts: () => axios.get("/api/client/products/latest"),
  getProduct: (id) => axios.get(`/api/client/products/${id}`),
};

export default productService;
