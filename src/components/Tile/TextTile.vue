<template>
  <article class="box tile">
    <header class="flex-space-between-row flex-align-top">
      <h2>{{ title }}</h2>
      <Icon :icon="icon"/>
    </header>
    <main class="flex-center-vertical">
      <p class="important">{{ text }}</p>
    </main>
  </article>
</template>

<script>
import Icon from "@/components/Icon/Icon.vue";

export default {
  name: "TextTile",
  components: {Icon},
  props: {
    title: String,
    icon: String,
    text: String
  }
};
</script>

<style scoped lang="scss">

</style>
