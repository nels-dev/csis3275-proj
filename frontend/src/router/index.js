import { createWebHistory, createRouter } from "vue-router";
import SignIn from '../views/SignIn.vue'
import AdminDashboard from '../views/AdminDashboard.vue'
import ClientMarketplace from '../views/ClientMarketplace.vue'
import DefaultRedirect from '../views/DefaultRedirect.vue'

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
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})