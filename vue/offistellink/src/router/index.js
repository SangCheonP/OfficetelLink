import { createRouter, createWebHistory } from "vue-router";
import MapView from "@/components/map/Map.vue";
import LoginView from "@/components/login/Login.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: "/home",
    },
    {
      path: "/home",
      name: "Home",
      component: MapView,
    },
    {
      path: "/login",
      name: "Login",
      component: LoginView,
    },
    {
      path: "/register",
      name: "Register",
      component: () => import("@/components/login/Register.vue")
    },
  ],
});

export default router;
