<template>
 <div class="notification" :class="notificationType">
   <div class="notification-content flex-center-row flex-gap-row">
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
  components: {
    IconButton,
    Icon
  },
  created() {
    setTimeout(this.close, 8000);
  },
  computed: {
    ...mapGetters(['notificationContent', 'notificationType', 'notificationShow'])
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
        case "warning":
          return `warning`;
        default:
          return `info`;
      }
    }
  },
  watch: {
    notificationShow(n, o) {
      if(n === true) {
        setTimeout(this.close, 8000);
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

.warning {
  background-color: var(--color-orange);
}

</style>
