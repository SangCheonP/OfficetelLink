<template>
  <div class="map-container">
    <div ref="map" style="width: 500px; height: 500px;"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'MapVue',
  data() {
    return {
      map: null
    };
  },
  methods: {
    loadKakaoMapScript() {
      const script = document.createElement('script');
      script.onload = () => this.initMap();
      script.src = "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=04a4129040b60b0fd253d0e40a236e9a";
      document.head.appendChild(script);
    },
    initMap() {
      window.kakao.maps.load(() => {
        const container = this.$refs.map;
        const options = {
          center: new window.kakao.maps.LatLng(36.350411, 127.384548),  // 대전 부근 좌표로 설정
          level: 3  // 지도의 확대 레벨
        };
        this.map = new window.kakao.maps.Map(container, options);
        this.loadPropertyData();  // JSON 데이터 로드 및 마커 추가
      });
    },
    addMarker(latitude, longitude) {
      const position = new window.kakao.maps.LatLng(latitude, longitude);
      const marker = new window.kakao.maps.Marker({
        position
      });
      marker.setMap(this.map);
    },
    convertAddressToCoordinates(address) {
      axios.get('https://dapi.kakao.com/v2/local/search/address.json', {
        headers: { 'Authorization': 'KakaoAK YOUR_REST_API_KEY' },
        params: { query: address }
      })
      .then(response => {
        const location = response.data.documents[0];
        this.addMarker(location.y, location.x);
      })
      .catch(error => console.error('Geocoding error:', error));
    },
    loadPropertyData() {
      axios.get('/officetel_transaction_data.json')  // JSON 파일 경로
        .then(response => {
          response.data.forEach(property => {
            if (property.latitude && property.longitude) {
              this.addMarker(property.latitude, property.longitude);
            } else if (property.sigungu) {
              this.convertAddressToCoordinates(property.sigungu);
            }
          });
        })
        .catch(error => console.error('Error loading the property data:', error));
    }
  },
  mounted() {
    this.loadKakaoMapScript();
  }
}
</script>

<style scoped>
.map-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>


<style scoped>
.map-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
