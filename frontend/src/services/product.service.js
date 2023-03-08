import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/products"),
  getProduct: (id) => axios.get(`/api/products/${id}`),
  createProduct: (formData) =>
    axios.post("/api/products", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    }),
  editProductStatus: (id, status) =>
    axios.patch(`/api/client/store/products/${id}/status?status=${status}`),
  editProduct: (id, form) =>
    axios.put(`/api/client/store/products/${id}`, form),
};

export default productService;
