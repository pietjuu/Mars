<template>
  <HeaderContent :title="`Dashboard`"/>
  <main class="main-content">
    <div class="box">
      test
    </div>
    <div class="box">
      test
    </div>
    <div class="box">
      test
    </div>
    <div class="box">
      test
    </div>
    <div class="box">
      test
    </div>
    <div class="box">
      test
    </div>
    <div class="box notification">
      test
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent.vue";

export default {
  name: "DashboardView",
  components: {HeaderContent}
}
</script>

<style scoped lang="scss">

.main-content {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(3, 10rem);
  grid-gap: 1.25rem;
  grid-template-areas:
    ". . . ."
    ". notif notif notif"
    ". notif notif notif";

}

.notification {
  grid-area: notif;
}

</style>