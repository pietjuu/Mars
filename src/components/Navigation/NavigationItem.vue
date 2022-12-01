<template>
  <router-link :to="route" class="no-text-decoration nav-item">
    <IconAndText :title="title" :icon="icon"/>
  </router-link>
</template>

<script>
import IconAndText from "@/components/Item/IconAndText";

export default {
  name: "NavigationItem",
  props: {
    route: String,
    title: String,
    icon: String
  },
  components: {
    IconAndText
  }
};
</script>

<style scoped lang="scss">

.nav-item {
  padding: 0 1rem;
}

</style>
