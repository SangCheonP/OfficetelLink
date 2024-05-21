import { createRouter, createWebHistory } from 'vue-router';
import MapView from '@/components/map/Map.vue';
import LoginView from '@/components/user/UserLogin.vue';
import BoardView from '@/components/board/Board.vue';

import { storeToRefs } from 'pinia';
import { useUserStore } from '@/stores/user';

const onlyAuthUser = async (to, from, next) => {
  const userStore = useUserStore();
  const { userInfo, isValidToken } = storeToRefs(userStore);
  const { getUserInfo } = userStore;

  let token = sessionStorage.getItem('accessToken');

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }

  if (!isValidToken.value || userInfo.value === null) {
    next({ name: 'user-login' });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: { name: 'home' },
    },
    {
      path: '/home',
      name: 'home',
      component: MapView,
    },
    {
      path: '/shop',
      name: 'shop'
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('@/views/TheUserView.vue'),
      children: [
        {
          path: 'login',
          name: 'user-login',
          component: LoginView,
        },
        {
          path: 'register',
          name: 'user-register',
          component: () => import('@/components/user/UserRegister.vue'),
        },
        {
          path: 'mypage',
          name: 'user-mypage',
          beforeEnter: onlyAuthUser,
          component: () => import('@/components/user/UserMyPage.vue'),
        },
        {
          path: 'modify',
          name: 'user-modify',
          beforeEnter: onlyAuthUser,
          component: () => import('@/components/user/UserInfoModify.vue'),
        },
      ],
    },
    {
      path: '/board',
      name: 'board',
      component: () => import('@/components/board/Board.vue')
    },
    {
      path: '/board/:id',
      name: 'board-detail',
      component: () => import('@/components/board/BoardDetail.vue')
    },
    {
      path:'/create-board',
      name: 'create-board',
      component: () => import('@/components/board/CreateBoard.vue')
    }
  ],
});

export default router;
