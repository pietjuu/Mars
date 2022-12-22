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
            <div>
              <label for="search-transporter-name">Select Transporter <span>*</span></label>
              <div class="flex-gap-col">
                <input v-model="search" type="text" id="search-transporter-name" name="search-transporter-name" autocomplete="off" placeholder="Search name of transporter"/>
                <RadioList :name="`transporter-names`" :items="this.getTransporterRadioListItems()" :maxHeight="`15rem`" @input="onSelectTransporter"/>
                <p v-if="transporterName" class="selected-value box">Selected: <span>{{ transporterName }}</span></p>
              </div>
            </div>
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

export default {
  name: "ConnectTransporterView",
  props: {
    info: String
  },
  components: {
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
    getTransporterRadioListItems() {
      const filtered = this.transporters.filter(transporter => {
        return containsQuery(transporter.name.toLowerCase(), this.search.toLowerCase());
      });
      return filtered.map(transporter => {
        return { value: transporter.id, label: transporter.name };
      });
    },
    onSelectTransporter(e) {
      this.transporterId = e.target.value;
      this.transporterName = e.target.dataset.label;
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

form {

  input {
    border-color: var(--color-primary-soft);
    min-height: 1.25rem;

    &:focus {
      outline: none;
      border-color: var(--color-primary);
    }
  }

  .selected-value {
    line-height: 0.5rem;
    background-color: var(--color-secondary-soft);
    color: var(--color-white);
    border: none;
    border-radius: 0.5rem;
  }

}

@media (max-width: 1600px) {
  .form-and-message-wrapper {
    flex-direction: column;
  }
}

</style>
