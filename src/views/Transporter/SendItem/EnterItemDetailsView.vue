<template>
  <div class="item-details-wrapper flex-gap-col">
    <div class="flex-gap-row">
      <TextTile class="price-tile" :title="`Cost to Send Item`" :icon="`payments`" :text="`MC ${this.calculatedPrice}`"/>
      <form class="item-name-tile box" action="#">
        <label for="item-name">Name your item</label>
        <input v-model="itemName" type="text" id="item-name" name="item-name" required autocomplete="off" placeholder="Name your item here"/>
      </form>
    </div>
    <div class="bottom-buttons">
      <TextIconButton :content="`Next Step`" :icon="`arrow_forward_ios`" :width="`10rem`" :height="`2.3rem`" @click="onNextStep" :flexDirection="`row-reverse`"/>
      <TextIconButton :content="`Previous Step`" :icon="`arrow_back_ios`" :width="`10rem`" :height="`2.3rem`" @click="onPreviousStep"/>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import Icon from "@/components/Icon/Icon.vue";
import TextTile from "@/components/Tile/TextTile.vue";
import TextIconButton from "@/components/Button/TextIconButton.vue";

export default {
  name: "EnterItemDetailsView",
  data() {
    return {
      itemName: undefined
    };
  },
  computed: {
    ...mapGetters(['user', 'calculatedPrice'])
  },
  components: {
    TextIconButton,
    TextTile,
    Icon
  },
  created() {
    this.itemName = `${this.user.firstname}'s Item`;
  },
  methods: {
    ...mapActions(['continueToSendItemStep', 'createNotification', 'saveItemName']),
    onNextStep(e) {
      this.itemName = this.itemName.trim();
      if(this.itemName === undefined || this.itemName.length < 1 ) {
        this.createNotification({content: "Please enter a valid item name!", type: `warning`});
        return;
      }
      else if(this.itemName.length > 20) {
        this.createNotification({content: "Item name cannot exceed more than 20 characters!", type: `warning`});
        return;
      }
      this.saveItemName(this.itemName);
    },
    onPreviousStep(e) {
      this.continueToSendItemStep(1);
    }
  }
};
</script>

<style scoped lang="scss">

.item-details-wrapper {
  height: 100%;

  .price-tile {
    flex: 1 1 40%;
  }

  .item-name-tile {
    flex: 1 1 60%;
  }

}

form.item-name-tile {
  label {
    flex: 1 1 80%;
  }
  input {
    align-self: center;
    flex: 1 1 100%
  }
}

</style>
