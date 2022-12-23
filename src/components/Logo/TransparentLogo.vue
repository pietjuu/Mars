<template>
  <img :style="{'width': width }" src="@/assets/media/logo.png" :alt="titleTxt" :title="titleTxt">
</template>

<script>
export default {
  name: "TransparentLogo",
  props: {
    titleTxt: String,
    width: String
  }
};
</script>
