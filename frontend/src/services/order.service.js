import axios from "./axios";

const orderService = {
  addOrder: (Id) => axios.post("/api/client/order", Id),
};

export default orderService;
