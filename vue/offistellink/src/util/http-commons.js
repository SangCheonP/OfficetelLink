import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    // withCredentials: true,
    // headers: {
    //   "Content-Type": "application/json;charset=utf-8",
    // },
  });

  // Request 발생 시 적용할 내용.
  instance.defaults.headers.common["Authorization"] = "";
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";

  return instance;
}

export{localAxios}
