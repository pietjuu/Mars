<template>
  <button type="button">
    <Icon :icon="icon"/>
  </button>
</template>

<script>
import Icon from "@/components/Icon/Icon";

export default {
  name: "IconButton",
  props: {
    icon: String
  },
  components: {
    Icon
  }
}
</script>

<style scoped lang="scss">

button {
  background: var(--color-background);
  color: var(--color-text);
  border-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 1.75rem;
  width: 3.125rem;
}

</style>
