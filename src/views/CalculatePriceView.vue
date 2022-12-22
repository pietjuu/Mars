<template>
  <HeaderContent :title="`Calculate Price`"/>
  <main class="main-content flex-gap-col">
    <ProgressBar :steps="stepsToCalculatePrice"/>
    <div class="step-views">
      <ConnectTransporterView v-if="stepsToCalculatePrice[0].inProgress" :info="stepsToCalculatePrice[0].info"/>
      <ShowPriceView v-if="stepsToCalculatePrice[1].inProgress"/>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import ProgressBar from "@/components/Progress/ProgressBar.vue";
import {mapActions, mapGetters} from "vuex";
import ConnectTransporterView from "@/views/CalculatePrice/ConnectTransporterView.vue";
import ShowPriceView from "@/views/CalculatePrice/ShowPriceView.vue";

export default {
  name: "CalculatePriceView",
  components: {ShowPriceView, ConnectTransporterView, ProgressBar, HeaderContent},
  computed: {
    ...mapGetters(['stepsToCalculatePrice'])
  },
  methods: {
    ...mapActions(['continueToCalculatedPriceStep'])
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
