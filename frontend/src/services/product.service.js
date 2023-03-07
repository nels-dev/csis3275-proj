import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/products"),
  getProduct: (id) => axios.get(`/api/products/${id}`),
  editProductStatus: (id, status) =>
    axios.patch(`/api/client/store/products/${id}/status?status=${status}`),
};

export default productService;
