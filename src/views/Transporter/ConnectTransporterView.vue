<template>
<div class="connect-transporter-wrapper flex-gap-col">

    <InfoBox :text="info"/>

    <div class="form-and-message-wrapper flex-gap-row">

      <div class="important box flex-center-col">
        <img src="@/assets/media/transporter.png" alt="">
        <p>Place item in the Transporter and close the door!</p>
      </div>

      <div class="box">
        <form action="#" id="connect-transporter">
          <fieldset>
            <legend>Connect  Your Transporter</legend>
            <RadioListWithSearch
                :placeholderSearchBox="`Search name of transporter`" :name="`transporter-names`"
                :items="this.transporterRadioListItems()" :radioListMaxHeight="`15rem`" @select="onSelectTransporter"/>
          </fieldset>
        </form>
      </div>
    </div>

    <div class="bottom-buttons">
      <TextIconButton :content="`Link`" :icon="`link`" :width="`6.5rem`" :height="`2.3rem`" @click="link"/>
    </div>

</div>
</template>

<script>
import InfoBox from "@/components/Info/InfoBox.vue";
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions, mapGetters} from "vuex";
import RadioList from "@/components/Form/RadioList.vue";
import {containsQuery} from "@/assets/js/helper";
import RadioListWithSearch from "@/components/Form/RadioListWithSearch.vue";

export default {
  name: "ConnectTransporterView",
  props: {
    info: String
  },
  components: {
    RadioListWithSearch,
    RadioList,
    InfoBox,
    TextIconButton
  },
  data() {
    return {
      transporterId: undefined,
      transporterName: undefined,
      search: ''
    };
  },
  computed: {
    ...mapGetters(['transporters', 'calculatedPrice'])
  },
  methods: {
    ...mapActions(['fetchTransporters', 'createNotification', 'calculatePrice', 'continueToCalculatedPriceStep']),
    link(e) {
      if(!this.transporterId) {
        this.createNotification({content: "Please select a transporter", type: `warning`});
        return;
      }
      this.calculatePrice(this.transporterId).then(() => {
        if(this.calculatedPrice) {
          this.continueToCalculatedPriceStep(2);
        }
      });
    },
    transporterRadioListItems() {
      return this.transporters.map(transporter => {
        return { value: transporter.id, label: transporter.name };
      });
    },
    onSelectTransporter(picked) {
      this.transporterId = picked.value;
      this.transporterName = picked.label;
    }
  },
  created() {
    this.fetchTransporters();
  }
};
</script>

<style scoped lang="scss">

.connect-transporter-wrapper {
  height: 100%; /* REQUIRED FOR BUTTONS TO BE AT THE BOTTOM */
  /* CONTAINER HAS TO BE FLEX TOO */
}

.form-and-message-wrapper {

  > .box {
    flex: 1 1 100%;
  }

  .important {
    font-size: var(--h2-text-size);
    padding-bottom: 2rem;
    text-align: center;
  }
}

@media (max-width: 1600px) {
  .form-and-message-wrapper {
    flex-direction: column;
  }
}

</style>
