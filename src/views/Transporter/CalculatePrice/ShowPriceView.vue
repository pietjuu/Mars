<template>
  <Load v-if="loading"/>
  <div class="show-calculated-price-wrapper flex-gap-col" v-if="!loading">
    <TextTile :title="`Cost to Send Item`" :icon="`payments`" :text="`MC ${this.calculatedPrice}`"/>
    <div class="bottom-buttons">
      <TextIconButton :content="`Go back`" :icon="`refresh`" :width="`8.5rem`" :height="`2.3rem`" @click="onGoBack"/>
    </div>
  </div>
</template>

<script>
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions, mapGetters} from "vuex";
import TextTile from "@/components/Tile/TextTile.vue";
import Load from "@/components/Load/Load.vue";

export default {
  name: "ShowPriceView",
  components: {Load, TextTile, TextIconButton},
  props: {
    loading: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  computed: {
    ...mapGetters(['calculatedPrice'])
  },
  methods: {
    ...mapActions(['continueToCalculatedPriceStep', 'resetCalculatedPrice']),
    onGoBack() {
      this.resetCalculatedPrice();
      this.continueToCalculatedPriceStep(1);
    }
  }
};
</script>

<style scoped lang="scss">

.show-calculated-price-wrapper {
  height: 100%; /* REQUIRED FOR BUTTONS TO BE AT THE BOTTOM */
  /* CONTAINER HAS TO BE FLEX TOO */

  .bottom-buttons {
    flex-direction: row;
  }
}

</style>
