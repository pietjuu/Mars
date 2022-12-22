<template>
  <HeaderContent :title="`Send Item`"/>
  <main class="main-content">
    <div class="progress-bar-wrapper">
      <ProgressBar :steps="sendSteps"/>
    </div>
    <div class="send-item-views">
      <!-- <ConnectTransporterView/> -->
      <EnterItemDetailsView/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

import ConnectTransporterView from "@/views/CalculatePrice/ConnectTransporterView.vue";
import EnterItemDetailsView from "@/views/SendItem/EnterItemDetailsView";

import {mapGetters} from "vuex";


export default {
  name: "SendItemView",
  computed: {
    ...mapGetters([`sendSteps`])
  },
  components: {
    HeaderContent,
    ProgressBar,
    ConnectTransporterView,
    EnterItemDetailsView
  }
};
</script>

<style scoped lang="scss">

</style>
