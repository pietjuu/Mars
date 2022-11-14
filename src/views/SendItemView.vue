<template>
  <div class="send-item-wrapper">
    <HeaderContent :title="`Send Item`"/>
    <main>
      <ProgressBar :texts="[`Step 1`, `Step 2`, `Step 3`, `Step 4`]"/>
    </main>
  </div>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import ProgressBar from "@/components/Progress/ProgressBar";

export default {
  name: "SendItemView",
  components: {
    HeaderContent,
    ProgressBar
  }
}
</script>

<style scoped lang="scss">

</style>