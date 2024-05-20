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
        <textarea id="content" v-model="form.content" required></textarea>
        </div>
        <button type="submit" class="submit-button">등록</button>
    </form>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const form = ref({
title: '',
content: ''
});

const router = useRouter();

const handleSubmit = async () => {
const formData = {
    title: form.value.title,
    content: form.value.content
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
margin-bottom: 70px;
}

.form-group label {
display: block;
margin-bottom: 5px;
font-weight: bold;
}

.form-group input,
.form-group textarea {
width: 100%;
padding: 10px;
border: 1px solid #ccc;
border-radius: 4px;
}

.form-group input#title {
width: 100%;
padding: 10px;
border: 1px solid #ccc;
border-radius: 4px;
}

.form-group textarea#content {
height: 200px;
padding: 10px;
border: 1px solid #ccc;
border-radius: 4px;
}

.submit-button {
display: block;
width: 30%;
padding: 10px;
margin-left: 200px;
background-color: #28a745;
color: #fff;
border: none;
border-radius: 4px;
cursor: pointer;
}

.submit-button:hover {
background-color: #218838;
}
</style>
