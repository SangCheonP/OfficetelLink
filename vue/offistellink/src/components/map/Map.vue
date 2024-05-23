<template>
  <div class="board-wrapper">
    <div class="container-fluid mt-3">
      <!-- 맵 필터링 기능 구역 -->
      <div class="row mb-3 search-bar">
        <div class="col-12 select-bar" style="margin: 10px">
          <MapSelectBar
            :selectedGu="selectedGu"
            :selectedDong="selectedDong"
            :selectedRoad="selectedRoad"
            @update:selectedGu="selectedGu = $event"
            @update:selectedDong="selectedDong = $event"
            @update:selectedRoad="selectedRoad = $event"
            @search="handleSearch"
            class="custom-map-select-bar"
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
          <div class="card map-card">
            <div class="card-body map-card-body">
              <!-- <h5 class="card-title text-brand">Map</h5> -->
              <div ref="mapContainer" class="map-container"></div>
              <div v-for="marker in markersDataArray.value" :key="marker.roadName">
                <p>{{ marker.gu }} {{ marker.dong }} {{ marker.roadName }}</p>
              </div>
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
import { POSITION, useToast } from 'vue-toastification';

export default {
  name: 'KakaoMap',
  components: {
    MapSelectBar,
    MapChart,
  },
  setup() {
    const mapContainer = ref(null);
    const markersDataArray = ref([]); // 초기화를 빈 배열로 설정
    const selectedGu = ref('');
    const selectedDong = ref('');
    const selectedRoad = ref('');
    let map = null;
    let clusterer = null;
    const alert = useToast();

    // 클러스터 스타일 
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

    // 클러스터러에 마우스 오버 이벤트 리스너 추가
    const addClusterEventListeners = (clusterer) => {
      window.kakao.maps.event.addListener(clusterer, 'clusterover', function(cluster) {
        const markers = cluster.getMarkers();
        let totalDeal = 0;
        markers.forEach(marker => {
          totalDeal += marker.deal;
        });
        const averageDeal = (totalDeal / markers.length).toFixed(0);
        const content = 
          `<div style="padding:10px; background:rgba(255,255,255,0.9); color:#333; border-radius:10px; border: 1px solid #ccc; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); font-size:14px; font-weight:bold;">
            평균 거래가: ${averageDeal}만원
          </div>`;
        const position = cluster.getCenter();
        const customOverlay = new window.kakao.maps.CustomOverlay({
          position: position,
          content: content,
          xAnchor: 0.5,
          yAnchor: 1.5
        });

        if (window.currentOverlay) {
          window.currentOverlay.setMap(null);
        }
        customOverlay.setMap(map);
        window.currentOverlay = customOverlay;
      });

      window.kakao.maps.event.addListener(clusterer, 'clusterout', function() {
        if (window.currentOverlay) {
          window.currentOverlay.setMap(null);
          window.currentOverlay = null;
        }
      });
    };

    // 카카오 맵 초기화 & db 데이터 연동
    const initMap = () => {
      if (mapContainer.value) {
        const centerPosition = new window.kakao.maps.LatLng(36.3556018, 127.3445886);
        map = new window.kakao.maps.Map(mapContainer.value, {
          center: centerPosition,
          level: 3
        });

        clusterer = new window.kakao.maps.MarkerClusterer({
          map: map,
          averageCenter: true,
          minLevel: 3,
          disableClickZoom: false,
          calculator: [10, 30, 100],
          styles: clusterStyles
        });

        addClusterEventListeners(clusterer);

        const imageSize = new window.kakao.maps.Size(64, 69);
        const imageOption = { offset: new window.kakao.maps.Point(27, 69) };
        const markerImage = new window.kakao.maps.MarkerImage(markerImageSrc, imageSize, imageOption);

        axios.get("http://localhost:8080/transactions")
          .then(response => {
            markersDataArray.value = response.data;
            updateMarkers(markersDataArray.value);
          });
      }
    };

    // 검색 조건에 따른 마커 업데이트 & 지도 이동
    const updateMarkers = (data) => {
      if (clusterer) {
        clusterer.clear();
      }
  
      const imageSize = new window.kakao.maps.Size(64, 69);
      const imageOption = { offset: new window.kakao.maps.Point(27, 69) };
      const markerImage = new window.kakao.maps.MarkerImage(markerImageSrc, imageSize, imageOption);

      const bounds = new window.kakao.maps.LatLngBounds();

      const markers = data.map(markerData => {
        const markerPosition = new window.kakao.maps.LatLng(markerData.latitude, markerData.longitude);
        bounds.extend(markerPosition);
        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
          image: markerImage
        });
        marker.dangiName = markerData.dangiName;
        marker.bungi = markerData.bungi;
        marker.deal = markerData.deal;

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

        window.kakao.maps.event.addListener(marker, 'mouseout', function() {
          if (window.currentOverlay) {
            window.currentOverlay.setMap(null);
            window.currentOverlay = null;
          }
        });

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
      map.setBounds(bounds);

      // 초기 줌 레벨을 지정
      if (data.length === 562) {
        const firstMarkerPosition = new window.kakao.maps.LatLng(data[192].latitude, data[192].longitude);
        map.setCenter(firstMarkerPosition);
        map.setLevel(3); // 적절한 줌 레벨로 설정
      }
    };

    const loadKakaoMapsScript = () => {
      const script = document.createElement('script');
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=04a4129040b60b0fd253d0e40a236e9a&libraries=clusterer";
      script.onload = () => window.kakao.maps.load(initMap);
      document.head.appendChild(script);
    };

    // 필터링 조건 
    const handleSearch = (gu, dong, road) => {
      if (!gu || !dong || !road) {
        alert.error('모든 검색 조건을 입력하세요.',
        { position: POSITION.TOP_CENTER }); // 경고 메시지 표시
        return;
      }

      const searchDto = {
        gu,
        dong,
        roadName: road
      };

      axios.post('http://localhost:8080/transactions/search', searchDto)
        .then(response => {
          markersDataArray.value = response.data;
          if (markersDataArray.value.length === 0) {
            alert.warning('조건에 맞는 지역이 존재하지 않습니다!',
            { position: POSITION.TOP_CENTER});
            return;
          }
          updateMarkers(markersDataArray.value);
        });
    };

    return {
      mapContainer,
      markersDataArray,
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
  margin-top: 100px;
  background: #9053c7;
  background: -webkit-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -o-linear-gradient(-135deg, #c850c0, #4158d0);
  background: -moz-linear-gradient(-135deg, #c850c0, #4158d0);
  background: linear-gradient(-135deg, #c850c0, #4158d0);
}
.board-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin-left: 150px;
    margin-right: 150px;   
}
.container-fluid {
  border: 2px solid darkslategray; /* 검은색 테두리 */
  border-radius: 15px;
  padding: 20px; /* 패딩 설정 */
  background: rgb(247, 246, 246);
}
.card {
  border-radius: 15px;
  margin: 0; /* 카드 요소의 마진 제거 */
  background: rgb(247, 246, 246);
}
.card-body {
  padding: 0; /* 카드 바디의 패딩 제거 */

  background: rgb(247, 246, 246);
}

.map-container {
  width: 100%;
  height: 400px;
  border-radius: 15px; /* 둥근 테두리 */
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
