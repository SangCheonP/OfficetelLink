// import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import { useKakao } from 'vue3-kakao-maps/@utils';

useKakao('API key');

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
