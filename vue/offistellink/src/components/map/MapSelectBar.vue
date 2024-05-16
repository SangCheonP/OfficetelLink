<template>
  <div class="input-group search-bar">
    <select
      :value="selectedGu"
      @input="$emit('update:selectedGu', $event.target.value)"
      class="form-select custom-select"
    >
      <option value="" disabled selected>구</option>
      <option value="대덕구">대덕구</option>
      <option value="동구">동구</option>
      <option value="서구">서구</option>
      <option value="유성구">유성구</option>
      <option value="중구">중구</option>
    </select>
    <select
      :value="selectedDong"
      @input="$emit('update:selectedDong', $event.target.value)"
      class="form-select custom-select"
    >
      <option value="" disabled selected>동</option>
      <option value="가양동">가양동</option>
      <option value="관평동">관평동</option>
      <option value="노은동">노은동</option>
      <option value="대흥동">대흥동</option>
      <option value="도마동">도마동</option>
      <option value="도안동">도안동</option>
      <option value="둔곡동">둔곡동</option>
      <option value="둔산동">둔산동</option>
      <option value="만년동">만년동</option>
      <option value="변동">변동</option>
      <option value="봉명동">봉명동</option>
      <option value="오류동">오류동</option>
      <option value="용계동">용계동</option>
      <option value="월평동">월평동</option>
      <option value="은행동">은행동</option>
      <option value="석봉동">석봉동</option>
      <option value="선화동">선화동</option>
      <option value="성남동">성남동</option>
      <option value="신성동">신성동</option>
      <option value="신탄진동">신탄진동</option>
      <option value="장대동">장대동</option>
      <option value="지족동">지족동</option>
      <option value="탄방동">탄방동</option>
    </select>
    <select
      :value="selectedRoad"
      @input="$emit('update:selectedRoad', $event.target.value)"
      class="form-select custom-select"
    >
      <option value="" disabled selected>도로명</option>
      <option value="가양로">가양로</option>
      <option value="계룡로">계룡로</option>
      <option value="과학지원">과학지원</option>
      <option value="노은로">노은로</option>
      <option value="노은서로">노은서로</option>
      <option value="대덕대로">대덕대로</option>
      <option value="대종로">대종로</option>
      <option value="대학로">대학로</option>
      <option value="대흥로">대흥로</option>
      <option value="도마로">도마로</option>
      <option value="도산로">도산로</option>
      <option value="도안대로">도안대로</option>
      <option value="도안북로">도안북로</option>
      <option value="동서대로">동서대로</option>
      <option value="둔산대로">둔산대로</option>
      <option value="둔산남로">둔산남로</option>
      <option value="둔산서로">둔산서로</option>
      <option value="둔산중로">둔산중로</option>
      <option value="목척">목척</option>
      <option value="문화원로">문화원로</option>
      <option value="보문로">보문로</option>
      <option value="석봉북로">석봉북로</option>
      <option value="신탄진로">신탄진로</option>
      <option value="오류로">오류로</option>
      <option value="온천로">온천로</option>
      <option value="원도안로">원도안로</option>
      <option value="유성대로">유성대로</option>
      <option value="은구비남로">은구비남로</option>
      <option value="지족로">지족로</option>
      <option value="청사로">청사로</option>
      <option value="테크노1로">테크노1로</option>
      <option value="테크노3로">테크노3로</option>
      <option value="테크노4로">테크노4로</option>
      <option value="테크노중앙로">테크노중앙로</option>
    </select>
    <button @click="handleSearch" class="btn btn-custom">검색</button>
  </div>
</template>

<script>
export default {
  name: 'SelectBar',
  props: {
    selectedGu: String,
    selectedDong: String,
    selectedRoad: String
  },
  emits: ['update:selectedGu', 'update:selectedDong', 'update:selectedRoad', 'search'],
  methods: {
    handleSearch() {
      this.$emit('search');
    }
  }
};
</script>

<style scoped>
.search-bar {
  margin-bottom: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}

.custom-select {
  border: 1px solid #ced4da;
  border-radius: 5px;
  padding: 10px;
  margin-right: 10px;
  font-size: 14px;
}

.btn-custom {
  background-color: rgba(87, 184, 70, 0.8);
  border-color: rgba(87, 184, 70, 0.8);
  color: white;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 14px;
}

.btn-custom:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}
</style>
