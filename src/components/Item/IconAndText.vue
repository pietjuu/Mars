<template>
  <div class="item flex-center-vertical">
    <div class="item-icon-wrapper">
      <Icon :icon="icon"/>
    </div>
    <div class="item-content-wrapper flex-center-vertical">
      <p>{{ title }}</p>
    </div>
  </div>
</template>

<script>
import Icon from "@/components/Icon/Icon";

export default {
  name: "IconAndText",
  props: {
    title: String,
    icon: String
  },
  components: {
    Icon
  }
};
</script>

<style scoped lang="scss">

.item {
  color: var(--color-text);
  min-height: 3rem;
}

.item-content-wrapper {
  flex-wrap: wrap;
  margin-left: 0.625rem;
  padding: 0.75rem 0;
  flex: 1 1;
}

.item-content-wrapper p {
  line-height: 1.2;
}

.item-icon-wrapper {
  margin: 16px 0;
  align-self: flex-start;
  min-width: 1.5rem;
  display: inline-flex;
}

</style>
