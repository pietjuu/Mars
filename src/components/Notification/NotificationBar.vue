<template>
 <div class="notification" :class="notificationType">
   <div class="notification-content flex-center-row">
     <Icon :icon="icon()" :color="`var(--color-white)`"/>
     <p>{{ notificationContent }}</p>
   </div>
   <div class="close-notification">
     <IconButton :icon="`close`" :color="`var(--color-white)`" @click="close"/>
   </div>
 </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

import IconButton from "@/components/Button/IconButton";
import Icon from "@/components/Icon/Icon";

export default {
  name: "NotificationBar",
  created() {
    setTimeout(this.close, 8000);
  },
  components: {
    IconButton,
    Icon
  },
  computed: {
    ...mapGetters(['notificationContent', 'notificationType'])
  },
  methods: {
    ...mapActions(['removeNotification']),
    close() {
      this.removeNotification();
    },
    icon() {
      switch (this.notificationType){
        case "error":
          return `error`;
        case "success":
          return `check_circle`;
        default:
          return `info`;
      }
    }
  }
};
</script>

<style scoped>

.notification {
  color: var(--color-white);
  height: 4rem;
  background-color: var(--color-secondary-soft);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 1rem;
}

.notification-content {
  gap: 1rem;
}

.info {
  background-color: var(--color-secondary-soft);
}

.success {
  background-color: var(--color-green);
}

.error {
  background-color: var(--color-red);
}

</style>
