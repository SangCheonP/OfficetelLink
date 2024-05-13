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
    <div class="kakaonav">
      <Map />
    </div>
  </div>
</template>

<style scoped>
.kakaonav {
  text-align: center;
}
</style>
