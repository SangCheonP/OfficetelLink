<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const { userInfo } = storeToRefs(userStore);
const { profileImageUpdate } = userStore;

const fileInput = ref(null);
const croppedImageUrl = ref(null);

// 프로필 이미지 변경 함수
const triggerFileInput = () => {
  fileInput.value.click();
};

const changeProfileImage = async (event) => {
  const file = event.target.files[0]; // 파일 선택
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      cropImage(e.target.result); // 이미지 자르기 함수 호출
    };
    reader.readAsDataURL(file);
  }
};

const cropImage = (dataUrl) => {
  const canvas = document.createElement("canvas");
  const ctx = canvas.getContext("2d");
  const img = new Image();
  img.onload = () => {
    const size = 250; // 원하는 크기
    canvas.width = size;
    canvas.height = size;
    ctx.clearRect(0, 0, size, size);
    ctx.beginPath();
    ctx.arc(size / 2, size / 2, size / 2, 0, Math.PI * 2);
    ctx.clip();
    ctx.drawImage(img, 0, 0, size, size);
    croppedImageUrl.value = canvas.toDataURL(); // 자른 이미지 URL 설정

    // 서버로 이미지 업로드
    canvas.toBlob(async (blob) => {
      const formData = new FormData();
      formData.append("image", blob, "profile.png");
      await profileImageUpdate(formData); // 이미지 업데이트 함수 호출
    });
  };
  img.src = dataUrl;
};
</script>

<template>
  <section class="wrap-mypage">
    <!-- 사용자 정보 컴포넌트 -->
    <div class="section">
      <div class="image-container">
        <div class="mypage-pic" v-if="!userInfo.profileImageUrl">
          <img src="@/assets/images/no-image.png" alt="IMG" />
        </div>
        <div class="mypage-pic" v-else>
          <img
            :src="`http://localhost:8080${userInfo.profileImageUrl}?t=${Date.now()}`"
            alt="IMG"
          />
        </div>
      </div>
      <input type="file" ref="fileInput" style="display: none" @change="changeProfileImage" />

      <div class="container-mypage-form-btn">
        <button class="mypage-form-btn" @click="triggerFileInput">이미지 변경</button>
        <button class="mypage-form-btn">테두리 변경</button>
      </div>

      <!-- 얇고 흐릿한 실선 추가 -->
      <hr class="separator-line" />

      <div class="user-info">
        <div class="input-group">
          <label for="email">이메일 :</label>
          <span name="email" id="email">{{ userInfo.email }}</span>
        </div>

        <div class="input-group">
          <label for="name">이름 :</label>
          <span name="name" id="name">{{ userInfo.name }}</span>
        </div>

        <div class="input-group">
          <label for="cur-password">현재 비밀번호 :</label>
          <input type="password" name="cur-password" id="cur-password" />
        </div>

        <div class="input-group">
          <label for="new-password">새 비밀번호 :</label>
          <input type="password" name="new-password" id="new-password" />
        </div>

        <div class="input-group">
          <label for="confirm-password">새 비밀번호 재입력 :</label>
          <input type="password" name="confirm-password" id="confirm-password" />
        </div>

        <div class="input-group">
          <label for="phone">전화번호 :</label>
          <input type="text" name="phone" id="phone" :value="userInfo.phone" />
        </div>
      </div>

      <div class="container-mypage-form-btn">
        <router-link :to="{ name: 'user-mypage' }" class="mypage-form-btn">취소</router-link>
        <router-link :to="{ name: 'user-mypage' }" @click.prevent="" class="mypage-form-btn"
          >변경</router-link
        >
      </div>
    </div>
  </section>
</template>

<style scoped>
.wrap-mypage {
  width: 100%;
  max-width: 960px;
  background: #fff;
  border-radius: 10px;
  overflow: hidden;
  margin-top: 60px;
  padding: 55px 55px 37px 55px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.section {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.image-container {
  position: relative;
  width: 250px;
  height: 250px;
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px; /* 이미지 아래 간격 추가 */
}

.mypage-pic img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  border: 5px solid rgb(0, 0, 0, 0.1);
  object-fit: cover;
}

.container-mypage-form-btn {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

.container-mypage-form-btn router-link {
  padding-top: 100px;
}

.mypage-form-btn {
  font-family: "Montserrat", sans-serif;
  font-size: 18px;
  line-height: 1.5;
  color: #fff;
  text-transform: uppercase;
  width: 35%;
  height: 50px;
  border-radius: 25px;
  background: #57b846;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 25px;
  transition: all 0.4s;
  border: none;
  cursor: pointer;
}

.separator-line {
  width: 50%;
  border: 0;
  border-top: 2px solid rgba(0, 0, 0, 0.5);
  margin: 50px 0;
}

.user-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.input-group {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.input-group label {
  width: 30%;
  font-weight: bold;
}

.input-group span,
.input-group input {
  width: 65%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
