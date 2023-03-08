import { createWebHistory, createRouter } from "vue-router";
import SignIn from "../views/SignIn.vue";
import AdminDashboard from "../views/AdminDashboard.vue";
import ClientMarketplace from "../views/ClientMarketplace.vue";
import DefaultRedirect from "../views/DefaultRedirect.vue";
import RegistrationPage from "../views/RegistrationPage.vue";
import ProductDetailsPage from "../views/ProductDetailsPage.vue";
import MyStore from "../views/MyStore.vue";
import MyAccountPage from "@/views/MyAccountPage";
import CreateProductPage from "../views/CreateProductPage.vue";
const routes = [
  {
    path: "/",
    component: DefaultRedirect,
  },
  {
    path: "/signin",
    component: SignIn,
  },
  {
    path: "/home",
    component: ClientMarketplace,
  },
  {
    path: "/admin",
    component: AdminDashboard,
  },
  {
    path: "/register",
    component: RegistrationPage,
  },
  {
    path: "/productdetails/:id",
    component: ProductDetailsPage,
  },
  {
    path: "/Mystore",
    component: MyStore,
  },
  {
    path: "/account",
    component: MyAccountPage,
  },
  {
    path: "/createproduct",
    component: CreateProductPage,
  },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
