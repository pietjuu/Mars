<template>
  <div class="send-item-wrapper">
    <HeaderContent :title="`Send Item`"/>
    <main>
      <div class="progress-bar-wrapper">
        <ProgressBar :steps="sendSteps"/>
      </div>
      <div class="send-item-views">
        <!-- <ConnectTransporterView/> -->
        <EnterItemDetailsView/>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

import ConnectTransporterView from "@/views/SendItem/ConnectTransporterView";
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
