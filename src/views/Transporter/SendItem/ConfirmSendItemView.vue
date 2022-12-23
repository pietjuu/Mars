<template>
  <div class="confirm-send-item flex-gap-col">
    <div class="tiles flex-gap-row">
      <TextTile :title="`Price to Send Item`" :text="`MC ${calculatedPrice}`" :icon="`payments`" :text-size="`var(--large-text-size)`"/>
      <TextTile :title="`Item Name`" :text="`${itemName}`" :icon="`badge`" :text-size="`var(--large-text-size)`"/>
      <TextTile :title="`Receiver`" :text="`${receiver.name}`" :icon="`person`" :text-size="`var(--large-text-size)`"/>
      <TextTile :title="`Destination`" :text="`${destination.name}`" :icon="`location_on`" :text-size="`var(--large-text-size)`"/>
    </div>
    <div class="bottom-buttons">
      <TextIconButton :content="`Send Item`" :icon="`send`" :width="`10rem`" :height="`2.3rem`" @click="onSendItem" :flexDirection="`row-reverse`"/>
      <TextIconButton :content="`Previous Step`" :icon="`arrow_back_ios`" :width="`10rem`" :height="`2.3rem`" @click="onPreviousStep"/>
    </div>
  </div>
</template>

<script>
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions, mapGetters} from "vuex";
import router from "@/router";
import TextTile from "@/components/Tile/TextTile.vue";

export default {
  name: "ConfirmSendItemView",
  components: {TextTile, TextIconButton},
  computed: {
   ...mapGetters(['calculatedPrice', 'itemName', 'receiver', 'destination'])
  },
  methods: {
    ...mapActions(['continueToSendItemStep', 'sendItem']),
    onSendItem(e) {
      this.sendItem();
      router.push({path: `/dashboard`});
    },
    onPreviousStep(e) {
      this.continueToSendItemStep(3);
    }
  }
};
</script>

<style scoped lang="scss">

.confirm-send-item {
  height: 100%;
}

.tiles {
  flex-wrap: wrap;
  > * {
    flex: 1 1 20%;
  }
}

</style>
