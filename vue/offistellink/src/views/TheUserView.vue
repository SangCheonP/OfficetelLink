<script setup>

</script>

<!-- PurpleBackground.vue -->
<template>
    <div class="limiter">
      <main class="container-login100">
        <router-view/>
      </main>
    </div>
  </template>
  
  <style scoped>
  .limiter {
    width: 100%;
    margin: 0 auto;
  }
  
  .container-login100 {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 15px;
    background: #9053c7;
    background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
    background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
    background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
    background: linear-gradient(-135deg, #c850c0, #4158d0);
  }
  </style>
  