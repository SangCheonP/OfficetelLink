import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

// Pinia 인스턴스를 생성합니다.
const pinia = createPinia();

// `pinia-plugin-persistedstate`를 Pinia에 추가합니다.
pinia.use(piniaPluginPersistedstate);

export default pinia;
