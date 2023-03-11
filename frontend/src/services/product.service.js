import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/client/products"),
  getProduct: (id) => axios.get(`/api/client/products/${id}`),
};

export default productService;
