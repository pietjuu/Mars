<template>
  <article class="box tile">
    <header class="flex-space-between-row flex-align-top">
      <h2>{{ title }}</h2>
      <Icon :icon="icon"/>
    </header>
    <main class="flex-center-vertical">
      <p class="important" :style="{'font-size': textSize}">{{ text }}</p>
    </main>
  </article>
</template>

<script>
import Icon from "@/components/Icon/Icon.vue";

export default {
  name: "TextTile",
  components: {Icon},
  props: {
    title: {
      type: String,
      required: true
    },
    icon: {
      type: String,
      required: true
    },
    text: {
      type: String,
      required: true
    },
    textSize: {
      type: String,
      required: false
    }
  }
};
</script>
