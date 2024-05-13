<script setup>
import { ref } from "vue";

// store import
import { useLoginStore } from "@/stores/login.js";

// login
const loginStore = useLoginStore();
</script>

<template>
  <div>
    <nav
      class="navbar navbar-expand-lg navbar-light px-5"
      style="background-color: #e3f2fd"
    >
      <div class="container-fluid d-flex justify-content-between">
        <!-- Logo -->
        <a class="navbar-brand" href="#">OfficetelLink</a>

        <!-- Toggle button for offcanvas sidebar -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasNavbar"
          aria-controls="offcanvasNavbar"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Offcanvas sidebar -->
        <div
          class="offcanvas offcanvas-end"
          tabindex="-1"
          id="offcanvasNavbar"
          aria-labelledby="offcanvasNavbarLabel"
          style="width: 250px"
        >
          <div class="offcanvas-header">
            <h5
              class="offcanvas-title"
              id="offcanvasNavbarLabel"
              style="vertical-align: inherit"
            >
              OfficetelLink
            </h5>
            <!-- 토글 닫는 버튼-->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="offcanvas"
              aria-label="Close"
            ></button>
          </div>
          <div class="offcanvas-body">
            <hr class="d-lg-none text-black-50" />
            <!-- Links -->
            <a
              class="nav-link fw-light"
              aria-current="page"
              href="#"
              style="vertical-align: inherit"
            >
              홈
            </a>
            <a
              class="nav-link fw-light"
              href="#"
              style="vertical-align: inherit"
              >게시판
            </a>
          </div>
        </div>

        <!-- Center search button, visible only on large screens -->
        <button class="image-button d-none d-lg-block" type="button">
          <img id="searchBarImg" src="../assets/search-bar.png" alt="Search" />
        </button>

        <!-- Right login button, visible only on large screens -->
        <button
          class="image-button d-none d-lg-block"
          type="button"
          data-bs-toggle="modal"
          data-bs-target="#loginModal"
        >
          <img id="loginImg" src="../assets/login.png" alt="Login" />
        </button>
      </div>

      <!-- login Modal-->
      <div
        class="modal fade"
        id="loginModal"
        tabindex="-1"
        aria-labelledby="loginModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title">OfficetelLink 시작하기</h1>
            </div>
            <div class="modal-body">
              <form>
                <div class="mb-3">
                  <label for="email" class="form-label">아이디</label>
                  <input
                    type="email"
                    class="form-control form-input"
                    id="email"
                    placeholder="이메일 주소를 입력해주세요"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">비밀번호</label>
                  <input
                    type="password"
                    class="form-control form-input"
                    id="password"
                    placeholder="비밀번호를 입력해주세요"
                    maxlength="15"
                    required
                  />
                </div>
                <button type="submit" class="btn btn-primary form-input">
                  로그인
                </button>
              </form>
              <div class="help-links">
                <a href="#">아이디 찾기</a> | <a href="#">비밀번호 찾기</a>
              </div>
            </div>
            <div class="modal-footer">
              <a class="btn btn-secondary form-input" href="#"
                >처음 사용해요? 가입하기</a
              >
              <a
                class="btn btn-secondary form-input"
                data-bs-dismiss="modal"
                aria-label="Close"
                href="#"
                >닫기</a
              >
            </div>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.form-input {
  width: 100%; /* Ensures full width inside their container */
}

.modal-dialog {
  max-width: 400px; /* Restricting modal width for better focus */
}

.modal-header,
.modal-body,
.modal-footer {
  padding: 1rem; /* Consistent padding */
}

.modal-body .form-label {
  display: block; /* Makes the label take the full line for clarity */
  color: #6c757d; /* Grey color for form labels */
  margin-bottom: 0.5rem; /* Space below the label */
}

.modal-body .form-control {
  margin-bottom: 1rem; /* Space below each input field */
}

.modal-footer {
  display: flex; /* Ensures the buttons in footer align correctly */
  justify-content: center; /* Center aligns the buttons */
  padding: 1rem;
}

.btn-primary,
.btn-secondary {
  width: 100%; /* Full width for buttons */
  display: block; /* Ensures the button takes the full width available */
}
.btn-primary {
  background-color: #007bff; /* Bootstrap primary blue */
  border-color: #007bff;
}
.help-links a {
  color: #007bff; /* Link color matching your primary color */
}

.btn-secondary {
  background-color: #6c757d; /* Grey for secondary buttons */
}

.modal-dialog {
  max-width: 400px; /* Restricting modal width for better focus */
}
.offcanvas-header {
  padding-bottom: 0rem; /* Optional: adjust padding to fine-tune the spacing */
}

.offcanvas-body {
  padding-top: 0rem; /* Reduces the top padding to bring contents closer to the header */
}

.nav-link:hover {
  color: #007bff; /* Changes text color on hover */
  font-weight: bold; /* Makes text bold on hover */

  transition: color 0.3s ease, transform 0.3s ease; /* Smooth transition for color and transform */
}

.image-button {
  border: none;
  background: none;
  padding: 0;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

#searchBarImg,
#loginImg {
  width: auto;
  height: auto;
  display: block;
  transition: opacity 0.3s ease, transform 0.3s ease;
}

#searchBarImg:hover,
#loginImg:hover {
  opacity: 0.8;
  transform: scale(1.05);
}

#searchBarImg {
  width: 300px;
}

#loginImg {
  width: 60px;
}

.nav-link {
  margin-right: 15px; /* Right margin for nav items */
  padding: 7px 0;
}
</style>
