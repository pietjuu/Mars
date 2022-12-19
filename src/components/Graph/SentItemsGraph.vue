<template>
  <Line id="sent-items-chart" :options="chartOptions" :data="chartData"/>
</template>

<script>
import { Line } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, CategoryScale, LinearScale, PointElement, LineElement } from 'chart.js';
import { ItemsProcessor } from "@/assets/js/data-processor/items-processor";

ChartJS.register(Title, Tooltip, Legend, PointElement, LineElement, CategoryScale, LinearScale);

export default {
  name: "SentItemsGraph",
  components: {
    Line
  },
  props: {
    items: Array
  },
  data() {
    return {
      chartOptions: {
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
            labels: {
              boxHeight: 1
            }
          }
        },
        scales: {
          y: {
            title: {
              display: true,
              align: "center",
              text: "Amount of Items",
            },
            ticks: {
              stepSize: 1
            },
            min: 0
          },
          x: {
            title: {
              display: true,
              align: "center",
              text: "Date"
            }
          }
        }
      },
      chartData: {
        labels: this.getAllDays(),
        datasets: [
          {
            data: this.getSentItemsPerDay(),
            label: "Sent Items", // label of dataset aka line
            backgroundColor: "#9C27B0"
          },
          {
            data: this.getReceivedItemsPerDay(),
            label: "Received Items", // label of dataset aka line
            backgroundColor: "#FFA500"
          }
        ]
      }
    };
  },
  methods: {
    getAllDays() {
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
