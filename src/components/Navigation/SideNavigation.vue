<template>
  <nav class="navigation-items-wrapper">
    <ul>
      <li><NavigationItem :route="`/dashboard`" :title="`Dashboard`" :icon="`home`"/></li>
      <li><NavigationItem :route="`/send-item`" :title="`Send Item`" :icon="`outgoing_mail `"/></li>
      <li><NavigationItem :route="`/calculate-price`" :title="`Calculate Price`" :icon="`payments `"/></li>
      <li><NavigationItem :route="`/destinations`" :title="`Destinations`" :icon="`map `"/></li>
      <li><NavigationItem :route="`/statistics`" :title="`Statistics`" :icon="`monitoring `"/></li>
      <li><NavigationItem :route="`/history`" :title="`History`" :icon="`history `"/></li>
      <li><NavigationItem :route="`/blacklist`" :title="`Blacklist`" :icon="`block `"/></li>
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
};
</script>
