import { createRouter, createWebHistory } from "vue-router";
import MapView from "@/components/map/Map.vue";
import LoginView from "@/components/user/UserLogin.vue";

import { storeToRefs } from "pinia";

import {useUserStore} from "@/stores/user"

const onlyAuthUser = async(to, from, next) =>{
  const userStore = useUserStore()
  const { userInfo, isValidToken } = storeToRefs(userStore);
  const { getUserInfo } = userStore;

  let token = sessionStorage.getItem("accessToken");

  // 유저 정보가 있고, 토큰이 있으면 
  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }

  // 유저 정보가 없거나, 토큰이 유효하지 않으면
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    // 현재 라우팅 계속 진행
    next();
  }
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      redirect: {name: "home"},
    },
    {
      path: "/home",
      name: "home",
      component: MapView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children:[
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/user/UserLogin.vue")
        },
        {
          path: "register",
          name: "user-register",
          component: () => import("@/components/user/UserRegister.vue")
        },{
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserMyPage.vue")
        }
      ]
    }
  ],
});

export default router;
