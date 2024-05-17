import { defineStore } from "pinia";
import { ref } from "vue";
import { useRouter } from "vue-router";

import { findByEmail, userConfirm } from "@/api/user";
import { httpStatusCode } from "@/util/http-status";
import { jwtDecode } from "jwt-decode";

export const useUserStore = defineStore("userStore", () => {
  const router = useRouter();

  const isLogin = ref(false);
  const isLoginError = ref(false);
  const userInfo = ref(null);
  const isValidToken = ref(false);

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("로그인 성공!!!");

          let { data } = response;
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];

          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;

          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);
        }
      },
      (error) => {
        console.log("로그인 실패!!!!");

        isLogin.value = false;
        isLoginError.value = true;
        isValidToken.value = false;

        console.error(error);
      }
    );
  };

  const getUserInfo = async (token) => {
    let decodeToken = jwtDecode(token);
    // console.log("decodeToken: " + decodeToken);
    await findByEmail(
      decodeToken.email,
      (response) => {
        console.log(response)
        if (response.status === httpStatusCode.OK) {
          userInfo.value = response.data.userInfo;
          console.log(userInfo.value)
        } else {
          console.log("유저 정보 없음!!!!");
        }
      },
      async (error) => {
        console.error(
          "g[토큰 만료되어 사용 불가능.] : ",
          error.response.status,
          error.response.statusText
        );
        isValidToken.value = false;

        //   await tokenRegenerate()
      }
    );
  };
  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    userLogin,
    getUserInfo,
  };
});
