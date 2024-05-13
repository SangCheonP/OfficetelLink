<script setup></script>

<template>
  <div>
    <nav
      class="navbar navbar-expand-lg navbar-light px-5"
      style="background-color: #e3f2fd"
    >
      <div
        class="container-fluid d-flex justify-content-around align-items-center"
      >
        <!-- 로고, 홈, 게시판: 왼쪽 -->
        <div class="d-flex align-items-center">
          <a class="navbar-brand fw-normal" href="#">OfficetelLink</a>
          <div class="d-flex d-none d-lg-flex align-items-center">
            <!-- 홈과 게시판을 포함하는 div -->
            <div class="nav-item mr-3">
              <a class="nav-link fw-light" aria-current="page" href="#">홈</a>
            </div>
            <div class="nav-item">
              <a class="nav-link fw-light" href="#">게시판</a>
            </div>
          </div>
        </div>

        <!-- 검색 버튼: 가운데 -->
        <button class="image-button d-none d-lg-block" type="button">
          <img id="searchBarImg" src="../assets/search-bar.png" alt="Search" />
        </button>

        <!-- 로그인 버튼: 오른쪽 -->
        <button class="image-button d-none d-lg-block" type="button">
          <img id="loginImg" src="../assets/login.png" alt="Login" />
        </button>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.image-button {
  border: none; /* Removes the default border */
  background: none; /* Removes the default background */
  padding: 0; /* Removes the default padding */
  cursor: pointer; /* Changes the cursor to a pointer to indicate it's clickable */
  transition: transform 0.3s ease, box-shadow 0.3s ease; /* Smooth transition for transform and box-shadow */
}

#searchBarImg,
#loginImg {
  width: auto; /* Auto width to respect height changes */
  height: auto; /* Maintains the aspect ratio of the image */
  display: block; /* Ensures the image takes up the entire space of the button */
  transition: opacity 0.3s ease, transform 0.3s ease; /* Smooth transition for opacity and transform */
}

#searchBarImg:hover,
#loginImg:hover {
  opacity: 0.8; /* Reduces opacity to 80% */
  transform: scale(1.05); /* Scales the image to 105% */
}

#searchBarImg {
  width: 300px; /* Sets a specific width for the image */
}

#loginImg {
  width: 60px; /* Sets a specific width for the image */
}

.nav-item {
  margin-right: 15px; /* Adds 20px margin to the right of each nav-item */
}
</style>
