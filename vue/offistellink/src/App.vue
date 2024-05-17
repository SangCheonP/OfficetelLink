<script setup>
import { ref } from "vue";
import { RouterLink, RouterView } from "vue-router";

// vue import
import AppHeader from "@/components/AppHeader.vue";
import Map from "@/components/map/Map.vue";

// store import

// login
// const loginStore = useLoginStore();
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
.kakaonav {
  text-align: center;
}
</style>
