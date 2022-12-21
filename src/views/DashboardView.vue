<template>
  <HeaderContent :title="`Dashboard`"/>
  <main class="main-content">
    <TextTile :title="`My Daily Limit`" :icon="`security`" :text="`${this.user.limit.reached} / ${this.user.limit.max !== -1 ? this.user.limit.max : 'Unlimited'}`"/>
    <TextTile :title="`Total number of sent items`" :icon="`unarchive`" :text="`${this.user.totalSent}`"/>
    <TextTile :title="`Total number of received items`" :icon="`archive`" :text="`${this.user.totalReceived}`"/>
    <TextTile :title="`Total number of sent/received items`" :icon="`all_inbox`" :text="`${this.user.totalSent +  this.user.totalReceived}`"/>

    <ShortcutTile :title="`Send Item`" :icon="`send`" @click="navToSendItem"/>
    <ShortcutTile :title="`Calculate Price`" :icon="`payments`" @click="navToCalculatePrice"/>

    <article class="box tile recent-notifications">
      <header class="flex-space-between-row flex-align-top">
        <h2>Recent Notifications</h2>
        <Icon :icon="`notifications`"/>
      </header>
      <main v-if="this.user.notifications !== undefined">
        <NotificationItem v-if="this.user.notifications.length >= 1" :notification="this.user.notifications.at(-1)"/>
        <NotificationItem v-if="this.user.notifications.length >= 2" :notification="this.user.notifications.at(-2)"/>
      </main>
    </article>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";
import Icon from "@/components/Icon/Icon.vue";
import {mapGetters} from "vuex";
import router from "@/router";
import TextTile from "@/components/Tile/TextTile.vue";
import ShortcutTile from "@/components/Tile/ShortcutTile.vue";
import NotificationItem from "@/components/Notification/NotificationItem.vue";

export default {
  name: "DashboardView",
  components: {NotificationItem, ShortcutTile, TextTile, Icon, HeaderContent},
  computed: {
    ...mapGetters(['user'])
  },
  methods: {
    navToCalculatePrice() {
      router.push({path: `/calculate-price`});
    },
    navToSendItem() {
      router.push({path: `/send-item`});
    }
  }
};
</script>

<style scoped lang="scss">

.main-content {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(3, 10rem);
  grid-gap: 2.5rem;
  grid-template-areas:
    ". . . ."
    ". notif notif notif"
    ". notif notif notif";

}

.recent-notifications {
  grid-area: notif;

  main {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    overflow: scroll;
  }

}

</style>
