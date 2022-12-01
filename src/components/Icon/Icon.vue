<template>
  <div class="icon">
    <span class="material-symbols-outlined ">{{ icon }}</span>
  </div>
</template>

<script>
export default {
  name: "Icon",
  props: {
    icon: String
  }
};
</script>

<style scoped lang="scss">

.icon {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  line-height: 1;
  position: relative;
}

</style>
