import "./assets/css/style.css";

import { createApp } from "vue";
import pinia from "./stores"; // Pinia 설정을 가져옵니다.

import App from "./App.vue";
import router from "./router";

import { useKakao } from "vue3-kakao-maps/@utils";

useKakao("API key");

const app = createApp(App);

// Toast plugin
import Toast from 'vue-toastification';
import 'vue-toastification/dist/index.css';

const options = {};
app.use(Toast, options);

app.use(pinia); // Pinia를 Vue 애플리케이션에 추가합니다.
app.use(router);

app.mount("#app");
