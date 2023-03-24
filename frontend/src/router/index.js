import { createWebHistory, createRouter } from "vue-router";
import SignIn from "../views/SignIn.vue";
import AdminDashboard from "../views/AdminDashboard.vue";
import ClientMarketplace from "../views/ClientMarketplace.vue";
import ClientHome from "../views/ClientHome.vue";
import DefaultRedirect from "../views/DefaultRedirect.vue";
import RegistrationPage from "../views/RegistrationPage.vue";
import ProductDetailsPage from "../views/ProductDetailsPage.vue";
import MyStore from "../views/MyStore.vue";
import MyAccountPage from "@/views/MyAccountPage";
import CreateProductPage from "../views/CreateProductPage.vue";
import EditProductPage from "../views/EditProductPage.vue";
import MyOrderPage from "../views/MyOrderPage.vue";
import MyProfilePage from "../views/MyProfilePage.vue";
import ClientPage from "../views/ClientPage.vue";

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
    path: "/register",
    component: RegistrationPage,
  },
  {
    path: "/client",
    component: ClientPage,
    children: [
      {
        path: "home",
        component: ClientHome,
      },
      {
        path: "marketplace",
        component: ClientMarketplace,
      },
      {
        path: "productdetails/:id",
        component: ProductDetailsPage,
      },
      {
        path: "mystore",
        component: MyStore,
      },
      {
        path: "account",
        component: MyAccountPage,
      },
      {
        path: "createproduct",
        component: CreateProductPage,
      },
      {
        path: "editProduct/:id",
        component: EditProductPage,
      },
      {
        path: "myorder",
        component: MyOrderPage,
      },
      {
        path: "profile",
        component: MyProfilePage,
      },
    ],
  },
  {
    path: "/admin",
    component: AdminDashboard,
  },
];

export default createRouter({
  history: createWebHistory(),
  routes,
});
