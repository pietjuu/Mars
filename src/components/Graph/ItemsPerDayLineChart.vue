<template>
  <Line :id="chartId" :options="chartOptions" :data="chartData"/>
</template>

<script>
import { Line } from 'vue-chartjs';
import { Chart as ChartJS, LineController, LineElement, PointElement, LinearScale, Title, TimeScale, Legend } from 'chart.js';
import 'chartjs-adapter-moment';
import { ItemsProcessor } from "@/assets/js/data-processor/items-processor";

ChartJS.register(LineController, LineElement, PointElement, LinearScale, Title, TimeScale, Legend);

export default {
  name: "ItemsPerDayLineChart",
  components: {
    Line
  },
  props: {
    items: Array
  },
  data() {
    return {
      chartId: "items-per-day-line-chart",
      chartOptions: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: "Sent/Received items Per Day",
            position: "top",
            align: "start",
            color: "#76787a",
            font: {
              size: 16,
              weight: "normal"
            }
          },
          legend: {
            display: true,
            position: "top",
            align: "start",
            labels: {
              boxHeight: 1
            }
          }
        },
        scales: {
          x: {
            type: 'time',
            display: true,
            bounds: 'ticks',
            time: {
              unit: 'day'
            }
          },
          y: {
            ticks: {
              stepSize: 1
            },
            min: 0
          }
        }
      },
      chartData: {
        labels: this.getLabels(),
        datasets: [
          {
            data: Object.values(this.getSentItemsPerDay()),
            label: "Sent Items", // label of dataset aka line
            backgroundColor: '#335eea',
            borderColor: '#335eea',
            fill: false,
            tension: 0.1,
          },
          {
            data: Object.values(this.getReceivedItemsPerDay()),
            label: "Received Items", // label of dataset aka line
            backgroundColor: "#170040",
            borderColor: "#170040",
            fill: false,
            tension: 0.1
          }
        ]
      }
    };
  },
  methods: {
    getLabels() {
      return new ItemsProcessor(this.items).getAllDays();
    },
    getSentItemsPerDay() {
      return new ItemsProcessor(this.items).getSentItemsPerDay();
    },
    getReceivedItemsPerDay() {
      return new ItemsProcessor(this.items).getReceivedItemsPerDay();
    }
  }
};
</script>

<style scoped lang="scss">

</style>
