import axios from "./axios";

const productService = {
  getProducts: () => axios.get("/api/products"),
  getProduct: (id) => axios.get(`/api/products/${id}`),
  createProduct: (formData) =>
    axios.post("/api/products", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    }),
};

export default productService;
