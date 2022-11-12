<template>
  <main class="main-content">
    <router-view/>
  </main>
</template>

<script>
export default {
  name: "MainContent"
}
</script>

<style scoped lang="scss">
  main {
    height: 100vh;
    background-color: var(--color-background-mute);
  }
</style>
