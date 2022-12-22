<template>
  <HeaderContent :title="`Send Item`"/>
  <main class="main-content flex-gap-col">
    <ProgressBar :steps="stepsToSendItem"/>
    <div class="step-views">
      <ConnectTransporterView v-if="stepsToSendItem[0].inProgress" :info="stepsToSendItem[0].info"/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

import ConnectTransporterView from "@/views/SendItem/ConnectTransporterView.vue";
import EnterItemDetailsView from "@/views/SendItem/EnterItemDetailsView";

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
    ...mapActions(['continueToSendItemStep'])
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
