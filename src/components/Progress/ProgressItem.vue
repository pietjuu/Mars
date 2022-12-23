<template>
  <div class="progress-item box" :class="{ 'in-progress': inProgress }">
    <span>{{ text }}</span>
  </div>
</template>

<script>
export default {
  name: "ProgressItem",
  props: {
    text: String,
    inProgress: Boolean
  }
};
</script>

<style scoped lang="scss">

  .progress-item {
    display: flex;
    align-items: center;
    justify-content: start;
    flex: 1 1 100%;
    min-width: 5rem;
  }

  .in-progress {
    color: var(--color-white);
    background-color: var(--color-secondary-soft);
  }


</style>
