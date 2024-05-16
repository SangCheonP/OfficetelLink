<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

// Form data
const email = ref("");
const password = ref("");

const router = useRouter();

// 로그인 함수
const userLogion = () => {
  console.log("Email:", email.value);
  console.log("Password:", password.value);
  // Redirect or show success message
  router.push("/");
};
</script>

<template>
  <div>
    <div class="limiter">
      <main class="container-login100">
        <section class="wrap-login100 row">
          <div
            class="login100-pic js-tilt d-none d-lg-block col-lg-6"
            data-tilt
          >
            <img src="@/assets/images/login-page-img.png" alt="IMG" />
          </div>

          <form
            class="login100-form validate-form col-12 col-lg-6 d-flex flex-column align-items-center justify-content-center"
            @submit.prevent="userLogion"
          >
            <h1 class="login100-form-title">로그인</h1>

            <div
              class="wrap-input100 validate-input"
              data-validate="Valid email is required: ex@abc.xyz"
            >
              <input
                class="input100"
                type="text"
                name="email"
                placeholder="이메일"
                v-model="email"
              />
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-envelope" aria-hidden="true"></i>
              </span>
            </div>

            <div
              class="wrap-input100 validate-input"
              data-validate="Password is required"
            >
              <input
                class="input100"
                type="password"
                name="pass"
                placeholder="비밀번호"
                v-model="password"
              />
              <span class="focus-input100"></span>
              <span class="symbol-input100">
                <i class="fa fa-lock" aria-hidden="true"></i>
              </span>
            </div>

            <div class="container-login100-form-btn">
              <button class="login100-form-btn">로그인</button>
            </div>

            <div class="text-center p-t-12">
              <router-link class="txt2" href="#">
                이메일 / 비밀번호 찾기
              </router-link>
            </div>

            <div class="text-center p-t-36">
              <router-link class="txt2" :to="{ name: 'Register' }">
                계정 생성하기
                <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
              </router-link>
            </div>
          </form>
        </section>
      </main>
    </div>
  </div>
</template>

<style scoped>
@import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
@import url("https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css");
@import url("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");

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
  width: 100%;
  max-width: 960px;
  background: #fff;
  border-radius: 10px;
  overflow: hidden;
  padding: 55px 55px 37px 55px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
  -moz-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -webkit-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -o-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
  -ms-box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.1);
}

.login100-pic {
  width: 100%;
  padding-right: 50px;
}

.login100-pic img {
  max-width: 100%;
}

.login100-form {
  width: 100%;
}

.login100-form-title {
  font-family: "Poppins", sans-serif;
  font-size: 45px;
  color: #333333;
  line-height: 1.2;
  text-align: center;
  width: 100%;
  display: block;
  padding-bottom: 45px;
}

.wrap-input100 {
  position: relative;
  width: 100%;
  z-index: 1;
  margin-bottom: 20px;
}

.input100 {
  font-family: "Poppins", sans-serif;
  font-size: 15px;
  color: #666666;
  line-height: 1.5;
  display: block;
  width: 100%;
  background: #e6e6e6;
  height: 50px;
  border-radius: 25px;
  padding: 0 30px 0 68px;
  border: none;
}

.focus-input100 {
  display: block;
  position: absolute;
  border-radius: 25px;
  bottom: 0;
  left: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
  box-shadow: 0px 0px 0px 0px;
  color: rgba(87, 184, 70, 0.8);
}

.input100:focus + .focus-input100 {
  -webkit-animation: anim-shadow 0.5s ease-in-out forwards;
  animation: anim-shadow 0.5s ease-in-out forwards;
}

.symbol-input100 {
  font-size: 15px;
  color: #666666;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
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
  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}

.input100:focus + .focus-input100 + .symbol-input100 {
  color: #57b846;
  padding-left: 28px;
}

.container-login100-form-btn {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.login100-form-btn {
  font-family: "Montserrat", sans-serif;
  font-size: 18px;
  line-height: 1.5;
  color: #fff;
  text-transform: uppercase;
  width: 100%;
  height: 50px;
  border-radius: 25px;
  background: #57b846;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 25px;
  -webkit-transition: all 0.4s;
  -o-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
  border: none;
}

.login100-form-btn:hover {
  background: #333333;
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
  font-family: "Poppins", sans-serif;
  font-size: 18px;
  color: #666666;
  line-height: 1.5;
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

.m-l-5 {
  margin-left: 5px;
}

@keyframes anim-shadow {
  to {
    box-shadow: 0px 0px 70px 25px;
    opacity: 0;
  }
}
</style>
