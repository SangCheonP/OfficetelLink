<template>
  <div class="map-container">
    <div ref="mapContainer" style="width: 500px; height: 500px;"></div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import markerImageSrc from '@/assets/images/house-icon.png';

export default {
  name: 'KakaoMap',
  setup() {
    const mapContainer = ref(null);
    const markersDataArray = ref([]);
    const markersArray = ref([]);

    // 클러스터러 스타일 설정
    const clusterStyles = [
    {
        width: '53px',
        height: '52px',
        background: 'rgba(51, 204, 255, .8)',
        borderRadius: '25px',
        color: '#000',
        textAlign: 'center',
        lineHeight: '54px'
      },
      {
        width: '56px',
        height: '55px',
        background: 'rgba(255, 153, 0, .8)',
        borderRadius: '25px',
        color: '#000',
        textAlign: 'center',
        lineHeight: '55px'
      },
      {
        width: '66px',
        height: '65px',
        background: 'rgba(255, 51, 204, .8)',
        borderRadius: '25px',
        color: '#000',
        textAlign: 'center',
        lineHeight: '65px'
      }
    ]

    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        console.log('Kakao Maps API 로드 완료');
        initMap();
      } else {
        console.error('Kakao Maps API 로드 실패');
        loadKakaoMapsScript();
      }
    });

    // 카카오 맵 초기화 
    const initMap = () => {
      if (mapContainer.value) {
        const markers = {
          center: new window.kakao.maps.LatLng(36.3556018, 127.3445886),
          level: 3
        };

        const map = new window.kakao.maps.Map(mapContainer.value, markers);

        // 클러스터 선언
        const clusterer = new window.kakao.maps.MarkerClusterer({
          map: map,
          averageCenter: true,
          minLevel: 1,
          disableClickZoom: false,
          calculator: [10, 30, 100],
          styles: clusterStyles
        });

        // 마커 이미지 추가
        const imageSize = new window.kakao.maps.Size(64,69);
        const imageOption = {offset:new window.kakao.maps.Point(27,69)};
        const markerImage = new window.kakao.maps.MarkerImage(markerImageSrc, imageSize, imageOption);

        // JSON 파일에서 마커 데이터를 불러와서 지도에 마커를 추가
        axios.get("/officetel_transaction_data.json")
          .then(response => {
            markersDataArray.value = response.data;
            const markers = markersDataArray.value.map(markerData => {
              const markerPosition = new window.kakao.maps.LatLng(markerData.latitude, markerData.longitude);
              const marker = new window.kakao.maps.Marker({
                position: markerPosition,
                image: markerImage
              });
              marker.deal = markerData.deal; //거래가 컬럼 추가
              return marker;
            });
            clusterer.addMarkers(markers);
            markersArray.value = markers;
          });

        // 클러스터러에 마우스오버 이벤트 추가
        window.kakao.maps.event.addListener(clusterer, 'clusterover', function(cluster) {
          const markers = cluster.getMarkers();
          const totalDeal = markers.reduce((sum, marker) => sum + marker.deal, 0);
          const averageDeal = Math.round(totalDeal / markers.length);
          console.log();
          // 커스텀 오버레이 생성
          const content = `<div style="padding:5px;background:rgba(0,0,0,0.5);color:#fff;border-radius:5px;">평균 거래가: ${averageDeal.toLocaleString()}만원</div>`;
          const position = cluster.getCenter();
          const customOverlay = new window.kakao.maps.CustomOverlay({
            position: position,
            content: content,
            xAnchor: 0.5,
            yAnchor: 0.5
          });

          // 기존의 오버레이 제거 후 새로운 오버레이 추가
          if (window.currentOverlay) {
            window.currentOverlay.setMap(null);
          }
          customOverlay.setMap(map);
          window.currentOverlay = customOverlay;
        });  
      }
    };

    // 카카오 맵 로드 
    const loadKakaoMapsScript = () => {
      const script = document.createElement('script');
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=04a4129040b60b0fd253d0e40a236e9a&libraries=clusterer";
      script.onload = () => window.kakao.maps.load(initMap);
      document.head.appendChild(script);
    };

    return {
      mapContainer
    };
  }
};
</script>

<style scoped>
.map-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
