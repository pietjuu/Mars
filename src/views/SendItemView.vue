<template>
  <div class="send-item-wrapper">
    <HeaderContent :title="`Send Item`"/>
    <main>
      <div class="progress-bar-wrapper">
        <ProgressBar :steps="steps"/>
      </div>
      <div class="send-item-views">
        <ConnectTransporterView/>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

import ConnectTransporterView from "@/views/SendItem/ConnectTransporterView";
import {mapGetters} from "vuex";


export default {
  name: "SendItemView",
  computed: {
    ...mapGetters([`steps`])
  },
  components: {
    HeaderContent,
    ProgressBar,
    ConnectTransporterView
  }
}
</script>

<style scoped lang="scss">

</style>