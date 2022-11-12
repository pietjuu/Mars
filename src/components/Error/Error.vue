<template>
  <TypeWriter :value="msg"/>
</template>

<script>
import TypeWriter from "@/components/Util/TypeWriter";

export default {
  name: "Error",
  components: {
    TypeWriter
  },
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>