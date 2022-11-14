<template>
  <div class="progress-item">
    <span>{{ text }}</span>
  </div>
</template>

<script>
export default {
  name: "ProgressItem",
  props: {
    text: String
  }
}
</script>

<style scoped lang="scss">

  .progress-item {
    background-color: var(--color-background);
    display: flex;
    align-items: center;
    justify-content: start;
    padding: 0.5rem 1rem;
    border: var(--border);
    border-radius: var(--box-radius);
    flex: 1 1 100%;
    min-width: 5rem;
  }


</style>