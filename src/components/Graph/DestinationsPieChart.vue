<template>
  <Pie :id="chartId" :options="chartOptions" :data="chartData"/>
</template>

<script>
import { Pie } from 'vue-chartjs';
import { Chart as ChartJS, LineController, LineElement, PointElement, LinearScale, Title, TimeScale, Legend, ArcElement } from 'chart.js';
import 'chartjs-adapter-moment';
import { ItemsProcessor } from "@/assets/js/data-processor/items-processor";

ChartJS.register(LineController, LineElement, PointElement, LinearScale, Title, TimeScale, Legend, ArcElement);

export default {
  name: "DestinationsPieChart",
  components: {
    Pie
  },
  props: {
    items: Array
  },
  data() {
    return {
      chartId: "destinations-pie-chart",
      chartOptions: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: "My Destinations",
            position: "top",
            align: "start",
            color: "#76787a",
            font: {
              size: 16,
              weight: "normal"
            }
          }
        },
        cutout: 150
      },
      chartData: {
        labels: Object.keys(this.getMostUsedDestinations()),
        datasets: [
          {
            data: Object.values(this.getMostUsedDestinations()),
            label: "Sent Items", // label of dataset aka line
            backgroundColor: [
              'rgb(45, 135, 187)',
              'rgb(100, 194, 166)',
              'rgb(170, 222, 167)',
              'rgb(230, 246, 157)',
              'rgb(254, 174, 101)'
            ],
            fill: false,
            tension: 0.1,
          }
        ]
      }
    };
  },
  methods: {
    getMostUsedDestinations() {
      console.log(new ItemsProcessor(this.items).getMostUsedDestinations());
      return new ItemsProcessor(this.items).getMostUsedDestinations();
    }
  }
};
</script>

<style scoped lang="scss">

</style>
