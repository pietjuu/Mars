<template>
  <div class="sidebar">
    <div class="logo-wrapper flex-center-row">
      <router-link :to="`/dashboard`">
        <TransparentLogo :titleTxt="`Sidebar Logo Shippert`" :width="`70%`"/>
      </router-link>
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
};
</script>

<style scoped lang="scss">

 .sidebar {
   --logo-height: 4rem;

   border-right: var(--border);
 }

.sidebar > .logo-wrapper {
  text-align: center;
  height: var(--logo-height);
  overflow: hidden;
}

.sidebar > .navigation-wrapper {
  height: calc(100vh - var(--logo-height));
  padding-top: 0.625rem;
  overflow: auto;
}

</style>
