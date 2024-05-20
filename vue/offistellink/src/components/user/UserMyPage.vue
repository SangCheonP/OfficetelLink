<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const { userInfo } = storeToRefs(userStore);
const { userLogout } = userStore;

// 로그아웃 함수
const logout = () => {
  userLogout();
  alert("로그아웃 되었습니다.");
};

// 탭 상태 관리
const activeTab = ref("recentPosts");

// 예시 데이터
const recentPosts = ref([
  "최근 게시글 1",
  "최근 게시글 2",
  "최근 게시글 3",
  "최근 게시글 4",
  "최근 게시글 5",
  "최근 게시글 6",
  "최근 게시글 7",
  "최근 게시글 8",
  "최근 게시글 9",
  "최근 게시글 10",
]);

const recentComments = ref([
  "최근 댓글 1",
  "최근 댓글 2",
  "최근 댓글 3",
  "최근 댓글 4",
  "최근 댓글 5",
  "최근 댓글 6",
  "최근 댓글 7",
  "최근 댓글 8",
  "최근 댓글 9",
  "최근 댓글 10",
]);

const savedPosts = ref([
  "저장한 게시글 1",
  "저장한 게시글 2",
  "저장한 게시글 3",
  "저장한 게시글 4",
  "저장한 게시글 5",
  "저장한 게시글 6",
  "저장한 게시글 7",
  "저장한 게시글 8",
  "저장한 게시글 9",
  "저장한 게시글 10",
]);

onMounted(() => {
  console.log(userInfo.value);
});
</script>

<template>
  <section class="wrap-mypage">
    <!-- 사용자 정보 컴포넌트 -->
    <div class="user-info-section">
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
      <div class="mypage-info">
        <div class="user-info">
          <div class="user-info-field">
            <label>이름 :</label>
            <span>{{ userInfo.name }}</span>
          </div>
          <div class="user-info-field">
            <label>이메일 :</label>
            <span>{{ userInfo.email }}</span>
          </div>
          <div class="user-info-field user-info-field-addition">
            <label>게시글 :</label>
            <span>15 </span>
            <label>댓글 :</label>
            <span>3 </span>
            <label>포인트 :</label>
            <span>{{ userInfo.point }} </span>
          </div>
        </div>
        <div class="container-mypage-form-btn">
          <router-link :to="{ name: 'user-modify' }" class="mypage-form-btn">정보 수정</router-link>
          <router-link :to="{ name: 'home' }" class="mypage-form-btn" @click="logout"
            >로그아웃</router-link
          >
        </div>
      </div>
    </div>

    <!-- 유저 경험치바 -->
    <div class="user-experience">
      <div class="user-experience-labels">
        <span class="current-rank">Gold III 1172</span>
        <span class="next-rank">Gold II 승급까지 -78</span>
      </div>
      <div class="progress-bar-container">
        <div class="progress-bar" style="width: 60%"></div>
      </div>
    </div>

    <!-- 얇고 흐릿한 실선 추가 -->
    <hr class="separator-line" />

    <!-- 탭 및 내용 표시 -->
    <div class="content-section">
      <div class="container-mypage-tabs">
        <button
          class="mypage-tab-btn"
          :class="{ active: activeTab === 'recentPosts' }"
          @click="activeTab = 'recentPosts'"
        >
          최근 게시글
        </button>
        <button
          class="mypage-tab-btn"
          :class="{ active: activeTab === 'recentComments' }"
          @click="activeTab = 'recentComments'"
        >
          최근 댓글
        </button>
        <button
          class="mypage-tab-btn"
          :class="{ active: activeTab === 'savedPosts' }"
          @click="activeTab = 'savedPosts'"
        >
          저장한 게시글
        </button>
      </div>

      <div class="mypage-content">
        <ul v-if="activeTab === 'recentPosts'">
          <li v-for="(post, index) in recentPosts" :key="index">{{ post }}</li>
        </ul>
        <ul v-if="activeTab === 'recentComments'">
          <li v-for="(comment, index) in recentComments" :key="index">
            {{ comment }}
          </li>
        </ul>
        <ul v-if="activeTab === 'savedPosts'">
          <li v-for="(post, index) in savedPosts" :key="index">{{ post }}</li>
        </ul>
      </div>
    </div>

    <div class="text-center p-t-12">
      <router-link class="txt2" :to="{ name: 'home' }">홈으로 돌아가기</router-link>
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

.user-info-section {
  width: 100%;
  display: flex;
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
}

.mypage-pic img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  border: 5px solid rgb(0, 0, 0, 0.1);
  object-fit: cover;
}

.mypage-info {
  flex: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding-left: 20px;
}

.user-info {
  text-align: center;
  width: 100%;
}

.user-info-field {
  margin-bottom: 10px;
}

.user-info-field label {
  font-family: "Poppins", sans-serif;
  font-weight: bold;
  font-size: 18px;
  color: #666666;
  line-height: 1.5;
  padding-right: 13px;
}

.user-info-field span {
  font-family: "Poppins", sans-serif;
  font-size: 20px;
  color: #333333;
  line-height: 1.5;
}

.user-info-field-addition {
  padding-bottom: 10px;
}

.user-info-field-addition span {
  padding-right: 20px;
}

.user-experience {
  width: 100%;
  margin-bottom: 20px;
}

.user-experience-labels {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.current-rank {
  font-family: "Poppins", sans-serif;
  font-size: 16px;
  color: #ec9a00;
  font-weight: bold;
}

.next-rank {
  font-family: "Poppins", sans-serif;
  font-size: 16px;
  color: #666666;
}

.progress-bar-container {
  position: relative;
  height: 24px;
  width: 100%;
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

.separator-line {
  width: 50%;
  border: 0;
  border-top: 2px solid rgba(0, 0, 0, 0.5);
  margin: 30px 0;
}

.container-mypage-form-btn {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

.mypage-form-btn {
  font-family: "Montserrat", sans-serif;
  font-size: 18px;
  line-height: 1.5;
  color: #fff;
  text-transform: uppercase;
  width: 45%;
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

.content-section {
  width: 100%;
}

.container-mypage-tabs {
  width: 100%;
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.mypage-tab-btn {
  font-family: "Montserrat", sans-serif;
  font-size: 18px;
  line-height: 1.5;
  color: #333333;
  text-transform: uppercase;
  width: 30%;
  height: 50px;
  border-radius: 25px;
  background: #e6e6e6;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 25px;
  transition: all 0.4s;
  border: none;
  cursor: pointer;
}

.mypage-tab-btn.active {
  background: #57b846;
  color: #fff;
}

.mypage-content {
  width: 100%;
  margin-top: 20px;
}

.mypage-content ul {
  list-style: none;
  padding: 0;
}

.mypage-content li {
  font-family: "Poppins", sans-serif;
  font-size: 16px;
  color: #333333;
  padding: 10px 0;
  border-bottom: 1px solid #e6e6e6;
}

.text-center {
  text-align: center;
}

.p-t-12 {
  padding-top: 12px;
}

.txt2 {
  font-family: "Poppins", sans-serif;
  font-size: 18px;
  color: #57b846;
  line-height: 1.5;
}

.txt2:hover {
  text-decoration: underline;
}
</style>
