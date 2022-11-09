<template>
  <div id="logged-in-layout">
    <div class="sidebar-wrapper">
      <Sidebar/>
    </div>
    <div class="header-and-content-wrapper">
    </div>
  </div>
</template>

<script>
import Sidebar from "@/components/Sidebar/Sidebar";
import HeaderBar from "@/components/Header/HeaderBar";
import NotificationBar from "@/components/Notification/NotificationBar";
import MainContent from "@/components/Main/MainContent";

export default {
  name: "BaseLayout",
  components: {
    Sidebar,
    HeaderBar,
    NotificationBar,
    MainContent
  }
}
</script>

<style scoped lang="scss">

#logged-in-layout {
  position: fixed;
  width: 100vw;
  height: 100vw;
  overflow: hidden;
}

.sidebar-wrapper {
  position: relative;
  float: left;
}

</style>
