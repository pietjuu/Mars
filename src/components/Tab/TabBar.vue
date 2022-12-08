<template>
  <div class="tab-nav flex-gap">
      <Tab v-for="tab in tabs" :id="tab" :text="tab" @click="currentTab = tab" :class="{active: currentTab === tab}"/>
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
  color: #A612DA;
  font-weight: bold;
}

</style>
