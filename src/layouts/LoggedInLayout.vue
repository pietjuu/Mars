<template>
  <LoadApp v-if="!loaded && !error.state"/>
  <Error v-if="error.state" :messages="error.messages"/>
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
import Error from "@/components/Error/Error";


export default {
  name: "LoggedInLayout",
  components: {
    Error,
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
      }
    };
  },
  computed: {
    ...mapGetters(['notificationShow', 'user', 'userRequest'])
  },
  created() {
    // load screen by default 2sec
    setTimeout(async () => {

      await this.fetchUser();

      if (this.userRequest.error) {
        this.error.state = true;
        this.error.messages.push(this.userRequest.message);
        this.error.messages.push("Contact shippert support...");
      }
      else {
        this.loaded = true;
        this.createNotification({content: `Welcome, ${this.user.firstname} ${this.user.lastname}!`});
      }
    }, 1000);
  },
  methods: {
    ...mapActions(["fetchUser", "createNotification"])
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
  height: 100vh;
  background-color: var(--color-background-mute);
  padding: 2.5rem;
  overflow: scroll;
}

</style>
