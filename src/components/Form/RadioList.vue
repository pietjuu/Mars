<template>
  <div class="radio-list" :style="{'height':height}">

    <div v-for="item in items" class="flex-space-between-row flex-center-vertical radio-container">
      <label :for="valueToId(item.value)">{{ item.label }}</label>
      <input type="radio" :id="valueToId(item.value)" :name="name" :value="item.value" :data-label="item.label">
    </div>

  </div>
</template>

<script>
import { convertSpacesToUnderscores } from "@/assets/js/helper";

export default {
  name: "RadioList",
  props: {
    name: String,
    items: Array,
    height: String
  },
  methods: {
    valueToId(value) {
      return convertSpacesToUnderscores(value.toLowerCase());
    },
  }
};
</script>

<style scoped lang="scss">

.radio-list {
  overflow: scroll;
  padding: 0 1rem;
}

.radio-container {
  border-bottom: dashed var(--color-primary-soft) 0.1rem;
  margin-bottom: 0.5rem;
}

label, input {
  color: var(--color-text);
  display: inline-block;
  width: fit-content;
  margin: 0;
}

</style>
