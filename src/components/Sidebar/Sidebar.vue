<template>
  <div id="sidebar">
    <div class="logo-wrapper">
      <a href="/dashboard">
        <TransparentLogo :titleTxt="`Sidebar Logo Shippert`" :width="`70%`"/>
      </a>
    </div>
    <div class="navigation-wrapper">

    </div>
  </div>
</template>

<script>
import TransparentLogo from "@/components/Logo/TransparentLogo";

export default {
  name: "Sidebar",
  components: {
    TransparentLogo
  }
}
</script>

<style scoped lang="scss">
@use '@/assets/scss/constants.scss';

 div {
   width: 16rem;
   overflow: hidden;
   height: 100vh;
   border-right: var(--border);
 }

#sidebar > .logo-wrapper {
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 4rem;
  overflow: hidden;
}

#sidebar > .navigation-wrapper {
  height: calc(100vh - 4rem);
  padding-top: 0.625rem;
  overflow: auto;
}

</style>
