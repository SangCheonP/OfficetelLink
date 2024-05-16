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
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';
import axios from 'axios';

export default {
  name: 'MapChart',
  setup() {
    const initChart = (data) => {
      // Aggregate the data by month
      const monthlyData = {};
      data.forEach(item => {
        const yearMonth = `${item.contrach_date.toString().slice(0, 4)}-${item.contrach_date.toString().slice(4, 6)}`;
        if (!monthlyData[yearMonth]) {
          monthlyData[yearMonth] = [];
        }
        monthlyData[yearMonth].push(item.deal);
      });

      // Calculate average deal per month and round to the nearest integer
      const labels = Object.keys(monthlyData).sort();
      const prices = labels.map(label => {
        const deals = monthlyData[label];
        const total = deals.reduce((acc, curr) => acc + curr, 0);
        const average = total / deals.length;
        return Math.round(average); // Round to nearest integer
      });

      // Initialize the chart
      const ctx = document.getElementById('priceTrendChart').getContext('2d');
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [{
            label: '월별 시세 추이',
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
      axios.get('/officetel_transaction_data.json')
        .then(response => {
          initChart(response.data);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    });

    return { ref };
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
