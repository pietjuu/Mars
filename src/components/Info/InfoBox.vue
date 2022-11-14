<template>
  <div class="info-box box">
    <div class="info-icon">
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
}
</script>

<style scoped lang="scss">

.info-box {
  background-color: rgba(255, 255, 255, 30%);
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
  min-height: 2rem;

  .info-icon {
    width: 2rem;
    display: flex;
    align-items: center;
    justify-content: center;

  }
}




</style>