import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useLoginStore = defineStore("login", () => {
  const showLoginModal = ref(true);

  const changeModalStatus = () => {
    showLoginModal.value === true
      ? (showLoginModal.value = false)
      : (showLoginModal.value = true);

    console.log(showLoginModal.value);
  };

  return { showLoginModal, changeModalStatus };
});
