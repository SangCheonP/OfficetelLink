<template>
  <div class="board-wrapper">
    <div class="board-container">
      <template v-if="editMode">
        <input v-model="editForm.title" class="edit-input" />
        <textarea v-model="editForm.content" class="edit-textarea"></textarea>
      </template>
      <template v-else>
        <h1>{{ notice.title }}</h1>
        <div class="notice-meta">
          <span>조회수: {{ notice.views }}</span>
          <span>작성자: {{ notice.userEmail }}</span>
        </div>
        <div class="notice-content">
          {{ notice.content }}
        </div>
        <div class="like-content">
          <img 
            src="@/assets/images/ddabong.png" 
            alt="Like Button" 
            class="good-img" 
            @click="confirmLike(notice.id)" 
            :class="{ liked: hasLiked }"
          />
          <span>: {{ notice.isLike }}</span>
        </div>
        <div class="file-content">
          <span>파일: {{ notice.fileName }}</span>
        </div>
      </template>
      <div class="button-group">
        <router-link v-if="!editMode" :to="{ name: 'board' }" class="btn btn-primary">목록으로 돌아가기</router-link>
        <button v-if="editMode" @click="confirmUpdate" class="btn btn-info">저장</button>
        <button v-if="editMode" @click="cancelEdit" class="btn btn-secondary">취소</button>
        <button v-if="!editMode" @click="editNotice" class="btn btn-info">수정</button>
        <button v-if="!editMode" @click="confirmDelete(notice.id)" class="btn btn-danger">삭제</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const notice = ref({});
const editForm = ref({
  title: '',
  content: ''
});
const editMode = ref(false);
const route = useRoute();
const router = useRouter();
const hasLiked = ref(false); // 사용자가 좋아요를 눌렀는지 여부를 추적

const fetchNotice = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/notices/${route.params.id}`);
    notice.value = response.data;
  } catch (error) {
    console.error('Error fetching notice:', error);
  }
};

const confirmDelete = async (id) => {
  if (confirm('삭제하시겠습니까?')) {
    await deleteNotice(id);
  }
};

const editNotice = () => {
  editForm.value = { ...notice.value };
  editMode.value = true;
};

const cancelEdit = () => {
  editMode.value = false;
};

const confirmUpdate = async () => {
  if (confirm('수정하시겠습니까?')) {
    await updateNotice();
    alert('수정 완료!');
    fetchNotice();
  }
};

const updateNotice = async () => {
  try {
    const response = await axios.put(`http://localhost:8080/notices/${route.params.id}`, editForm.value);
    notice.value = response.data;
    editMode.value = false;
  } catch (error) {
    console.error('Error updating notice:', error);
  }
};

const deleteNotice = async (id) => {
  try {
    await axios.delete(`http://localhost:8080/notices/${id}`);
    router.push({ name: 'board' });
  } catch (error) {
    console.error('Error deleting notice:', error);
  }
};

const confirmLike = async (id) => {
  if (!hasLiked.value && confirm('좋아요를 누르시겠습니까?')) {
    likeNotice(id);
    alert('좋아요가 반영되었습니다.');
  }
};

const likeNotice = async (id) => {
  try {
    await axios.post(`http://localhost:8080/notices/${id}/like`);
    notice.value.isLike += 1; // 좋아요 수 갱신
    hasLiked.value = true; // 좋아요를 눌렀음을 표시
  } catch (error) {
    console.error('Error liking notice:', error);
  }
};

onMounted(fetchNotice);
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
  display: flex;
  flex-direction: column;
}

.notice-detail {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-family: 'Poppins', sans-serif;
  color: #333;
}

.notice-meta {
  display: flex;
  justify-content: space-between;
  font-family: 'Poppins', sans-serif;
  color: #666;
  margin-bottom: 10px;
}

.notice-content {
  font-family: 'Poppins', sans-serif;
  color: #666;
  margin-bottom: 20px;
}

.file-content {
  margin-top: 320px;
  margin-left: 450px;
  font-family: 'Poppins', sans-serif;
  color: #666;
}

.good-img {
  width: 30px;
  height: 30px;
  cursor: pointer; /* 클릭 가능하게 커서 변경 */
}

.good-img.liked {
  filter: grayscale(100%); /* 이미 좋아요를 누른 경우 이미지 회색 처리 */
}

.notice-file {
  margin-top: 20px;
  font-family: 'Poppins', sans-serif;
  color: #666;
}

.button-group {
  display: flex;
  gap: 10px;
  margin-top: auto;
  justify-content: center;
}

.button-group .btn {
  flex: 1;
  padding: 10px;
  text-align: center;
  border-radius: 4px;
  font-size: 16px;
}

.edit-input,
.edit-textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-family: 'Poppins', sans-serif;
  color: #666;
}

.edit-textarea {
  height: 300px; /* 텍스트 영역의 높이를 증가 */
}
</style>