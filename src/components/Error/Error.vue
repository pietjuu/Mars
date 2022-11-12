<template>
  <TypeWriter :values="messages"/>
</template>

<script>
import TypeWriter from "@/components/Util/TypeWriter";

export default {
  name: "Error",
  components: {
    TypeWriter
  },
  props: {
    messages: Array
  }
}
</script>

<style scoped>

</style>