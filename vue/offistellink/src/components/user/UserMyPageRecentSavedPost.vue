<script setup>
import { defineProps } from 'vue';

// props 정의
const props = defineProps({
    savedPosts: {
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
        <th>제목</th>
        <th>작성자</th>
        <th>저장일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(post, index) in savedPosts" :key="post.saved_at">
        <td>{{ post.title }}</td>
        <td>{{ post.writer }}</td>
        <td>{{ formatDate(post.saved_at) }}</td>
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
</style>
