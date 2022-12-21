<template>
<div class="connect-transporter-wrapper flex-gap-col">

    <InfoBox :text="info"/>

    <div class="form-and-message-wrapper flex-gap-row flex-space-between-row">

      <div class="important box flex-center-col">
        <img src="@/assets/media/transporter.png" alt="">
        <p>Place item in the Transporter and close the door!</p>
      </div>

      <div class="box">
        <form action="#" id="connect-transporter">
          <fieldset>
            <legend>Connect  Your Transporter</legend>
            <label for="transporter-id">Enter ID of The Transporter You want to Send <span>from</span> <span>*</span></label>
            <input type="text" id="transporter-id" name="transporter-id" required autocomplete="off" placeholder="Enter the ID here"/>
          </fieldset>
        </form>
      </div>
    </div>

    <div class="bottom-buttons">
      <TextIconButton :content="`Link`" :icon="`link`" :width="`6.5rem`" :height="`2.3rem`"/>
    </div>

</div>
</template>

<script>
import InfoBox from "@/components/Info/InfoBox.vue";
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions} from "vuex";

export default {
  name: "ConnectTransporterView",
  props: {
    info: String
  },
  components: {
    InfoBox,
    TextIconButton
  }
};
</script>

<style scoped lang="scss">

.connect-transporter-wrapper {
  height: 100%; /* REQUIRED FOR BUTTONS TO BE AT THE BOTTOM */
}

.form-and-message-wrapper {

  > .box {
    min-height: 10rem;
    flex: 1 1 100%;
  }

  .important {
    font-size: var(--h2-text-size);
    padding-bottom: 2rem;
  }
}

</style>
