<template>
  <HeaderContent :title="`Blacklist`"/>
  <main class="main-content">
    <TabBar :tabs="tabs" @click="setCurrentTab"/>
    <div class="tab-content" v-if="currentTab === `My Blacklist`">
      <div class="add-blacklist-item box flex-space-between-row flex-gap flex-center-vertical">
        <input type="text" id="new-blacklist-item" name="new-blacklist-item" required autocomplete="off" placeholder="Add new blacklist item"/>
        <IconButton :icon="`add_circle`" :color="`var(--color-primary-soft)`"/>
      </div>
      <div>
        <div>ddd</div>
        <div>fdff</div>
      </div>
    </div>
    <div class="tab-content" v-if="currentTab === `Shippert Blacklist`">
      test
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import TabBar from "@/components/Tab/TabBar.vue";
import IconButton from "@/components/Button/IconButton.vue";

export default {
  name: "BlacklistView",
  components: {
    IconButton,
    TabBar,
    HeaderContent
  },
  data() {
    return {
      tabs: [
        { name:'My Blacklist', index:0 },
        { name:'Shippert Blacklist', index:1 }
      ],
      currentTab: 'My Blacklist'
    }
  },
  methods: {
    setCurrentTab(tab) {
      if(!tab.target.closest('button')) { return; }
      this.currentTab = tab.target.dataset.tab;
    }
  }
};

</script>

<style scoped lang="scss">

.tab-content {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

</style>
