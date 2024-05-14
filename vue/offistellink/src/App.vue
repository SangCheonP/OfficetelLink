<script setup>
import { ref } from "vue";
import { RouterLink, RouterView } from "vue-router";

// vue import
import AppHeader from "@/components/AppHeader.vue";
import Map from "@/components/map/Map.vue";

// store import
import { useLoginStore } from "@/stores/login.js";

// login
const loginStore = useLoginStore();
</script>

<template>
  <div>
    <!-- 메인 페이지 위 네비게이션 바 -->
    <AppHeader />
    <div class="main">
      <div class="kakaonav">
        <!-- <Map></Map> -->
        <RouterView />
      </div>
    </div>
  </div>
</template>

<style scoped>
body {
  background-color: red; /* 회색 배경색 설정 */
}

.main {
  min-height: 100vh; /* 전체 화면 높이 설정 */
  background-color: inherit; /* 부모 배경색 상속 */
}
.kakaonav {
  text-align: center;
}
</style>
