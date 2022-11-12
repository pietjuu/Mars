<template>
  <LoadApp v-if="!loaded"/>
  <div id="logged-in-layout" v-if="loaded">
    <div class="sidebar-wrapper">
      <Sidebar/>
    </div>
    <div class="header-and-content-wrapper">
      <HeaderBar/>
      <NotificationBar v-show="notificationShow"/>
      <MainContent/>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

import LoadApp from "@/components/Load/LoadApp";
import Sidebar from "@/components/Sidebar/Sidebar";
import HeaderBar from "@/components/Header/HeaderBar";
import NotificationBar from "@/components/Notification/NotificationBar";
import MainContent from "@/components/Main/MainContent";


export default {
  name: "BaseLayout",
  components: {
    LoadApp,
    Sidebar,
    HeaderBar,
    NotificationBar,
    MainContent
  },
  data() {
    return {
      loaded: false
    }
  },
  computed: {
    ...mapGetters(['notificationShow', 'user'])
  },
  created() {
    // load screen by default 2sec
    setTimeout(async () => {
      // set loaded true after all requests finished
      await this.fetchUser();
      this.loaded = true;

      this.createNotification({content: `Welcome, ${this.user.firstname} ${this.user.lastname}!`});
    }, 1000);
  },
  methods: {
    ...mapActions(["fetchUser", "createNotification"])
  }
}
</script>

<style scoped lang="scss">


#logged-in-layout {
  --sidebar-width: 16rem;

  position: fixed;
  width: 100vw;
  height: 100vw;
  overflow: hidden;
}

.sidebar-wrapper {
  position: fixed;
  left: 0;
  top: 0;
  overflow: hidden;
  height: 100vh;
  width: var(--sidebar-width);
}

.header-and-content-wrapper {
  height: 100vh;
  position: relative;
  margin-left: var(--sidebar-width);
}

</style>
