<template>
  <button class="flex-center-row" type="button" :style="{width: width, height: height, flexDirection:flexDirection}">
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
    content: {
      type: String,
      required: true
    },
    icon: {
      type: String,
      required: true
    },
    width: {
      type: String,
      required: true
    },
    height: {
      type: String,
      required: true
    },
    flexDirection: {
      type: String,
      default: 'row',
      required: false
    }
  }
};
</script>

<style scoped lang="scss">

button {
  border-radius: 0.375rem;
  gap: 0.5rem;
  min-width: 3.125rem;
  padding: 0 0.75rem;
}

</style>
