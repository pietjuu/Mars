<template>
  <HeaderContent :title="`History`"/>
  <main class="main-content flex-gap-col">
    <div class="flex-space-between-row">
      <TabBar :tabs="tabs" @click="setCurrentTab"/>
      <Dropdown :name="`sort`" :values="sortTypes" @click="this.getSelectedSortType"/>
    </div>
    <HistoryTable v-if="this.userItems !== undefined" :items="this.getItems()"/>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import TabBar from "@/components/Tab/TabBar.vue";
import HistoryTable from "@/components/Table/HistoryTable.vue";
import {mapActions, mapGetters} from "vuex";
import Dropdown from "@/components/Form/Dropdown.vue";

export default {
  name: "HistoryView",
  components: {Dropdown, HistoryTable, TabBar, HeaderContent},
  data() {
    return {
      sortTypes: ['Newest first', 'Oldest first'],
      selectedSortType: 'Newest first',
      tabs: [
        { name:'All', index:0 },
        { name:'Sent', index:1 },
        { name:'Received', index:2 }
      ],
      currentTab: 'All'
    };
  },
  computed: {
    ...mapGetters(['userItems'])
  },
  methods: {
    ...mapActions(['fetchUserItems']),
    setCurrentTab(event) {
      if(!event.target.closest('button')) {
        return;
      }
      this.currentTab = event.target.dataset.tab;
    },
    compareNewestDateFirst(a, b) {
      const dateA = new Date(a.timeSent);
      const dateB = new Date(b.timeSent);

      if (dateA > dateB) {
        return -1;
      }
      if (dateA < dateB) {
        return 1;
      }
      return 0;
    },
    compareOldestDateFirst(a, b) {
      const dateA = new Date(a.timeSent);
      const dateB = new Date(b.timeSent);

      if (dateA < dateB) {
        return -1;
      }
      if (dateA > dateB) {
        return 1;
      }
      return 0;
    },
    sortItems() {
      if(this.selectedSortType === this.sortTypes[0]) {
        return this.userItems.sort(this.compareNewestDateFirst);
      }
      else if(this.selectedSortType === this.sortTypes[1]) {
        return this.userItems.sort(this.compareOldestDateFirst);
      }
      else {
        return this.userItems;
      }
    },
    getItems() {
      if(this.currentTab === 'Sent') {
        return this.sortItems().filter(item => item.action === 'sent');
      }
      else if (this.currentTab === 'Received') {
        return this.sortItems().filter(item => item.action === 'received');
      }
      else {
        return this.sortItems();
      }
    },
    getSelectedSortType(event) {
      if(!event.target.closest('select')) {
        return;
      }
      this.selectedSortType = event.target.value;
    }
  },
  created() {
    this.fetchUserItems();
  }
};
</script>

<style scoped lang="scss">

</style>
