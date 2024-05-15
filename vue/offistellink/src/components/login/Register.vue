<script setup>
import axios from "axios";
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

// Form data
const email = ref("");
const password = ref("");
const name = ref("");
const phone = ref("");
const verificationCode = ref("");

const router = useRouter();

// 이메일 형식 검사
const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const isEmailValid = computed(() => emailPattern.test(email.value));

// Handle form submission
const handleSubmit = () => {
  if (!isEmailValid.value) {
    alert("유효한 이메일을 입력하세요.");
    return;
  }
  // Implement your registration logic here
  console.log("Email:", email.value);
  console.log("Verification Code:", verificationCode.value);
  console.log("Name:", name.value);
  console.log("Password:", password.value);
  console.log("Phone:", phone.value);
  // Redirect or show success message
  router.push("/");
};

// 이메일 인증 코드 보내는 함수
const sendEmailAuthenticationCode = () => {
  if (isEmailValid.value === true) {
    console.log("Email verification for:", email.value);
    axios.post({});
  } else {
    alert("유효한 이메일을 입력하세요.");
    return;
  }
};

// Handle code verification
const handleCodeVerification = () => {
  // Implement your code verification logic here
  console.log("Verification code:", verificationCode.value);
};
</script>

<template>
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100 row">
        <form
          class="login100-form validate-form col-12 col-lg-6 d-flex flex-column align-items-center justify-content-center"
          @submit.prevent="handleSubmit"
        >
          <span class="login100-form-title"> 회원가입 </span>

          <div
            class="wrap-input100 validate-input d-flex align-items-center"
            :class="{ 'invalid-input': !isEmailValid && email.value }"
            data-validate="Valid email is required: ex@abc.xyz"
          >
            <input
              class="input100"
              type="email"
              name="email"
              placeholder="이메일"
              v-model="email"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-envelope" aria-hidden="true"></i>
            </span>
            <button
              @click.prevent="sendEmailAuthenticationCode"
              class="email-verify-btn"
            >
              인증
            </button>
          </div>
          <div v-if="email.value && !isEmailValid" class="error-message">
            유효한 이메일 형식을 입력하세요.
          </div>

          <div
            class="wrap-input100 validate-input d-flex align-items-center"
            data-validate="Verification code is required"
          >
            <input
              class="input100"
              type="text"
              name="verificationCode"
              placeholder="인증 코드"
              v-model="verificationCode"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-check" aria-hidden="true"></i>
            </span>
            <button
              @click.prevent="handleCodeVerification"
              class="code-verify-btn"
            >
              체크
            </button>
          </div>

          <div
            class="wrap-input100 validate-input"
            data-validate="Name is required"
          >
            <input
              class="input100"
              type="text"
              name="name"
              placeholder="이름"
              v-model="name"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-user" aria-hidden="true"></i>
            </span>
          </div>

          <div
            class="wrap-input100 validate-input"
            data-validate="Password is required"
          >
            <input
              class="input100"
              type="password"
              name="password"
              placeholder="비밀번호"
              v-model="password"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-lock" aria-hidden="true"></i>
            </span>
          </div>

          <div
            class="wrap-input100 validate-input"
            data-validate="Phone number is required"
          >
            <input
              class="input100"
              type="text"
              name="phone"
              placeholder="전화번호"
              v-model="phone"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-phone" aria-hidden="true"></i>
            </span>
          </div>

          <div class="container-login100-form-btn">
            <button class="login100-form-btn">가입하기</button>
          </div>

          <div class="text-center p-t-36">
            <router-link class="txt2" :to="{ name: 'Login' }">
              이미 계정이 있으신가요? <br />
              로그인 하러 가기
              <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
            </router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 기존 스타일 */

@import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
@import url("https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css");
@import url("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");

.invalid-input input {
  border-color: red;
}

.error-message {
  color: red;
  font-size: 0.875em;
  margin-top: 5px;
}

.limiter {
  width: 100%;
  margin: 0 auto;
}

.container-login100 {
  width: 100%;
  min-height: 100vh;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  padding: 15px;
  background: #9053c7;
  background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
  background: linear-gradient(-135deg, #c850c0, #4158d0);
}

.wrap-login100 {
  width: 60%;
  max-width: 960px;
  background: #fff;
  border-radius: 10px;
  overflow: hidden;
  padding: 55px 55px 37px 55px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  -moz-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -webkit-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -o-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -ms-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
}

.login100-form {
  width: 100%;
}

.login100-form-title {
  font-family: Poppins-Medium;
  font-size: 30px;
  color: #333333;
  line-height: 1.2;
  text-align: center;
  width: 100%;
  display: block;
  padding-bottom: 54px;
}

.wrap-input100 {
  position: relative;
  width: 120%;
  z-index: 1;
  margin-bottom: 20px;
  display: flex; /* Flexbox 추가 */
  align-items: center; /* 중앙 정렬 */
}

.input100 {
  font-family: Poppins-Medium;
  font-size: 15px;
  color: #666666;
  line-height: 1.5;
  display: block;
  width: calc(100% - 90px); /* 버튼 크기에 맞게 조정 */
  background: #e6e6e6;
  height: 60px;
  border-radius: 25px 0 0 25px; /* 왼쪽 테두리 둥글게 */
  padding: 0 30px 0 68px;
  border: 1px solid #e6e6e6; /* 테두리 추가 */
}

.email-verify-btn,
.code-verify-btn {
  font-size: 16px;
  color: #fff;
  background: #57b846;
  border: none;
  border-radius: 0 25px 25px 0; /* 오른쪽 테두리 둥글게 */
  padding: 10px 20px;
  height: 60px;
  cursor: pointer;
  width: 90px; /* 고정된 버튼 너비 */
  border: 1px solid #57b846; /* 테두리 추가 */
}

.input100 + .email-verify-btn,
.input100 + .code-verify-btn {
  margin-left: -1px; /* 테두리 겹침 방지 */
}

.focus-input100 {
  display: none; /* 숨김 */
}

.symbol-input100 {
  font-size: 15px;
  color: #666666;
  display: flex;
  align-items: center;
  position: absolute;
  border-radius: 25px;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding-left: 35px;
  pointer-events: none;
  transition: all 0.4s;
}

.input100:focus + .symbol-input100 {
  color: #57b846;
  padding-left: 28px;
}

.container-login100-form-btn {
  width: 120%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.login100-form-btn {
  font-family: Montserrat-Bold;
  font-size: 18px;
  line-height: 1.5;
  color: #fff;
  text-transform: uppercase;
  width: 100%;
  height: 50px;
  border-radius: 25px;
  background: #57b846;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 25px;
  transition: all 0.4s;
  border: 1px solid #57b846; /* 테두리 추가 */
}

.login100-form-btn:hover {
  background: #333333;
  border: 1px solid #333333; /* 테두리 색상 변경 */
}

.text-center {
  text-align: center;
}

.p-t-12 {
  padding-top: 12px;
}

.p-t-36 {
  padding-top: 36px;
}

.txt1 {
  font-family: Poppins-Regular;
  font-size: 18px;
  color: #666666;
  line-height: 1.5;
}

.txt2 {
  font-family: Poppins-Regular;
  font-size: 15px;
  color: #57b846;
  line-height: 1.5;
}

.txt2:hover {
  text-decoration: underline;
}

.m-l-5 {
  margin-left: 5px;
}

/* New responsive styles */

/* Additional styles for Name, Password, Phone inputs */
.wrap-input100 .input100,
.wrap-input100 .symbol-input100 {
  border-radius: 25px; /* Both left and right corners rounded */
}

.input100 {
  width: 100%; /* Full width */
}

/* Specific adjustments for email verification and code verification inputs */
.wrap-input100.d-flex.align-items-center .input100 {
  width: calc(
    100% - 90px
  ); /* Keep the width adjustment for email and code verification fields */
  border-radius: 25px 0 0 25px; /* Left side rounded */
}

.wrap-input100.d-flex.align-items-center .email-verify-btn,
.wrap-input100.d-flex.align-items-center .code-verify-btn {
  border-radius: 0 25px 25px 0; /* Right side rounded */
}
</style>
