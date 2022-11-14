<template>
  <button type="button" :style="{width: width, height: height}">
    <Icon :icon="icon"/>
    <span class="button-content">{{ content }}</span>
  </button>
</template>

<script>
import Icon from "@/components/Icon/Icon";
export default {
  name: "TextIconButton",
  components: {
    Icon
  },
  props: {
    content: String,
    icon: String,
    width: String,
    height: String
  }
}
</script>

<style scoped lang="scss">

button {
  border-radius: 0.375rem;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  min-width: 3.125rem;
  padding: 0 0.75rem;
}

</style>