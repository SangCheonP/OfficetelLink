<script setup>
import { RouterLink, RouterView } from 'vue-router';
import AppHeader from '@/components/AppHeader.vue';
import Map from '@/components/map/Map.vue';
</script>

<template>
  <div>
    <!-- 메인 페이지 위 네비게이션 바 -->
    <AppHeader/>
    <div class="kakaonav">
      <Map></Map>  
    </div>
  </div>
</template>

<style scoped>
.kakaonav {
  text-align: center;
}
</style>
