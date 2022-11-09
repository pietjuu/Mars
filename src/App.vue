<template>
  <LoggedInLayout/>
</template>

<script>
import LoggedInLayout from "@/layouts/LoggedInLayout";

export default {
  name: "App",
  components: {
    LoggedInLayout
  }
};
</script>

<style lang="scss">
@use "@/assets/scss/main.scss";
</style>
