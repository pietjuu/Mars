<template>
  <header class="content-header">
    <h1>{{ title }}</h1>
  </header>
</template>

<script>
export default {
  name: "HeaderContent",
  props: {
    title: String
  }
};
</script>

<style scoped lang="scss">

header {
  /* total height is 5rem - value used in <main/> ! */
  border-bottom: var(--divider);
  height: 3.5rem;
  margin-bottom: 1.5rem;

  h1 {
    font-weight: bold;
  }

}

</style>
