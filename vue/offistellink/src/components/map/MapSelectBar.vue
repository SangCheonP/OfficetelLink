<template>
  <div class="input-group search-bar">
    <select v-model="selectedGu" @change="updateGu" class="custom-select">
      <option value="" disabled selected>구 선택</option>
      <option v-for="gu in guOptions" :key="gu" :value="gu">{{ gu }}</option>
    </select>
    <select v-model="selectedDong" @change="updateDong" class="custom-select">
      <option value="" disabled selected>동 선택</option>
      <option v-for="dong in dongOptions" :key="dong" :value="dong">{{ dong }}</option>
    </select>
    <select v-model="selectedRoad" @change="updateRoad" class="custom-select">
      <option value="" disabled selected>도로명 선택</option>
      <option v-for="road in roadOptions" :key="road" :value="road">{{ road }}</option>
    </select>
    <div class="input-group-append">
      <button class="btn btn-outline-secondary custom-button" type="button" @click="search">검색</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MapSelectBar',
  data() {
    return {
      selectedGu: '',
      selectedDong: '',
      selectedRoad: '',
      guOptions: ['대덕구', '동구', '서구', '유성구', '중구'],
      dongOptions: ['가양동', '관평동', '노은동', '대흥동', '도마동', '도안동', '둔곡동', '둔산동', '만년동', '변동', '봉명동', '오류동', '용계동', '월평동', '은행동', '석봉동', '선화동', '성남동', '신성동', '신탄진동', '장대동', '지족동', '탄방동'],
      roadOptions: ['가양로', '계룡로', '과학지원', '노은로', '노은서로', '대덕대로', '대종로', '대학로', '대흥로', '도마로', '도산로', '도안대로', '도안북로', '동서대로', '둔산대로', '둔산남로', '둔산서로', '둔산중로', '목척', '문화원로', '보문로', '석봉북로', '신탄진로', '오류로', '온천로', '원도안로', '유성대로', '은구비남로', '지족로', '청사로', '테크노1로', '테크노3로', '테크노4로', '테크노중앙로']
    };
  },
  methods: {
    updateGu(event) {
      this.selectedGu = event.target.value;
      this.$emit('update:selectedGu', this.selectedGu);
    },
    updateDong(event) {
      this.selectedDong = event.target.value;
      this.$emit('update:selectedDong', this.selectedDong);
    },
    updateRoad(event) {
      this.selectedRoad = event.target.value;
      this.$emit('update:selectedRoad', this.selectedRoad);
    },
    search() {
      this.$emit('search', this.selectedGu, this.selectedDong, this.selectedRoad);
    }
  }
};
</script>

<style scoped>
.search-bar {
  margin-left:-10px;
  margin-bottom: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: transparent;
  display: flex;
  padding: 15px; /* Increased padding for size */
}

.custom-select {
  border: 1px solid #ced4da;
  border-radius: 5px;
  padding: 5px; /* Increased padding for size */
  margin-right: 20px;
  font-size: 18px; /* Increased font size */
  flex: 1;
  font-family: 'Arial', sans-serif; /* Font style */
  font-weight: bold; /* Font weight */
  color: #333; /* Font color */
}

.input-group-append {
  display: flex;
}

.custom-button {
  background-color: rgba(87, 184, 70, 0.8);
  border-color: rgba(87, 184, 70, 0.8);
  color: white;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 14px;
  flex: 1;
  text-align: center;
  white-space: nowrap;
}
</style>
