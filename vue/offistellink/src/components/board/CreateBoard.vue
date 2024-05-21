<template>
    <div class="create-board-wrapper">
        <div class="create-board-container">
        <form @submit.prevent="handleSubmit">
            <div class="form-group">
            <label for="title">제목</label>
            <input type="text" id="title" v-model="form.title" required />
            </div>
            <div class="form-group">
            <label for="content">내용</label>
            <div ref="editorContainer" class="editor-container"></div>
            </div>
            <div class="button-group">
                <button type="submit" class="btn btn-info">등록</button>
                <router-link v-if="!editMode" :to="{ name: 'board' }" class="btn btn-primary">취소</router-link>
            </div>
        </form>
        </div>
    </div>
</template>
  
<script setup>
    import { ref, onMounted } from 'vue';
    import axios from 'axios';
    import { useRouter } from 'vue-router';
    import Quill from 'quill';
    import 'quill/dist/quill.snow.css';

    const form = ref({
    title: '',
    content: ''
    });

    const router = useRouter();
    const editor = ref(null);
    const editorContainer = ref(null);

    const handleSubmit = async () => {
    const userStore = JSON.parse(localStorage.getItem('userStore'));
    const userEmail = userStore.userInfo.email;

    const formData = {
        title: form.value.title,
        content: editor.value.root.innerHTML,
        userEmail: userEmail
    };

    try {
        const response = await axios.post('http://localhost:8080/notices', formData);
        if (response.status === 201) {
        alert('공지사항이 등록되었습니다');
        router.push({ name: 'board' });
        }
    } catch (error) {
        console.error('Error creating notice:', error);
    }
    };

    onMounted(() => {
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
    });
</script>

<style scoped>
.create-board-wrapper {
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

.create-board-container {
  max-width: 600px;
  width: 100%;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input#title {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.editor-container {
  height: 300px;
  margin-bottom: 20px;
}

.submit-button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #218838;
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
</style>
