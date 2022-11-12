<template>
  <div class="sidebar">
    <div class="logo-wrapper">
      <a href="/dashboard">
        <TransparentLogo :titleTxt="`Sidebar Logo Shippert`" :width="`70%`"/>
      </a>
    </div>
    <div class="navigation-wrapper">
      <SideNavigation/>
    </div>
  </div>
</template>

<script>
import TransparentLogo from "@/components/Logo/TransparentLogo";
import SideNavigation from "@/components/Navigation/SideNavigation";

export default {
  name: "Sidebar",
  components: {
    TransparentLogo,
    SideNavigation
  }
}
</script>

<style scoped lang="scss">

 div {
   --logo-height: 4rem;

   border-right: var(--border);
 }

.sidebar > .logo-wrapper {
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: var(--logo-height);
  overflow: hidden;
}

.sidebar > .navigation-wrapper {
  height: calc(100vh - var(--logo-height));
  padding-top: 0.625rem;
  overflow: auto;
}

</style>
