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
}
</script>

<style scoped lang="scss">

header {
  border-bottom: var(--divider);
  margin-bottom: 1rem;

  h1 {
    margin-bottom: 1rem;
    font-weight: bold;
  }

}

</style>