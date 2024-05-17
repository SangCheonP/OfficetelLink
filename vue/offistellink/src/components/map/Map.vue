<template>
  <div>
    <header class="navbar navbar-expand navbar-dark bg-dark">
      <br><br><br><br>
    </header>

    <div class="container-fluid mt-3">
      <!-- 맵 필터링 기능 구역 -->
      <div class="row mb-3">
        <div class="col-12">
          <MapSelectBar
            :selectedGu="selectedGu"
            :selectedDong="selectedDong"
            :selectedRoad="selectedRoad"
            @update:selectedGu="selectedGu = $event"
            @update:selectedDong="selectedDong = $event"
            @update:selectedRoad="selectedRoad = $event"
            @search="handleSearch"
          />
        </div>
      </div>

      <div class="row">
        <!-- 월별 시세 평균 추이 차트 구역 -->
        <div class="col-md-5 mb-3">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title text-brand">월별 시세 추이</h5>
                <MapChart></MapChart>
              <!-- 차트 내용 -->
            </div>
          </div>
        </div>
        <!-- 지도 api 및 클러스터링 -->
        <div class="col-md-7 mb-3">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title text-brand">Map</h5>
              <div ref="mapContainer" style="width: 100%; height: 400px;"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import markerImageSrc from '@/assets/images/house-icon.png';
import MapSelectBar from '@/components/map/MapSelectBar.vue';
import MapChart from '@/components/map/MapChart.vue';

export default {
  name: 'KakaoMap',
  components: {
    MapSelectBar,
    MapChart
  },
  setup() {
    const mapContainer = ref(null);
    const markersDataArray = ref([]);
    const markersArray = ref([]);
    const selectedGu = ref('');
    const selectedDong = ref('');
    const selectedRoad = ref('');

    const hiddenInput = ref(null);
    const focusInput = () => {
      hiddenInput.value.focus();
    };

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
    ];

    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
        console.log('Kakao Maps API 로드 완료');
        initMap();
      } else {
        console.error('Kakao Maps API 로드 실패');
        loadKakaoMapsScript();
      }
    });

    // 맵 초기화
    const initMap = () => {
      if (mapContainer.value) {
        const markers = {
          center: new window.kakao.maps.LatLng(36.3556018, 127.3445886),
          level: 3
        };

        const map = new window.kakao.maps.Map(mapContainer.value, markers);

        // 클러스터 정의 
        const clusterer = new window.kakao.maps.MarkerClusterer({
          map: map,
          averageCenter: true,
          minLevel: 3,
          disableClickZoom: false,
          calculator: [10, 30, 100],
          styles: clusterStyles
        });

        // 마커 이미지 등록 
        const imageSize = new window.kakao.maps.Size(64, 69);
        const imageOption = { offset: new window.kakao.maps.Point(27, 69) };
        const markerImage = new window.kakao.maps.MarkerImage(markerImageSrc, imageSize, imageOption);

        // 데이터 불러오기 
        axios.get("http://localhost:8080/transactions")
          .then(response => {
            markersDataArray.value = response.data;
            const markers = markersDataArray.value.map(markerData => {     
              const markerPosition = new window.kakao.maps.LatLng(markerData.latitude, markerData.longitude);
              const marker = new window.kakao.maps.Marker({
                position: markerPosition,
                image: markerImage
              });
              marker.dangiName = markerData.dangiName;
              marker.bungi = markerData.bungi;
              marker.deal = markerData.deal;
              // 클러스터 마커 표시 
              window.kakao.maps.event.addListener(marker, 'mouseover', function() {
                const content = 
                  `<div style="padding:10px; background:rgba(255,255,255,0.9); color:#333; border-radius:10px; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); font-size:14px; font-weight:bold;">
                    평균 거래가: ${marker.deal.toLocaleString()}만원
                  </div>`;
                const position = marker.getPosition();
                const customOverlay = new window.kakao.maps.CustomOverlay({
                  position: position,
                  content: content,
                  xAnchor: 0.5,
                  yAnchor: 2.2
                });

                if (window.currentOverlay) {
                  window.currentOverlay.setMap(null);
                }
                customOverlay.setMap(map);
                window.currentOverlay = customOverlay;
              });

              // 마우스가 마커에서 벗어날 시 거래가 숨기기
              window.kakao.maps.event.addListener(marker, 'mouseout', function() {
                if (window.currentOverlay) {
                  window.currentOverlay.setMap(null);
                  window.currentOverlay = null;
                }
              });

              // 마커 클릭 시 단지 이름, 번지, 거래가 표시 및 토글 기능 추가
              window.kakao.maps.event.addListener(marker, 'click', function() {
                if (marker.customOverlay && marker.customOverlay.getMap()) {
                  marker.customOverlay.setMap(null);
                  marker.customOverlay = null;
                } else {
                  const content = 
                    `<div style="padding:10px; background:rgba(255,255,255,0.9); color:#333; border-radius:10px; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); font-size:14px; font-weight:bold;">
                      단지 이름: ${marker.dangiName}<br>
                      번지: ${marker.bungi}<br>
                      거래가: ${marker.deal.toLocaleString()}만원
                    </div>`;
                  const position = marker.getPosition();
                  marker.customOverlay = new window.kakao.maps.CustomOverlay({
                    position: position,
                    content: content,
                    xAnchor: 0.5,
                    yAnchor: 1.7
                  });
                  marker.customOverlay.setMap(map);
                }
              });

              return marker;
            });
            clusterer.addMarkers(markers);
            markersArray.value = markers;
          });

        // 평균 거래가 계산 
        window.kakao.maps.event.addListener(clusterer, 'clusterover', function(cluster) {
          const markers = cluster.getMarkers();
          const totalDeal = markers.reduce((sum, marker) => sum + marker.deal, 0);
          const averageDeal = Math.round(totalDeal / markers.length);
          
          const content = 
            `<div style="padding:10px; background:rgba(255,255,255,0.9); color:#333; border-radius:10px; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); font-size:14px; font-weight:bold;">
                평균 거래가: ${averageDeal.toLocaleString()}만원
            </div>`;
          const position = cluster.getCenter();
          const customOverlay = new window.kakao.maps.CustomOverlay({
            position: position,
            content: content,
            xAnchor: 0.5,
            yAnchor: 1.2
          });

          if (window.currentOverlay) {
            window.currentOverlay.setMap(null);
          }
          customOverlay.setMap(map);
          window.currentOverlay = customOverlay;
        });

        // 클러스터 마우스 아웃 시 평균 거래가 숨기기
        window.kakao.maps.event.addListener(clusterer, 'clusterout', function() {
          if (window.currentOverlay) {
            window.currentOverlay.setMap(null);
            window.currentOverlay = null;
          }
        });  
      }
    };

    const loadKakaoMapsScript = () => {
      const script = document.createElement('script');
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=04a4129040b60b0fd253d0e40a236e9a&libraries=clusterer";
      script.onload = () => window.kakao.maps.load(initMap);
      document.head.appendChild(script);
    };

    // 검색 처리 로직
    const handleSearch = () => {
      console.log(`Selected Gu: ${selectedGu.value}`);
      console.log(`Selected Dong: ${selectedDong.value}`);
      console.log(`Selected Road: ${selectedRoad.value}`);
    };

    return {
      mapContainer,
      hiddenInput,
      focusInput,
      selectedGu,
      selectedDong,
      selectedRoad,
      handleSearch
    };
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  background-color: #f2f2f2;
  text-align: left;
}
</style>
