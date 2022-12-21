<template>
  <div class="progress-bar flex-space-between-row flex-gap-row">
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
};
</script>

<style scoped>

.progress-bar {
  background-image: url("@/assets/media/line.png");
  background-position: center center;
  background-repeat: no-repeat;
}

</style>
