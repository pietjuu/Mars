<template>
  <div class="load-wrapper">
    <img src="@/assets/media/loading.gif" alt="Loading" title="Loading">
  </div>
</template>

<script>
export default {
  name: "LoadApp"
}
</script>

<style scoped lang="scss">

.load-wrapper {
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}

img {
  width: 5rem;
  height: 5rem;
}

</style>
