<template>
  <nav class="navigation-items-wrapper">
    <ul>
      <li><NavigationItem :routeName="`Dashboard`" :title="`Dashboard`" :icon="`home`"/></li>
      <li><NavigationItem :routeName="`Send Item`" :title="`Send Item`" :icon="`outgoing_mail `"/></li>
      <li><NavigationItem :routeName="`Calculate Price`" :title="`Calculate Price`" :icon="`payments `"/></li>
      <li><NavigationItem :routeName="`Destinations`" :title="`Destinations`" :icon="`map `"/></li>
      <li><NavigationItem :routeName="`Statistics`" :title="`Statistics`" :icon="`monitoring `"/></li>
      <li><NavigationItem :routeName="`History`" :title="`History`" :icon="`history `"/></li>
      <li><NavigationItem :routeName="`Blacklist`" :title="`Blacklist`" :icon="`block `"/></li>
    </ul>
  </nav>
</template>

<script>
import NavigationItem from "@/components/Navigation/NavigationItem";

export default {
  name: "SideNavigation",
  components: {
    NavigationItem
  }
}
</script>

<style scoped lang="scss">

</style>
