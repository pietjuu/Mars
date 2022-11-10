<template>
  <router-link :to="route" class="nav-item">
    <div class="nav-icon-wrapper">
      <Icon :icon="icon"/>
    </div>
    <div class="nav-content-wrapper">
      <p>{{ title }}</p>
    </div>
  </router-link>
</template>

<script>
import Icon from "@/components/Icon/Icon";

export default {
  name: "NavigationItem",
  props: {
    route: String,
    title: String,
    icon: String
  },
  components: {
    Icon
  }
}
</script>

<style scoped lang="scss">

.nav-item {
  color: var(--color-text);
  display: flex;
  flex-direction: row;
  align-items: center;
  min-height: 3rem;
  padding: 0 1rem;
  text-decoration: none;
}

.nav-content-wrapper {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-left: 0.625rem;
  padding: 0.75rem 0;
  flex: 1 1;
}

.nav-content-wrapper p {
  line-height: 1.2;
}

.nav-icon-wrapper {
  margin: 16px 0;
  align-self: flex-start;
  min-width: 1.5rem;
  display: inline-flex;
}

</style>
