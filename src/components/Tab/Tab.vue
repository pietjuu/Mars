<template>
  <button class="box">
    {{ text }}
  </button>
</template>

<script>
export default {
  name: "Tab",
  props: {
    text: String
  }
};

</script>

<style scoped lang="scss">

button {
  background-color: transparent;
  font-size: var(--small-text-size);
  height: fit-content;
  color: var(--color-primary-soft);
}

</style>
