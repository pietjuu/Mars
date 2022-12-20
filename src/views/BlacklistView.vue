<template>
  <HeaderContent :title="`Blacklist`"/>
  <main class="main-content">
    <TabBar :tabs="tabs" @click="setCurrentTab"/>
    <div class="tab-content" v-if="currentTab === `My Blacklist`">
      <div class="add-blacklist-item box flex-space-between-row flex-gap-row flex-center-vertical">
        <input type="text" id="new-blacklist-item" name="new-blacklist-item" required autocomplete="off" placeholder="Add new blacklist item"/>
        <IconButton :icon="`add_circle`" :color="`var(--color-primary-soft)`" @click="createItem"/>
      </div>
      <div class="blacklist-items" v-if="this.userBlacklist !== undefined">
        <div v-for="item in this.userBlacklist.items" :data-item="item" :id="item + `-item`" class="blacklist-item box flex-space-between-row flex-center-vertical">
          <p>{{ item }}</p>
          <IconButton :icon="`delete`" :color="`var(--color-primary-soft)`" @click="deleteItem"/>
        </div>
      </div>
    </div>
    <div class="tab-content" v-if="currentTab === `Shippert Blacklist`">
      <div class="blacklist-items" v-if="this.shippertBlacklist !== undefined">
        <div v-for="item in this.shippertBlacklist.items" :id="item" class="box">
          <p>{{ item }}</p>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import TabBar from "@/components/Tab/TabBar.vue";
import IconButton from "@/components/Button/IconButton.vue";
import {mapActions, mapGetters} from "vuex";

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
    };
  },
  methods: {
    ...mapActions(['fetchUserBlacklist', 'fetchShippertBlacklist', 'deleteUserBlacklistItem', 'createUserBlacklistItem']),
    setCurrentTab(event) {
      if(!event.target.closest('button')) { return; }
      this.currentTab = event.target.dataset.tab;
    },
    deleteItem(event) {
      this.deleteUserBlacklistItem(event.target.closest('.blacklist-item').dataset.item);
    },
    createItem(event) {
      const input = event.target.closest('.add-blacklist-item').querySelector('#new-blacklist-item');
      const newItem = input.value;
      this.createUserBlacklistItem(newItem);
      input.value = "";
    }
  },
  computed: {
    ...mapGetters(['userBlacklist', 'shippertBlacklist'])
  },
  async created() {
    await this.fetchUserBlacklist();
    await this.fetchShippertBlacklist();
  }
};

</script>

<style scoped lang="scss">

.tab-content {
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.blacklist-items {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

</style>
