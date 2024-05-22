<script setup>
import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const { userInfo, borderImages } = storeToRefs(userStore);
const { updateUserProfileBorderAndExp } = userStore;

const props = defineProps({
  isOpen: Boolean,
});

const emit = defineEmits(["close"]);

// 선택된 탭 및 테두리 이미지 관련 상태
const selectedTab = ref("테두리");
const selectedImage = ref(userInfo.value.borderId); // 선택된 테두리 이미지

// 모달이 열릴 때 selectedImage 값을 초기화
watch(
  () => props.isOpen,
  (newValue) => {
    if (newValue) {
      selectedImage.value = userInfo.value.borderId;
    }
  }
);

const closeModal = () => {
  emit("close");
};

const applyChanges = async () => {
  const borderIndex = selectedImage.value;
  const experienceIndex = 0; // 경험치의 인덱스를 설정하세요. 예제에서는 0으로 설정.

  // 프로필 테두리와 경험치 업데이트
  await updateUserProfileBorderAndExp(
    userInfo.value.email,
    borderIndex,
    experienceIndex
  );

  // userInfo.borderId를 업데이트
  userInfo.value.borderId = borderIndex;

  // 모달 닫기
  closeModal();
};

const selectBorderImage = (index) => {
  selectedImage.value = index;
};
</script>

<template>
  <div class="modal-overlay" @click.self="closeModal" v-if="props.isOpen">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="borderModalLabel">프로필 변경</h5>
        <button
          type="button"
          class="btn-close"
          aria-label="Close"
          @click.prevent="closeModal"
        ></button>
      </div>
      <div class="modal-body">
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
      </div>
      <div class="modal-footer">
        <button
          type="button"
          class="btn btn-primary w-100"
          @click="applyChanges"
        >
          적용하기
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  width: 50%;
  height: 70%;
  padding: 20px;
  border-radius: 10px;
  position: relative;
}

.modal-header {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

.modal-title {
  font-family: "Poppins", sans-serif;
  font-size: 24px;
  flex-grow: 1;
  text-align: center;
}

.btn-close {
  position: absolute;
  right: 10px;
  top: 10px;
}

.modal-body {
  padding-top: 22px;
}

.preview-section {
  width: 40%;
}

.preview-section > h3 {
  padding-bottom: 20px;
}

.preview-border-container {
  position: relative;
  width: 200px; /* 이미지와 테두리 크기에 맞게 조정 */
  height: 200px; /* 이미지와 테두리 크기에 맞게 조정 */
  margin: 0 auto;
}

.preview-image {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 160px; /* 이미지 크기 */
  height: 160px; /* 이미지 크기 */
  transform: translate(-50%, -50%);
  border-radius: 50%;
  z-index: 1;
}

.profile-img {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 170px; /* 이미지 크기 */
  height: 170px; /* 이미지 크기 */
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
