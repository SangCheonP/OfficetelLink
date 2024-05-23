<template>
  <div class="board-wrapper">
    <div class="board-container">
      <h1 class="titleMain">궁금한 점, 나누고 싶은 이야기를 자유롭게 공유해보세요.</h1>
      <div class="form-group">
        <div class="notice-list">
          <div v-for="notice in paginatedNotices" :key="notice.id" class="notice-card" @click="handleNoticeClick(notice.id)">
            <div class="notice-header">
              <div class="title-wrapper">
                <div v-if="notice?.userEmail?.includes('admin')" class="admin-mark">
                  <img src="@/assets/images/pin.png" alt="admin Mark" class="admin-img" />
                </div>
                <div class="notice-title">{{ notice.title }}</div>
              </div>
            </div>
            <div class="notice-body">
              <div class="notice-stats">
                <p class="notice-likes">좋아요: {{ notice.isLike }}</p>
                <p class="notice-views">조회수: {{ notice.views }}</p>
              </div>
              <p class="notice-content">{{ truncateContent(stripHtmlTags(notice.content), 20) }}</p>
              <p class="notice-author">{{ notice.userEmail }}</p>
            </div>
          </div>
        </div>
        <div class="pagination">
          <button @click="handlePageChange(1)" :disabled="currentPage === 1">처음</button>
          <button @click="handlePageChange(currentPage - 1)" :disabled="currentPage === 1">이전</button>
          <span class="current-page">- {{ currentPage }} -</span>
          <button @click="handlePageChange(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
          <button @click="handlePageChange(totalPages)" :disabled="currentPage === totalPages">끝으로</button>
        </div>
        <button v-if="isAdmin" class="create-button" @click="navigateToCreateBoard">등록</button>
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
const noticesPerPage = ref(8);

const userStore = JSON.parse(localStorage.getItem('userStore'));
const isAdmin = computed(() => userStore?.userInfo?.admin === true);

const fetchNotices = async () => {
  try {
    const response = await axios.get('http://localhost:8080/notices');
    notices.value = response.data;
  } catch (error) {
    console.error('Error fetching notices:', error);
  }
};

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

const stripHtmlTags = (content) => {
  return content.replace(/<\/?[^>]+(>|$)/g, "");
};

const truncateContent = (content, maxLength) => {
  if (content.length > maxLength) {
    return content.substring(0, maxLength) + '...';
  }
  return content;
};
</script>

<style scoped>
.board-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  overflow: hidden;
  background: #9053c7;
  background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
  background: linear-gradient(-135deg, #c850c0, #4158d0);
}

.board-container {
  margin-top: 30px;
  max-width: 1000px;
  width: 100%;
  padding: 10px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  min-height: 600px;
  font-family: 'Poppins', sans-serif;
}

.titleMain {
  color: #1a0d31; /* 다크 블루 색상으로 변경 */
  font-family: 'Noto Sans', sans-serif; /* 글꼴 유지 */
  font-size: 2em; /* 글씨 크기 약간 증가 */
  text-align: center; /* 텍스트 정렬 유지 */
  font-weight: 700; /* 숫자로 굵기 지정 */
  margin-top: 30px; /* 상단 여백 증가 */
  margin-bottom: 50px; /* 하단 여백 증가 */
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2); /* 텍스트 그림자 부드럽게 */
  letter-spacing: 0.05em; /* 자간 추가 */
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

.notice-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  margin: 5px;
}

.notice-card {
  padding: 15px;
  background: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s;
}

.notice-card:hover {
  background: #f1f1f1;
}

.notice-header {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
}

.title-wrapper {
  display: flex;
  align-items: center;
}

.notice-title {
  font-size: 1.1em;
  font-weight: bold;
  color: #333;
  margin-left: 8px;
  text-align: center;
}

.notice-body {
  margin-top: 10px;
}

.notice-stats {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.notice-author {
  font-size: 0.9em;
  color: #666;
  text-align: center;
}

.notice-views, .notice-likes {
  font-size: 0.9em;
  color: #666;
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
  padding-top: 30px;
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

.current-page {
  display: inline-block;
  padding: 5px 10px;
  margin: 0 5px;
  font-size: 1.2em;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: #f5f5f5;
}

.admin-mark {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20px;
  height: 20px;
}

.admin-img {
  max-width: 100%;
  max-height: 100%;
}

/* Media queries for responsive design */
@media (max-width: 768px) {
  .notice-card {
    padding: 10px;
  }

  .notice-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .title-wrapper {
    justify-content: center;
    width: 100%;
  }

  .notice-stats {
    flex-direction: column;
    align-items: flex-start;
  }

  .notice-author {
    text-align: left;
    margin-top: 10px;
  }
}
</style>