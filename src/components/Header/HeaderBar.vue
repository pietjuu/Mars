<template>
  <header class="header-wrapper">
    <div class="desktop-header">
      <div class="header-actions">
        <TextButton :content="`Support`"/>
        <IconButton :icon="`notifications`" :color="`var(--color-text)`"/>
        <ProfileButton :content="initials()"/>
      </div>
    </div>
  </header>
</template>

<script>
import { mapGetters} from 'vuex';

import TextButton from "@/components/Button/TextButton";
import IconButton from "@/components/Button/IconButton";
import ProfileButton from "@/components/Button/ProfileButton";

export default {
  name: "HeaderBar",
  methods: {
    initials() {
      const char1 = this.user.firstname.charAt(0).toUpperCase();
      const char2 = this.user.lastname.charAt(0).toUpperCase();
      return char1 + char2;
    }
  },
  computed: {
    ...mapGetters(['user'])
  },
  components: {
    TextButton,
    IconButton,
    ProfileButton
  }
}
</script>

<style scoped lang="scss">

.header-wrapper {
  height: 4rem;
  display: flex;
  flex: auto;
  align-items: center;
  justify-content: end;
  border-bottom: var(--border);
  box-shadow: var(--box-shadow);
  padding: 0 1rem;
}

.header-actions {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 2rem;
}

</style>
