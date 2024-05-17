<template>
  <div>
    <div class="container-fluid mt-3">
      <div class="row mb-3">
        <div class="col-12">
          <div class="chart-container">
            <canvas id="priceTrendChart"></canvas>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted } from 'vue';
import Chart from 'chart.js/auto';
import axios from 'axios';

export default {
  name: 'MapChart',
  setup() {
    const initChart = (data) => {
      // Prepare the data for the chart
      const labels = data.map(item => item.contractDate);
      const prices = data.map(item => item.avgDeal);

      // Initialize the chart
      const ctx = document.getElementById('priceTrendChart').getContext('2d');
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [{
            label: '날짜별 시세 추이',
            data: prices,
            borderColor: 'rgba(75, 192, 192, 1)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            fill: true,
            tension: 0.1
          }]
        },
        options: {
          responsive: true, // Make the chart responsive
          maintainAspectRatio: false, // Allow the aspect ratio to be adjusted
          scales: {
            x: {
              title: {
                display: true,
                text: '날짜'
              }
            },
            y: {
              title: {
                display: true,
                text: '시세 (만원)'
              },
              ticks: {
                stepSize: 5000 // Set the y-axis increments to 5000 units
              }
            }
          },
          plugins: {
            legend: {
              display: true,
              position: 'top'
            }
          }
        }
      });
    };

    onMounted(() => {
      axios.get('http://localhost:8080/transactions/price')
        .then(response => {
          initChart(response.data);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    });

    return {};
  }
};
</script>

<style>
.chart-container {
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 50%; /* Adjust this value to control the aspect ratio */
}
</style>