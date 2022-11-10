<template>
  <button type="button" :style="{'color':color}">
    <Icon :icon="icon"/>
  </button>
</template>

<script>
import Icon from "@/components/Icon/Icon";

export default {
  name: "IconButton",
  props: {
    icon: String,
    color: String
  },
  components: {
    Icon
  }
}
</script>

<style scoped lang="scss">

button {
  background: transparent;
  border-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 1.75rem;
  width: 3.125rem;
}

</style>
