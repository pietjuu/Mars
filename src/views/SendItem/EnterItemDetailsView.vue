<template>
  <div class="item-details-wrapper">
    <div class="item-price box">
      <div class="item-price-title">
        <h2>Calculated Price to Send Item</h2>
        <Icon :icon="`payments`"/>
      </div>
      <div class="item-price-value">
        <span class="important">MC {{ price }}</span>
      </div>
    </div>
    <div class="enter-item-name box">
      <form action="#">
        <label for="item-name">Name your item <span>*</span></label>
        <input type="text" id="item-name" name="item-name" required autocomplete="off" placeholder="Name your item here" :value="`${this.user.firstname}'s Item`"/>
      </form>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import Icon from "@/components/Icon/Icon";

export default {
  name: "EnterItemDetailsView",
  data() {
    return {
      price: 200
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  methods: {
    ...mapActions(['setStepToInProgress'])
  },
  created() {
    this.setStepToInProgress(2);
  },
  components: {
    Icon
  }
}
</script>

<style scoped lang="scss">

h2 {
  font-size: var(--default-text-size);
  color: var(--color-text);
  margin: 0;
}

.item-details-wrapper {
  display: flex;
  gap: 1rem;
  min-height: 16rem;

  .item-price {
    flex: 1 1 20%;
    display: flex;
    flex-direction: column;
  }

  .enter-item-name {
    flex: 1 1 80%;
  }

}

.item-price-value {
  align-self: center;
  flex: 1 1 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.item-price-title {
  display: flex;
  justify-content: space-between;
}

</style>