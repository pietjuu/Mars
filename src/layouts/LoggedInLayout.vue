<template>
  <div id="logged-in-layout">
    <div class="sidebar-wrapper">
      <Sidebar/>
    </div>
    <div class="header-and-content-wrapper">
      <HeaderBar/>
      <NotificationBar :content="`Item is send to destination.`" :type="`error`"/>
      <MainContent/>
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
