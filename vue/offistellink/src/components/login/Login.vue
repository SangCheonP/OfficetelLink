<script setup>
import { ref } from "vue";

// store import
import { useLoginStore } from "@/stores/login.js";

// login
const loginStore = useLoginStore();
</script>

<template>
  <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#exampleModal"
    data-bs-whatever="@mdo"
  >
    Open modal for @mdo
  </button>

  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">New message</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="recipient-name" class="col-form-label"
                >Recipient:</label
              >
              <input type="text" class="form-control" id="recipient-name" />
            </div>
            <div class="mb-3">
              <label for="message-text" class="col-form-label">Message:</label>
              <textarea class="form-control" id="message-text"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn btn-primary">Send message</button>
        </div>
      </div>
    </div>
  </div>

  <div display="flex" class="css-8xlz89">
    <div display="flex" class="css-16q515s">
      <h1 color="$color_text_primary" class="css-1g6zb7b">뤼튼 시작하기</h1>
      <h2 color="$color_text_secondary" class="css-2rpix6">
        뤼튼과 함께
        <span color="$color_text_brand" class="css-141n3q4"
          >AI의 끝없는 가능성</span
        >을 탐험해 보세요
      </h2>
    </div>
    <div display="flex" class="css-1l9y1lg">
      <div display="flex" class="css-1360r14">
        <div display="flex" width="100%" class="css-1qkadj3">
          <p color="$color_text_primary" class="css-14r9c51">아이디</p>
        </div>
        <div display="flex" class="css-1les9v3">
          <input
            color="$color_text_primary"
            width="350px"
            height="48px"
            display="flex"
            type="email"
            id="email"
            placeholder="이메일 주소를 입력해주세요"
            autocomplete="on"
            required=""
            class="css-82mhcl"
            name="email"
            value=""
          />
        </div>
      </div>
      <div display="flex" class="css-1360r14">
        <p color="$color_text_primary" class="css-14r9c51">비밀번호</p>
        <div width="fit-content" class="css-3fim88">
          <input
            color="$color_text_primary"
            width="350px"
            height="48px"
            display="flex"
            type="password"
            id="password"
            placeholder="비밀번호를 입력해주세요"
            maxlength="15"
            autocomplete="on"
            required=""
            class="css-82mhcl"
            name="password"
            value=""
          />
          <div width="20px" height="20px" class="css-1cur2p2">
            <img
              alt="hide password"
              loading="lazy"
              width="20"
              height="20"
              decoding="async"
              data-nimg="1"
              style="color: transparent"
              src=""
            />
          </div>
        </div>
        <div height="12px" class="css-1b08fi0"></div>
      </div>
      <div display="flex" class="css-1s9dlem">
        <button
          disabled=""
          display="flex"
          width="100%"
          height="48px"
          color="$color_text_ivory"
          class="css-wk1976"
        >
          <div display="flex" class="css-e0yxdt">로그인</div>
        </button>
        <div display="flex" class="css-rzhiqp">
          <p color="$color_text_tertiary" class="css-k1r76r">
            <a href="/find/id?redirect_uri=https%3A%2F%2Fwrtn.ai%2F"
              >아이디 찾기</a
            >
          </p>
          <div width="1px" height="14px" class="css-8xybe3"></div>
          <p color="$color_text_tertiary" class="css-k1r76r">
            <a href="/find/password?redirect_uri=https%3A%2F%2Fwrtn.ai%2F"
              >비밀번호 찾기</a
            >
          </p>
        </div>
      </div>
    </div>
    <a
      style="width: 100%"
      href="/pass/signup?redirect_uri=https%3A%2F%2Fwrtn.ai%2F&amp;provider=local"
      ><div display="flex" width="100%" height="48px" class="css-1my21qh">
        <p color="$color_text_tertiary" class="css-14g2rrm">
          뤼튼이 처음이에요
          <span color="$color_action_blue_primary" class="css-1o3qo12"
            >가입하기</span
          >
        </p>
      </div></a
    >
  </div>
</template>

<style scoped>
.backdrop {
  z-index: 999;
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: center;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.modal-container {
  background: white;
  max-width: 320px;
  width: 100%;
  padding: 1.5rem;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
