<template>
  <div class="progress-bar">
    <ProgressItem v-for="step in steps" :id="step.number" :text="step.text" :inProgress="step.inProgress"/>
  </div>
</template>

<script>
import ProgressItem from "@/components/Progress/ProgressItem";

export default {
  name: "ProgressBar",
  props: {
    steps: Array
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
  margin-bottom: 1rem;
}

</style>