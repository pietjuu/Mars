<template>
  <nav class="navigation-items-wrapper">
    <NavigationItem :route="`/dashboard`" :title="`Dashboard`" :icon="`home`"/>
    <NavigationItem :route="`/send-item`" :title="`Send Item`" :icon="`outgoing_mail `"/>
    <NavigationItem :route="`/calculate-price`" :title="`Calculate Price`" :icon="`payments `"/>
    <NavigationItem :route="`/destinations`" :title="`Destinations`" :icon="`map `"/>
    <NavigationItem :route="`/statistics`" :title="`Statistics`" :icon="`monitoring `"/>
    <NavigationItem :route="`/history`" :title="`History`" :icon="`history `"/>
    <NavigationItem :route="`/blacklist`" :title="`Blacklist`" :icon="`block `"/>
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

.navigation-items-wrapper {

}

</style>