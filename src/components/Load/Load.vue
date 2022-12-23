<template>
  <div class="load-wrapper flex-center-row">
    <img src="@/assets/media/loading.gif" alt="Loading" title="Loading">
  </div>
</template>

<script>
export default {
  name: "Load"
};
</script>

<style scoped lang="scss">

.load-wrapper {
  height: 100%;
  width: 100%;
}

img {
  width: 5rem;
  height: 5rem;
}

</style>
