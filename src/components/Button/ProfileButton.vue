<template>
  <button type="button" class="flex-center-row">
    <span class="button-content">{{ content }}</span>
  </button>
</template>

<script>
export default {
  name: "ProfileButton",
  props: {
    content: String
  }
};
</script>

<style scoped lang="scss">

button {
  border-radius: 50%;
  @include size(2.5rem);
  padding: 1rem;
}

</style>
