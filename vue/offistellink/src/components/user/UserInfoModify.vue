<script setup>
import { ref, computed } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import Modal from "@/components/user/UserInfoModifyModal.vue";

const router = useRouter();

const userStore = useUserStore();
const { userInfo } = storeToRefs(userStore);
const {
  profileImageUpdate,
  updatePassword,
  updatePhoneNumber,
  checkCurrentPassword,
  updateUserProfile,
} = userStore;

const fileInput = ref(null);
const croppedImageUrl = ref(null);

const currentPassword = ref("");
const newPassword = ref("");
const confirmPassword = ref("");
const phoneInput = ref(userInfo.value.phone); // 전화번호 입력값을 ref로 관리
const errorMessage = ref("");

const isModalOpen = ref(false); // 모달 열림 여부 관리

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

// 전화번호 형식 변경 함수
const changePhoneFormat = (event) => {
  let input = event.target.value.replace(/[^\d-]/g, ""); // 숫자와 하이픈 이외의 문자 제거
  let formatted = "";

  if (input.length > 3) {
    formatted = input.slice(0, 3) + "-";
    if (input.length > 7) {
      formatted += input.slice(3, 7) + "-" + input.slice(7);
    } else {
      formatted += input.slice(3);
    }
  } else {
    formatted = input;
  }

  phoneInput.value = formatted;
};

// 비밀번호 일치 여부 확인
const passwordMismatch = computed(() => {
  return (
    newPassword.value &&
    confirmPassword.value &&
    newPassword.value !== confirmPassword.value
  );
});

const passwordMatch = computed(() => {
  return (
    newPassword.value &&
    confirmPassword.value &&
    newPassword.value === confirmPassword.value
  );
});

// 전화번호 형식 확인
const validPhoneNumber = computed(() => {
  const phonePattern = /^01[016789]-\d{3,4}-\d{4}$/;
  const input = phoneInput.value;

  // 입력값이 비어있으면 false 반환
  if (!input) return false;

  // 전화번호에 숫자와 하이픈 외의 다른 문자가 있는지 확인
  const containsOnlyValidChars = /^[\d-]+$/.test(input);
  const isValid = phonePattern.test(input) && containsOnlyValidChars;

  console.log(
    `isValid: ${isValid}, containsOnlyValidChars: ${containsOnlyValidChars}`
  );
  return isValid;
});

// 사용자 정보 업데이트 함수
const updateUserInfo = async () => {
  if (
    !(await checkCurrentPassword(userInfo.value.email, currentPassword.value))
  ) {
    errorMessage.value = "현재 비밀번호가 올바르지 않습니다.";
    return;
  }

  if (passwordMismatch.value) {
    errorMessage.value = "새 비밀번호가 일치하지 않습니다.";
    return;
  }

  if (!validPhoneNumber.value) {
    errorMessage.value = "전화번호 형식이 올바르지 않습니다.";
    return;
  }

  try {
    // 서버에 새 비밀번호와 전화번호 업데이트
    console.log(userInfo.value.email, newPassword.value, phoneInput.value);
    await updateUserProfile(
      userInfo.value.email,
      newPassword.value,
      phoneInput.value
    );
    alert("정보가 성공적으로 변경되었습니다.");
    router.replace("/user/mypage");
  } catch (error) {
    errorMessage.value = "정보 변경에 실패했습니다.";
  }
};

const openModal = () => {
  isModalOpen.value = true;
  console.log(isModalOpen.value);
};

const closeModal = () => {
  isModalOpen.value = false;
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
            :src="`http://localhost:8080${
              userInfo.profileImageUrl
            }?t=${Date.now()}`"
            alt="IMG"
          />
        </div>
      </div>
      <input
        type="file"
        ref="fileInput"
        style="display: none"
        @change="changeProfileImage"
      />

      <div class="container-mypage-form-btn">
        <button class="mypage-form-btn" @click="triggerFileInput">
          이미지 변경
        </button>
        <button class="mypage-form-btn" @click="openModal">테두리 변경</button>
      </div>

      <!-- 얇고 흐릿한 실선 추가 -->
      <hr class="separator-line" />

      <div class="user-info">
        <h3 class="user-info-title">정보 수정</h3>

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
          <input
            type="password"
            name="cur-password"
            id="cur-password"
            v-model="currentPassword"
          />
        </div>

        <div class="input-group">
          <label for="new-password">새 비밀번호 :</label>
          <input
            type="password"
            name="new-password"
            id="new-password"
            v-model="newPassword"
          />
        </div>

        <div class="input-group">
          <label for="confirm-password">새 비밀번호 재입력 :</label>
          <input
            type="password"
            name="confirm-password"
            id="confirm-password"
            v-model="confirmPassword"
          />
        </div>
        <div v-if="passwordMismatch" class="error-message">
          비밀번호가 일치하지 않습니다.
        </div>
        <div v-if="passwordMatch" class="success-message">
          비밀번호가 일치합니다.
        </div>

        <div class="input-group">
          <label for="phone">전화번호 :</label>
          <input
            type="text"
            name="phone"
            id="phone"
            placeholder="010-1234-5678"
            :value="phoneInput"
            @input="changePhoneFormat"
          />
        </div>
        <div v-if="!validPhoneNumber && phoneInput" class="error-message">
          전화번호 형식이 올바르지 않습니다.
        </div>
      </div>

      <div v-if="errorMessage" class="error-message-final">
        {{ errorMessage }}
      </div>

      <div class="container-mypage-form-btn">
        <router-link :to="{ name: 'user-mypage' }" class="mypage-form-btn"
          >취소</router-link
        >
        <button class="mypage-form-btn" @click="updateUserInfo">변경</button>
      </div>
    </div>

    <!-- 모달 컴포넌트 -->
    <Modal :isOpen="isModalOpen" @close="closeModal"></Modal>
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

.user-info-title {
  padding-bottom: 30px;
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
}

.input-group span {
  border: none;
  background: none;
}

.input-group input {
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* 이메일과 이름 필드에 대한 테두리 제거 */
.input-group span[name="email"],
.input-group span[name="name"] {
  border: none;
  background: none;
}

/* 경고 및 성공 메시지 스타일 */
.error-message {
  color: red;
  font-size: 14px;
  margin-top: -10px;
  margin-bottom: 10px;
}

.success-message {
  color: green;
  font-size: 14px;
  margin-top: -10px;
  margin-bottom: 10px;
}

.error-message-final {
  color: red;
  font-size: 17px;
  margin-top: 15px;
  margin-bottom: 20px;
}
</style>
