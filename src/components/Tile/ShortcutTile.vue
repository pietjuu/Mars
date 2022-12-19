<template>
  <article class="tile box">
    <header class="flex-space-between-row flex-align-top">
      <h2>{{ title }}</h2>
    </header>
    <main class="flex-center-col">
      <Icon :icon="icon"/>
    </main>
  </article>
</template>

<script>
import Icon from "@/components/Icon/Icon.vue";

export default {
  name: "ShortcutTile",
  components: {Icon},
  props: {
    icon: String,
    title: String
  }
};
</script>

<style scoped lang="scss">

article {
  cursor: pointer;
  .icon {
    scale: 3.5;
  }
}

</style>
