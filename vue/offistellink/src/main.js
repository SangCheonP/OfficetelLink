import { createApp } from 'vue';
import App from './App.vue';
import pinia from './stores';
import router from './router';

import Toast from 'vue-toastification';
import 'vue-toastification/dist/index.css';

import { useKakao } from 'vue3-kakao-maps/@utils';
useKakao('API key');

const app = createApp(App);

// Toast plugin
const toastOptions = {
  position: 'top-right',
  timeout: 3000,
};
app.use(Toast, toastOptions);

app.use(pinia);
app.use(router);

app.mount('#app');
