<template>
  <button type="button">
      <span class="button-content">{{ content }}</span>
  </button>
</template>

<script>
export default {
  name: "TextButton",
  props: {
    content: String
  }
}
</script>

<style scoped lang="scss">

button {
  border-radius: 0.375rem;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 1.75rem;
  min-width: 3.125rem;
  padding: 0 0.75rem;
}

</style>
