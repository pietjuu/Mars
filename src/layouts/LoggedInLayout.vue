<template>
  <LoadApp v-if="!loaded && !error.state"/>
  <ErrorWriter v-if="error.state" :messages="error.messages"/>
  <div id="logged-in-layout" v-if="loaded">
    <div class="sidebar-wrapper">
      <Sidebar/>
    </div>
    <div class="header-and-content-wrapper">
      <HeaderBar/>
      <NotificationBar v-show="notificationShow"/>
      <section class="content-wrapper">
        <router-view/>
      </section>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

import LoadApp from "@/components/Load/LoadApp";
import Sidebar from "@/components/Sidebar/Sidebar";
import HeaderBar from "@/components/Header/HeaderBar";
import NotificationBar from "@/components/Notification/NotificationBar";
import ErrorWriter from "@/components/Error/ErrorWriter.vue";


export default {
  name: "LoggedInLayout",
  components: {
    ErrorWriter,
    LoadApp,
    Sidebar,
    HeaderBar,
    NotificationBar
  },
  data() {
    return {
      loaded: false,
      error: {
        state: false,
        messages: []
      },
      reloadTimer: undefined
    };
  },
  computed: {
    ...mapGetters(['notificationShow', 'user', 'userRequest'])
  },
  async mounted() {

      await this.fetchUser().then(() => {
        if (this.userRequest.error) {
          this.error.state = true;
          this.error.messages.push(this.userRequest.message);
          this.error.messages.push("Contact shippert support...");
        }
        else {
          this.loaded = true;
          this.reload();
          this.createNotification({content: `Welcome, ${this.user.firstname} ${this.user.lastname}!`});
        }
      });

  },
  methods: {
    ...mapActions(["fetchUser", "createNotification", 'fetchTransporters', 'fetchUsers', 'fetchUserItems']),
    reload() {
      this.fetchUser();
      this.fetchTransporters();
      this.fetchUsers();
      this.fetchUserItems();
      this.reloadTimer = setTimeout(this.reload, 2000);
    }
  },
  destroyed() {
    clearTimeout(this.reloadTimer);
  }
};
</script>

<style scoped lang="scss">


#logged-in-layout {
  --sidebar-width: 16rem;

  position: fixed;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

.sidebar-wrapper {
  @include pos-fixed-top-left();
  overflow: hidden;
  height: 100vh;
  width: var(--sidebar-width);
}

.header-and-content-wrapper {
  position: relative;
  height: 100vh;
  margin-left: var(--sidebar-width);
}

.content-wrapper {
  /* 5rem from padding and 4rem is from header bar */
  height: calc(100vh - 5rem - 4rem);
  background-color: var(--color-background-mute);
  padding: 2.5rem;
  overflow: auto;
}

</style>
