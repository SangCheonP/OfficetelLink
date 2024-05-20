import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail);
}

async function findByEmail(email, success, fail) {
  local.defaults.headers["Authorization"] =
    sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${email}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] =
    sessionStorage.getItem("refreshToken");
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(email, success, fail) {
  await local.get(`/user/logout/${email}`).then(success).catch(fail);
}

async function imageUpdate(email, image, success, fail) {
  await local
    .post(`/user/${email}/profileimage`, image, {
      headers: {
        "Content-Type": "multipart/form-data", // Content-Type 설정
      },
    })
    .then(success)
    .catch(fail); // 성공 및 실패 콜백 설정
}

async function checkPasswrod(password, success, fail) {
  await local
    .post(`/user/verify-password`, { email, password })
    .then(success)
    .catch(fail);
}

export {
  userConfirm,
  findByEmail,
  tokenRegeneration,
  logout,
  imageUpdate,
  checkPasswrod,
};
