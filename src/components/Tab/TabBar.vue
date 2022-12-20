<template>
  <div class="tab-nav flex-gap-row">
      <Tab v-for="tab in tabs" :data-tab="tab.name" :id="tab.name" :text="tab.name" @click="currentTab = tab" :class="{active: currentTab.index === tab.index}"/>
  </div>
</template>

<script>
import Tab from "@/components/Tab/Tab.vue";

export default {
  name: "TabBar",
  components: {
    Tab
  },
  props: {
    tabs: Array
  },
  data() {
    return {
      currentTab: this.tabs[0]
    };
  }
};

</script>

<style scoped lang="scss">

.tab-nav {
  height: 3rem;
}

.active {
  color: var(--color-text-button);
  background: var(--color-secondary-soft);
}

</style>
