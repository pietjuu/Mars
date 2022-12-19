<template>
  <HeaderContent :title="`Statistics`"/>
  <main class="main-content flex-gap-col">
    <div class="flex-space-between-row flex-gap-row">
      <TextTile class="tile-item" :title="`My Daily Limit`" :icon="`security`" :text="`${this.user.limit.reached} / ${this.user.limit.max}`"/>
      <TextTile class="tile-item" :title="`Total number of sent items`" :icon="`unarchive`" :text="`${this.user.totalSent}`"/>
      <TextTile class="tile-item" :title="`Total number of received items`" :icon="`archive`" :text="`${this.user.totalReceived}`"/>
      <TextTile class="tile-item" :title="`Total number of sent/received items`" :icon="`all_inbox`" :text="`${this.user.totalSent +  this.user.totalReceived}`"/>
    </div>
    <div class="box">
      <ItemsPerDayLineChart :items="items"/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import {mapGetters} from "vuex";
import TextTile from "@/components/Tile/TextTile.vue";
import ItemsPerDayLineChart from "@/components/Graph/ItemsPerDayLineChart.vue";

export default {
  name: "StatisticsView",
  components: {ItemsPerDayLineChart, TextTile, HeaderContent},
  computed: {
    ...mapGetters(['user'])
  },
  data() {
    return {
      items: [
        {
          "id": "1211",
          "name": "Alarm Clock",
          "action": "sent",
          "timeSent": "2053-12-24 09:47",
          "timeReceived": "2053-12-24 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        },
        {
          "id": "1212",
          "name": "Alarm Clock",
          "action": "sent",
          "timeSent": "2053-12-24 09:47",
          "timeReceived": "2053-12-24 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        },
        {
          "id": "1212",
          "name": "Alarm Clock",
          "action": "sent",
          "timeSent": "2053-12-25 09:47",
          "timeReceived": "2053-12-24 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        },
        {
          "id": "1211",
          "name": "Alarm Clock",
          "action": "received",
          "timeSent": "2053-12-24 09:47",
          "timeReceived": "2053-12-24 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        },
        {
          "id": "1212",
          "name": "Alarm Clock",
          "action": "received",
          "timeSent": "2053-12-25 09:47",
          "timeReceived": "2053-12-25 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        },
        {
          "id": "1212",
          "name": "Alarm Clock",
          "action": "received",
          "timeSent": "2053-12-27 09:47",
          "timeReceived": "2053-12-29 09:48",
          "receiver": "133",
          "origin": "232",
          "sender": "34",
          "destination": "332"
        }
      ]
    };
  }
};
</script>

<style scoped lang="scss">

.tile-item {
  flex: 1 1 100%;
}

</style>
