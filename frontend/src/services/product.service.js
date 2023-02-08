import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/products"),
  getProduct: (id) => axios.get(`/api/products/${id}`),
};

export default productService;
