<template>
  <div class="board-wrapper">
    <div class="board-container">
      <h1 class="title">공지 사항</h1>
      <div class="form-group">
        <button class="create-button" @click="navigateToCreateBoard">등록</button>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>작성번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>조회수</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="notice in paginatedNotices" :key="notice.id">
              <td>{{ notice.id }}</td>
              <td>
                <div @click="handleNoticeClick(notice.id)" class="notice-title">
                  {{ notice.title }}
                </div>
              </td>
              <td>{{ notice.userEmail }}</td>
              <td>{{ notice.views }}</td>
            </tr>
          </tbody>
        </table>
        <div class="pagination">
          <button @click="handlePageChange(1)" :disabled="currentPage === 1">처음</button>
          <button @click="handlePageChange(currentPage - 1)" :disabled="currentPage === 1">이전</button>
          <span>{{ currentPage }}</span>
          <button @click="handlePageChange(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
          <button @click="handlePageChange(totalPages)" :disabled="currentPage === totalPages">끝으로</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const notices = ref([]);
const router = useRouter();

const currentPage = ref(1);
const noticesPerPage = ref(6);

const fetchNotices = async () => {
  try {
    const response = await axios.get('http://localhost:8080/notices');
    notices.value = response.data;
  } catch (error) {
    console.error('Error fetching notices:', error);
  }
};

// Call fetchNotices once when the component is mounted
onMounted(fetchNotices);

const totalPages = computed(() => {
  return Math.ceil(notices.value.length / noticesPerPage.value);
});

const paginatedNotices = computed(() => {
  const start = (currentPage.value - 1) * noticesPerPage.value;
  const end = start + noticesPerPage.value;
  return notices.value.slice(start, end);
});

const handlePageChange = (page) => {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
};

const handleNoticeClick = (id) => {
  router.push({ name: 'board-detail', params: { id } });
};

const navigateToCreateBoard = () => {
  router.push({ name: 'create-board' });
};
</script>


<style scoped>
.board-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #9053c7;
  background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
  background: linear-gradient(-135deg, #c850c0, #4158d0);
}

.board-container {
  max-width: 1000px;
  width: 100%;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  min-height: 600px;
  font-family: 'Poppins', sans-serif;
}

.title {
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Poppins', sans-serif;
  color: #333;
}

.form-group {
  margin-bottom: 20px;
}

.table-hover tbody tr:hover {
  background-color: #f5f5f5;
}

.notice-title {
  color: #007bff;
  text-decoration: none;
  cursor: pointer;
}

.notice-title:hover {
  text-decoration: underline;
}

.create-button {
  display: block;
  margin: 0 0 20px auto;
  padding: 10px 20px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.create-button:hover {
  background-color: #218838;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination button {
  margin: 0 5px;
  padding: 5px 10px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
}

.pagination button:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

.pagination button:not(:disabled):hover {
  background-color: #0056b3;
}
</style>
