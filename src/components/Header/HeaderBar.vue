<template>
  <header class="header-wrapper">
    <div class="desktop-header">
      <div class="header-actions">
        <TextButton :content="`Support`"/>
        <IconButton :icon="`notifications`"/>
        <ProfileButton :content="`XX`"/>
      </div>
    </div>
  </header>
</template>

<script>
import TextButton from "@/components/Button/TextButton";
import IconButton from "@/components/Button/IconButton";
import ProfileButton from "@/components/Button/ProfileButton";

export default {
  name: "HeaderBar",
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
