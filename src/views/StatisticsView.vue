<template>
  <HeaderContent :title="`Statistics`"/>
  <main class="main-content flex-gap-col">
    <div class="flex-space-between-row flex-gap-row">
      <TextTile class="tile-item" :title="`My Daily Limit`" :icon="`security`" :text="`${this.user.limit.reached} / ${this.user.limit.max}`"/>
      <TextTile class="tile-item" :title="`Total number of sent items`" :icon="`unarchive`" :text="`${this.user.totalSent}`"/>
      <TextTile class="tile-item" :title="`Total number of received items`" :icon="`archive`" :text="`${this.user.totalReceived}`"/>
      <TextTile class="tile-item" :title="`Total number of sent/received items`" :icon="`all_inbox`" :text="`${this.user.totalSent +  this.user.totalReceived}`"/>
    </div>
    <div class="flex-gap-row charts">
      <div class="box chart-1" v-if="this.userItems !== undefined">
        <ItemsPerDayLineChart :items="this.userItems"/>
      </div>
      <div class="box chart-2" v-if="this.userItems !== undefined">
        <DestinationsPieChart :items="this.userItems"/>
      </div>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import {mapActions, mapGetters} from "vuex";
import TextTile from "@/components/Tile/TextTile.vue";
import ItemsPerDayLineChart from "@/components/Graph/ItemsPerDayLineChart.vue";
import DestinationsPieChart from "@/components/Graph/DestinationsPieChart.vue";
import {Chart as ChartJS} from "chart.js";

export default {
  name: "StatisticsView",
  components: {DestinationsPieChart, ItemsPerDayLineChart, TextTile, HeaderContent},
  computed: {
    ...mapGetters(['user', 'userItems'])
  },
  methods: {
    ...mapActions(['fetchUserItems']),
    resizeCharts() {
      for (const id in ChartJS.instances) {
        if(ChartJS.instances.hasOwnProperty(id)) {
          ChartJS.instances[id].resize();
        }
      }
      this.timeout = setTimeout(this.resizeCharts, 500);
    }
  },
  mounted() {
    this.fetchUserItems();
    this.resizeCharts();
  },
  unmounted() {
    clearTimeout(this.timeout);
  }
};
</script>

<style scoped lang="scss">

.tile-item {
  flex: 1 1 100%;
}

.chart-1 {
  flex: 1 1 65%;
}

.chart-2 {
  flex: 1 1 30%;
}

@media (max-width: 1600px) {
  .charts {
    flex-direction: column;
  }
}

</style>
