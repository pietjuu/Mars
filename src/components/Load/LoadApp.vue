<template>
  <div class="load-wrapper flex-center-row">
    <img src="@/assets/media/loading.gif" alt="Loading" title="Loading">
  </div>
</template>

<script>
export default {
  name: "LoadApp"
};
</script>

<style scoped lang="scss">

.load-wrapper {
  height: 100vh;
  width: 100vw;
}

img {
  @include size(5rem);
}

</style>
