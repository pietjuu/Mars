<template>
  <div class="error-wrapper flex-center-col">
    <TypeWriter :values="messages"/>
    <TextButton :content="`Support`" @click="goToSupport()"/>
  </div>
</template>

<script>
import TypeWriter from "@/components/Util/TypeWriter";
import TextButton from "@/components/Button/TextButton";

export default {
  name: "Error",
  components: {
    TypeWriter,
    TextButton
  },
  props: {
    messages: Array
  },
  methods: {
    goToSupport() {
      window.open(`https://thiboverbeerst.wixsite.com/shippert/contact-4`);
    }
  }
};
</script>

<style scoped>

.error-wrapper {
  height: 100vh;
  width: 100vw;
}

</style>
