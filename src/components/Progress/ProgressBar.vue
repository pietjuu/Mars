<template>
  <div class="progress-bar">
    <ProgressItem v-for="text in texts" :id="text" :text="text"/>
  </div>
</template>

<script>
import ProgressItem from "@/components/Progress/ProgressItem";

export default {
  name: "ProgressBar",
  props: {
    texts: Array
  },
  components: {
    ProgressItem
  }
}
</script>

<style scoped>

.progress-bar {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 1rem;
  background-image: url("@/assets/media/line.png");
  background-position: center center;
  background-repeat: no-repeat;
}

</style>