<template>
  <load v-if="loading"/>
  <div class="connect-transporter-wrapper flex-gap-col" v-if="!loading">

      <InfoBox :text="info"/>

      <div class="form-and-message-wrapper flex-gap-row">

        <div class="important box flex-center-col">
          <img src="@/assets/media/transporter.png" alt="">
          <p>Place item in the Transporter and close the door!</p>
        </div>

        <div class="box">
          <h2>Connect  Your Transporter</h2>
          <RadioListWithSearch
              :label="`Select Transporter`"
              :placeholderSearchBox="`Search name of transporter`"
              :name="`transporter-names`"
              :items="this.transporterRadioListItems()"
              :radioListMaxHeight="`15rem`"
              @select="onSelectTransporter"/>
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
import RadioListWithSearch from "@/components/Form/RadioListWithSearch.vue";
import Load from "@/components/Load/Load.vue";

export default {
  name: "ConnectTransporterView",
  props: {
    info: {
      type: String,
      required: false
    },
    loading: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  components: {
    Load,
    RadioListWithSearch,
    InfoBox,
    TextIconButton
  },
  data() {
    return {
      transporter: {
        id: undefined,
        name: undefined
      }
    };
  },
  computed: {
    ...mapGetters(['transporters'])
  },
  methods: {
    ...mapActions(['createNotification']),
    link(e) {
      if(!this.transporter.id) {
        this.createNotification({content: "Please select a transporter", type: `warning`});
        return;
      }
      this.$emit('link', this.transporter);
    },
    transporterRadioListItems() {
      return this.transporters.map(transporter => {
        return { value: transporter.id, label: transporter.name };
      });
    },
    onSelectTransporter(picked) {
      this.transporter.id = picked.value;
      this.transporter.name = picked.label;
    }
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

@media (max-width: 1024px) {
  .form-and-message-wrapper {
    flex-direction: column;
  }
}

</style>
