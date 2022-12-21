<template>
  <div class="info-box box flex-center-vertical flex-gap-row">
    <div class="info-icon flex-center-row">
      <Icon :icon="`info`"/>
    </div>
    <div class="info-content">
      <p>{{ text }}</p>
    </div>
  </div>
</template>

<script>
import Icon from "@/components/Icon/Icon";

export default {
  name: "InfoBox",
  components: {
    Icon
  },
  props: {
    text: String
  }
};
</script>

<style scoped lang="scss">

.info-box {
  background-color: rgba(255, 255, 255, 30%);

  .info-icon {
    width: 2rem;
  }
}




</style>
