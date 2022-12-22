<template>
  <HeaderContent :title="`Calculate Price`"/>
  <main class="main-content flex-gap-col">
    <ProgressBar :steps="this.stepsToCalculatePrice"/>
    <div class="step-views">
      <ConnectTransporterView v-if="this.stepsToCalculatePrice[0].inProgress"
                              :info="this.stepsToCalculatePrice[0].info"
                              @link="(transporterId) => { this.calculatePrice(transporterId); }"/>
      <ShowPriceView v-if="this.stepsToCalculatePrice[1].inProgress"/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import ProgressBar from "@/components/Progress/ProgressBar.vue";

import ConnectTransporterView from "@/views/Transporter/ConnectTransporterView.vue";
import ShowPriceView from "@/views/Transporter/CalculatePrice/ShowPriceView.vue";

import {mapActions, mapGetters} from "vuex";


export default {
  name: "CalculatePriceView",
  components: {ShowPriceView, ConnectTransporterView, ProgressBar, HeaderContent},
  computed: {
    ...mapGetters(['stepsToCalculatePrice'])
  },
  methods: {
    ...mapActions(['continueToCalculatedPriceStep', 'calculatePrice'])
  },
  created() {
    this.continueToCalculatedPriceStep(1);
  }
};
</script>

<style scoped lang="scss">

.step-views {
  height: 100%; /* REQUIRED FOR BUTTONS TO BE AT THE BOTTOM */
}

</style>
