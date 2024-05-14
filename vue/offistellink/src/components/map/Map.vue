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
      map: null,
      markers: []
    };
  },
  methods: {
    loadKakaoMapScript() {
      return new Promise((resolve, reject) => {
        if (typeof window.kakao === 'undefined') {
          const script = document.createElement('script');
          script.onload = () => resolve();
          script.onerror = () => reject(new Error('Kakao map script load error'));
          script.src = "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=04a4129040b60b0fd253d0e40a236e9a&libraries=services,clusterer";
          document.head.appendChild(script);
        } else {
          resolve();
        }
      });
    },
    initMap() {
      window.kakao.maps.load(() => {
        const container = this.$refs.map;
        const options = {
          center: new window.kakao.maps.LatLng(36.3556018, 127.3445886),
          level: 3
        };
        this.map = new window.kakao.maps.Map(container, options);

        // 마우스 클릭 이벤트 리스너 추가
        window.kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {
          const latlng = mouseEvent.latLng;
          this.addMarker(latlng.getLat(), latlng.getLng());
        });

        this.loadPropertyData();
      });
    },
    async loadPropertyData() {
      try {
        const response = await axios.get('/officetel_transaction_data.json');
        let data = response.data;

        data.forEach(item => {
          this.addMarker(item.latitude, item.longitude);
        });
      } catch (error) {
        console.error('Error loading JSON data:', error);
      }
    },
    addMarker(latitude, longitude) {
      const position = new window.kakao.maps.LatLng(latitude, longitude);
      const marker = new window.kakao.maps.Marker({
        position
      });
      marker.setMap(this.map);
      this.markers.push(marker);  // 마커 배열에 마커 추가
    }
  },
  mounted() {
    this.loadKakaoMapScript()
      .then(() => {
        this.initMap();
      })
      .catch(error => {
        console.error('Error loading the Kakao map script:', error);
      });
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