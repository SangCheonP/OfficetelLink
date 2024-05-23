<script setup>
import { defineProps } from 'vue';

// props 정의
const props = defineProps({
  posts: {
    type: Array,
    required: true
  }
});

// 날짜 포맷팅 함수
function formatDate(dateString) {
  const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
  return new Date(dateString).toLocaleDateString('ko-KR', options);
}
</script>

<template>
  <table>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(post, index) in posts" :key="post.id">
        <td>
          <router-link :to="`/board/${post.id}`">{{ index + 1 }}</router-link>
        </td>
        <td>
          <router-link class="title" :to="`/board/${post.id}`">{{ post.title }}</router-link>
        </td>
        <td>{{ formatDate(post.created_at) }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');

table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  font-family: 'Noto Sans KR', sans-serif;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}
.title {
    color: steelblue;
}
th {
  background-color: #f4f4f4;
  font-weight: bold;
}
tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}
tbody tr:hover {
  background-color: #f1f1f1;
}
a {
  color: inherit;
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
</style>
