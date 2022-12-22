<template>
  <HeaderContent :title="`Send Item`"/>
  <main class="main-content flex-gap-col">
    <ProgressBar :steps="this.stepsToSendItem"/>
    <div class="step-views">
      <ConnectTransporterView v-if="this.stepsToSendItem[0].inProgress" :info="this.stepsToSendItem[0].info" @link="(transporterId) => { this.initSend(transporterId); }"/>
      <EnterItemDetailsView v-if="this.stepsToSendItem[1].inProgress"/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

import ConnectTransporterView from "@/views/Transporter/ConnectTransporterView.vue";
import EnterItemDetailsView from "@/views/Transporter/SendItem/EnterItemDetailsView";

import {mapActions, mapGetters} from "vuex";


export default {
  name: "SendItemView",
  components: {
    HeaderContent,
    ProgressBar,
    ConnectTransporterView,
    EnterItemDetailsView
  },
  computed: {
    ...mapGetters([`stepsToSendItem`])
  },
  methods: {
    ...mapActions(['continueToSendItemStep', 'initSend'])
  },
  created() {
    this.continueToSendItemStep(1);
  }
};
</script>

<style scoped lang="scss">

.step-views {
  height: 100%; /* REQUIRED FOR BUTTONS TO BE AT THE BOTTOM */
}

</style>
