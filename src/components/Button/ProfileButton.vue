<template>
  <button>
    <span class="button-content">{{ content }}</span>
  </button>
</template>

<script>
export default {
  name: "ProfileButton",
  props: {
    content: String
  }
}
</script>

<style scoped lang="scss">

button {
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 2.5rem;
  width: 2.5rem;
  padding: 1rem;
}

</style>
