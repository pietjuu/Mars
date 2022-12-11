<template>
  <div class="blacklist-tab">
    <p>{{ text }}</p>
  </div>
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

p {
  font-size: var(--medium-text-size);
  text-transform: uppercase;
}

</style>
