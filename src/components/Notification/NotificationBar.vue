<template>
 <div v-show="show" class="notification" :class="type">
   <div class="notification-content">
     <p>{{ content }}</p>
   </div>
   <div class="close-notification">
     <IconButton :icon="`close`" :color="`var(--color-white)`" @click="closeIcon"/>
   </div>
 </div>
</template>

<script>
import IconButton from "@/components/Button/IconButton";

export default {
  name: "NotificationBar",
  data() {
    return {
      show: true
    }
  },
  created() {
    setTimeout(() => this.show = false, 8000)
  },
  components: {
    IconButton
  },
  props: {
    content: String,
    type: String
  },
  methods: {
    closeIcon() {
      this.show = false;
    }
  }
}
</script>

<style scoped>

.notification {
  color: var(--color-white);
  height: 4rem;
  background-color: var(--color-secondary-soft);
  justify-content: space-between;
  display: flex;
  align-items: center;
  padding: 0 1rem;
}

.success {
  background-color: var(--color-green);
}

.failed {
  background-color: var(--color-red);
}

.error {
  background-color: var(--color-orange);
}

</style>
