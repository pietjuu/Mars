<template>
<div class="connect-transporter-wrapper">

    <InfoBox :text="infoText"/>

    <div class="form-and-message-wrapper flex-gap-row flex-space-between-row">

      <div class="info-message box flex-center-col">
        <img src="@/assets/media/transporter.png" alt="">
        <p>Place item in the Transporter and close the door!</p>
      </div>

      <div class="box">
        <form action="#" id="connect-transporter">
          <fieldset>
            <legend>Connect  Your Transporter</legend>

            <label for="transporter-id">Enter ID of The Transporter You want to Send <span>from</span> <span>*</span></label>
            <div class="flex-center-vertical">
              <input type="text" id="transporter-id" name="transporter-id" required autocomplete="off" placeholder="Enter the ID here"/>
              <TextIconButton :content="`Link`" :icon="`link`" :width="`6.5rem`" :height="`2.3rem`"/>
            </div>

          </fieldset>
        </form>
      </div>


    </div>
</div>
</template>

<script>
import InfoBox from "@/components/Info/InfoBox";
import TextIconButton from "@/components/Button/TextIconButton";
import {mapActions} from "vuex";

export default {
  name: "ConnectTransporterView",
  data() {
    return {
      infoText: `To send your item, we need to know which Transporter you’re sending from. To connect to your transporter, whom you’re sending from, you must enter the Transporter ID. Afterwards you can click “Link”.`
    };
  },
  methods: {
    ...mapActions(['setStepToInProgress'])
  },
  components: {
    InfoBox,
    TextIconButton
  },
  created() {
    this.setStepToInProgress(1);
  }
};
</script>

<style scoped lang="scss">

.form-and-message-wrapper {

  > .box {
    min-height: 10rem;
    flex: 1 1 100%;
  }

  .info-message {
    color: var(--color-heading2);
    font-size: var(--h2-text-size);
    font-weight: bold;
    padding-bottom: 2rem;
  }

  fieldset .flex-center-vertical {
    gap: 0.5rem;
  }


}

</style>
