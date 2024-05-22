<template>
  <div class="board-wrapper">
    <div class="board-container">
      <template v-if="editMode">
        <div class="form-group">
          <label for="title">제목</label>
          <input v-model="editForm.title" id="title" class="edit-input" required />
        </div>
        <div class="form-group">
          <label for="content">내용</label>
          <div ref="editorContainer" class="edit-textarea"></div>
        </div>
        <div class="form-group">
          <label for="file">파일 첨부하기</label>
          <div class="file-input-wrapper">
            <input type="file" id="file" @change="handleFileUpload" multiple />
            <div class="file-list">
              <div v-for="file in editForm.files" :key="file.id">
                {{ file.originName }}
                <button @click="confirmFileDelete(file.id)">삭제</button>
              </div>
            </div>
          </div>
        </div>
      </template>
      <template v-else>
        <h1>{{ notice.title }}</h1>
        <div class="notice-meta">
          <div class="meta-left">
            <img 
              src="@/assets/images/ddabong.png" 
              alt="Like Button" 
              class="good-img" 
              @click="confirmLike(notice.id)" 
              :class="{ liked: hasLiked }"
            />:
            <span>{{ notice.isLike }}</span>
            <img 
              src="@/assets/images/views.png" 
              alt="Like Views" 
              class="good-img" 
            />:
            <span> {{ notice.views }}</span>
          </div>
          <div class="meta-right">
            <span>작성자: {{ notice.userEmail }}</span>
          </div>
        </div>
        <div class="notice-content" v-html="notice.content"></div>
        <div class="file-content">
          <p><b>첨부 파일</b></p>
          <div v-for="file in notice.files" :key="file.id">
            <a :href="`http://localhost:8080/files/download/${file.savedName}`" @click.prevent="downloadFile(file.savedName)">
              {{ file.originName }}
            </a>
          </div>
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
import { ref, watch, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import Quill from 'quill';
import 'quill/dist/quill.snow.css';

const notice = ref({});
const editForm = ref({
  title: '',
  content: '',
  files: []
});
const editMode = ref(false);
const route = useRoute();
const router = useRouter();
const hasLiked = ref(false);

const editor = ref(null);
const editorContainer = ref(null);
const selectedFiles = ref([]);

const fetchNotice = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/notices/${route.params.id}`);
    notice.value = response.data;
    editForm.value.files = response.data.files || [];
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
    editForm.value.content = editor.value.root.innerHTML;

    const formData = new FormData();
    formData.append('notice', new Blob([JSON.stringify(editForm.value)], { type: 'application/json' }));
    selectedFiles.value.forEach(file => {
      formData.append('files', file);
    });

    const response = await axios.put(`http://localhost:8080/notices/${route.params.id}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

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
    notice.value.isLike += 1;
    hasLiked.value = true;
  } catch (error) {
    console.error('Error liking notice:', error);
  }
};

const handleFileUpload = (event) => {
  const files = Array.from(event.target.files);
  if (files.length > 0) {
    selectedFiles.value = files;
  } else {
    selectedFiles.value = editForm.value.files;
  }
};

const confirmFileDelete = async (fileId) => {
  if (confirm('파일을 삭제하시겠습니까?')) {
    await deleteFile(fileId);
  }
};

const deleteFile = async (fileId) => {
  try {
    await axios.delete(`http://localhost:8080/files/${fileId}`);
    // Remove the file from the editForm.files array
    editForm.value.files = editForm.value.files.filter(file => file.id !== fileId);
  } catch (error) {
    console.error('Error deleting file:', error);
  }
};

const downloadFile = async (savedName) => {
  try {
    const response = await axios.get(`http://localhost:8080/files/download/${savedName}`, {
      responseType: 'blob'
    });
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', savedName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  } catch (error) {
    console.error('Error downloading file:', error);
  }
};

const initializeQuillEditor = () => {
  if (editorContainer.value) {
    editor.value = new Quill(editorContainer.value, {
      theme: 'snow',
      placeholder: '내용을 입력하세요...',
      modules: {
        toolbar: [
          ['bold', 'italic', 'underline', 'strike', 'blockquote', 'code-block'],
          [{ 'header': 1 }, { 'header': 2 }],
          [{ 'list': 'ordered' }, { 'list': 'bullet' }],
          [{ 'script': 'sub' }, { 'script': 'super' }],
          [{ 'indent': '-1' }, { 'indent': '+1' }],
          [{ 'direction': 'rtl' }],
          [{ 'size': ['small', false, 'large', 'huge'] }],
          [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
          [{ 'color': [] }, { 'background': [] }],
          [{ 'font': [] }],
          [{ 'align': [] }],
          ['link', 'clean']
        ]
      }
    });
    editor.value.root.innerHTML = editForm.value.content;
  }
};

watch(editMode, (newValue) => {
  if (newValue) {
    setTimeout(() => {
      initializeQuillEditor();
    }, 0);
  }
});

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

h1 {
  font-size: 2em;
  font-family: 'Poppins', sans-serif;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.edit-input,
.edit-textarea,
#file {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-family: 'Poppins', sans-serif;
  color: #666;
}

.edit-textarea {
  height: 300px;
  margin-bottom: 20px;
}

.notice-meta {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.meta-left {
  display: flex;
  align-items: center;
  gap: 10px;
}

.meta-right {
  font-family: 'Poppins', sans-serif;
  color: #666;
}

.notice-content {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-family: 'Poppins', sans-serif;
  color: #666;
  margin-bottom: 20px;
}

.notice-content img {
  max-width: 100%;
  height: auto;
  display: block;
  margin: 0 auto;
}

.file-content {
  font-family: 'Poppins', sans-serif;
  color: #666;
  margin-bottom: 20px;
}

.good-img {
  width: 30px;
  height: 30px;
  cursor: pointer;
}

.good-img.liked {
  filter: grayscale(100%);
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

/* New CSS for aligning file names to the left */
.file-input-wrapper {
  display: flex;
  flex-direction: column;
}

.file-list {
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* Align items to the left */
  margin-top: 10px;
}

.file-list div {
  margin-bottom: 5px;
  font-family: 'Poppins', sans-serif;
  color: #666;
}
</style>