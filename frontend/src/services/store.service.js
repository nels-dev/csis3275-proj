import axios from "./axios";

const storeService = {
  getSellerProducts: () => axios.get("/api/client/store/products"),
  getSellerProductsByStatus: (status) =>
    axios.get(`/api/client/store/products?status=${status}`),
  createProduct: (formData) =>
    axios.post("/api/client/store/products", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    }),
  editProductStatus: (id, status) =>
    axios.put(`/api/client/store/products/${id}/status`, { status }),
  editProduct: (id, form) =>
    axios.put(`/api/client/store/products/${id}`, form),
  getOrderByProductIdAndStatus: (status, productId) =>
    axios.get(
      `/api/client/store/order?status=${status}&productId=${productId}`
    ),
};
export default storeService;
