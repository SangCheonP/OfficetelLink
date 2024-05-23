<script setup>
import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

// Pinia 스토어 가져오기
const userStore = useUserStore();
const { userInfo, borderImages } = storeToRefs(userStore);

// 선택된 탭 및 테두리 이미지 관련 상태
const selectedTab = ref("테두리");
const selectedImage = ref(userInfo.value.borderId); // 선택된 테두리 이미지

// 테두리 이미지 선택 함수
const selectBorderImage = (index) => {
  selectedImage.value = index;
};

const buy = () =>{
    alert("성공적으로 구매하였습니다.")
}

// 미리보기 이미지 URL 상태
const previewImageUrl = ref(`http://localhost:5173${borderImages[selectedImage.value]}?t=${Date.now()}`);

// selectedImage 값이 변경될 때마다 previewImageUrl 업데이트
watch(selectedImage, (newValue) => {
  previewImageUrl.value = `http://localhost:5173${borderImages[newValue]}?t=${Date.now()}`;
});
</script>

<template>
  <div class="page-container">
    <div class="content">
      <div class="header">
        <h5 class="title">상점</h5>
        <router-link to="/home" class="close-button">×</router-link>
      </div>
      <div class="body">
        <div class="d-flex justify-content-between">
          <div class="preview-section text-center">
            <h3>미리보기</h3>
            <div class="preview-border-container">
                <img
                :src="`http://localhost:8080${
                  userInfo.profileImageUrl
                }?t=${Date.now()}`"
                alt="IMG"
                id="previewImg"
                class="preview-image"
              />
              <img
                :src="`http://localhost:5173${
                  borderImages[selectedImage]
                }?t=${Date.now()}`"
                alt="login"
                id="profileImg"
                class="profile-img"
              />
            </div>
            <div class="progress-bar-container">
              <div class="progress-bar" style="width: 60%"></div>
            </div>
          </div>

          <div class="options-section">
            <div class="nav nav-tabs mb-3" id="nav-tab" role="tablist">
              <button
                class="nav-link"
                :class="{ active: selectedTab === '테두리' }"
                @click="selectedTab = '테두리'"
              >
                테두리
              </button>
              <button
                class="nav-link"
                :class="{ active: selectedTab === '경험치' }"
                @click="selectedTab = '경험치'"
              >
                경험치
              </button>
            </div>
            <div class="options" v-if="selectedTab === '테두리'">
              <div
                class="option border rounded p-2 m-1"
                v-for="(image, index) in borderImages"
                :key="index"
                :class="{ selected: selectedImage === index }"
                @click="() => selectBorderImage(index)"
              >
                <img :src="image" class="border-thumbnail" />
              </div>
            </div>
          </div>
        </div>
        <div class="footer">
          <router-link :to="{name: 'home'}" class="btn btn-primary w-100" @click="buy">
            구매하기
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');

.page-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: 100vh;
  padding-top: 100px; /* 헤더와 흰 박스 사이의 공간을 조정 */
}

.content {
  background: white;
  width: 1000px; /* 흰색 배경의 폭을 2배로 늘림 */
  max-width: 90%; /* 화면 크기에 따라 유동적으로 조절되도록 설정 */
  margin: 0 auto;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  font-family: 'Noto Sans KR', sans-serif;
}

.header {
  display: flex;
  justify-content: space-between; /* 제목과 닫기 버튼을 양쪽 끝으로 배치 */
  align-items: center;
  position: relative;
  margin-bottom: 20px;
}

.title {
  font-size: 24px;
  text-align: center;
  flex-grow: 1;
}

.close-button {
  font-size: 24px;
  text-decoration: none;
  color: black;
  padding: 0 10px;
}

.body {
  padding-top: 22px;
}

.points-section {
  margin-left: auto;
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.preview-section {
  width: 40%;
}

.preview-section > h3 {
  padding-bottom: 20px;
}

.preview-border-container {
  position: relative;
  width: 200px;
  height: 200px;
  margin: 0 auto;
}

.preview-image {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 160px;
  height: 160px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  z-index: 1;
}

.profile-img {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 170px;
  height: 170px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  z-index: 0;
}

.progress-bar-container {
  position: relative;
  height: 16px;
  width: 100%;
  margin: 25px 0;
  background: #0b131b;
  border-radius: 12px;
  overflow: hidden;
}

.progress-bar {
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  border-top-right-radius: 12px;
  border-bottom-right-radius: 12px;
  background: linear-gradient(to right, #ec9a00 0%, #ffd000 100%);
}

.options-section {
  width: 55%;
}

.options {
  display: flex;
  flex-wrap: wrap;
}

.option {
  width: 65px;
  height: 65px;
  margin: 4px;
  border: 2px solid transparent;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: transform 0.2s;
}

.option.selected {
  border-color: red;
  transform: scale(1.1);
}

.border-thumbnail {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 4px;
}

.footer {
  margin-top: 20px;
}

/* 미디어 쿼리를 사용하여 화면 크기에 따라 이미지 크기 조정 */
@media (max-width: 768px) {
  .preview-border-container {
    width: 150px;
    height: 150px;
  }

  .preview-image,
  .profile-img {
    width: 120px;
    height: 120px;
  }
}

@media (max-width: 480px) {
  .preview-border-container {
    width: 100px;
    height: 100px;
  }

  .preview-image,
  .profile-img {
    width: 80px;
    height: 80px;
  }
}
</style>
