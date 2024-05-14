<template>
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100 row">
        <form
          class="login100-form validate-form col-12 col-lg-8 d-flex flex-column align-items-center justify-content-center"
          @submit.prevent="handleSubmit"
        >
          <span class="login100-form-title"> 회원가입 하기 </span>

          <div class="wrap-input100 validate-input d-flex align-items-center" data-validate="Valid email is required: ex@abc.xyz">
            <input
              class="input100"
              type="text"
              name="email"
              placeholder="Email"
              v-model="email"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-envelope" aria-hidden="true"></i>
            </span>
            <button @click.prevent="handleEmailVerification" class="email-verify-btn">인증</button>
          </div>

          <div class="wrap-input100 validate-input d-flex align-items-center" data-validate="Verification code is required">
            <input
              class="input100"
              type="text"
              name="verificationCode"
              placeholder="Verification Code"
              v-model="verificationCode"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-check" aria-hidden="true"></i>
            </span>
            <button @click.prevent="handleCodeVerification" class="code-verify-btn">체크</button>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Name is required">
            <input
              class="input100"
              type="text"
              name="name"
              placeholder="Name"
              v-model="name"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-user" aria-hidden="true"></i>
            </span>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Password is required">
            <input
              class="input100"
              type="password"
              name="password"
              placeholder="Password"
              v-model="password"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-lock" aria-hidden="true"></i>
            </span>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Phone number is required">
            <input
              class="input100"
              type="text"
              name="phone"
              placeholder="Phone Number"
              v-model="phone"
            />
            <span class="focus-input100"></span>
            <span class="symbol-input100">
              <i class="fa fa-phone" aria-hidden="true"></i>
            </span>
          </div>

          <div class="container-login100-form-btn">
            <button class="login100-form-btn">Register</button>
          </div>

          <div class="text-center p-t-36">
            <a class="txt2" href="/login">
              Already have an account? Login
              <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
            </a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

// Form data
const email = ref("");
const password = ref("");
const name = ref("");
const phone = ref("");
const verificationCode = ref("");

const router = useRouter();

// Handle form submission
const handleSubmit = () => {
  // Implement your registration logic here
  console.log("Email:", email.value);
  console.log("Verification Code:", verificationCode.value);
  console.log("Name:", name.value);
  console.log("Password:", password.value);
  console.log("Phone:", phone.value);
  // Redirect or show success message
  router.push("/");
};

// Handle email verification
const handleEmailVerification = () => {
  // Implement your email verification logic here
  console.log("Email verification for:", email.value);
};

// Handle code verification
const handleCodeVerification = () => {
  // Implement your code verification logic here
  console.log("Verification code:", verificationCode.value);
};
</script>

<style scoped>
@import url('https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css');
@import url('https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css');
@import url('https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css');

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
  width: 100%;
  z-index: 1;
  margin-bottom: 10px;
}

.input100 {
  font-family: Poppins-Medium;
  font-size: 15px;
  color: #666666;
  line-height: 1.5;
  display: block;
  width: 100%;
  background: #e6e6e6;
  height: 50px;
  border-radius: 25px;
  padding: 0 30px 0 68px;
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

.email-verify-btn, .code-verify-btn {
  font-size: 16px;
  color: #fff;
  background: #57b846;
  border: none;
  border-radius: 25px;
  padding: 10px 20px;
  margin-left: 10px;
  cursor: pointer;
}

.email-verify-btn:hover, .code-verify-btn:hover {
  background: #333333;
}

.container-login100-form-btn {
  width: 100%;
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

/* Other required CSS */
@keyframes anim-shadow {
  to {
    box-shadow: 0px 0px 70px 25px;
    opacity: 0;
  }
}
</style>
