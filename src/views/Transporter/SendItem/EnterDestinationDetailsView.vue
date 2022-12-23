<template>
  <div class="destination-details-wrapper flex-gap-col">
    <div class="flex-gap-row">
       <div class="box receivers-container">
        <RadioListWithSearch
            :key="radioListKey"
            :label="`Select Receiver`"
            :placeholderSearchBox="`Search name of user`"
            :name="`user-names`"
            :items="this.userRadioListItems()"
            :selected="this.convertToRadioData(this.selectedReceiver)"
            :radioListMaxHeight="`20rem`"
            @select="onSelectUser"/>
      </div>
      <div class="box destinations-container">
        <RadioListWithSearch
            :key="radioListKey"
            :label="`Select Destination`"
            :placeholderSearchBox="`Search name of transporter`"
            :name="`transporter-names`"
            :items="this.transporterRadioListItems()"
            :selected="this.convertToRadioData(this.selectedDestination)"
            :radioListMaxHeight="`20rem`"
            @select="onSelectTransporter"/>
      </div>
    </div>
    <div class="bottom-buttons">
      <TextIconButton :content="`Connect`" :icon="`link`" :width="`10rem`" :height="`2.3rem`" @click="connect"/>
      <TextIconButton :content="`Previous Step`" :icon="`arrow_back_ios`" :width="`10rem`" :height="`2.3rem`" @click="onPreviousStep"/>
    </div>
  </div>
</template>

<script>
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions, mapGetters} from "vuex";
import RadioListWithSearch from "@/components/Form/RadioListWithSearch.vue";

export default {
  name: "EnterDestinationDetailsView",
  components: {RadioListWithSearch, TextIconButton},
  data() {
    return {
      selectedDestination: {
        id: undefined,
        name: undefined
      },
      selectedReceiver: {
        id: undefined,
        name: undefined
      },
      radioListKey: 0
    };
  },
  computed: {
    ...mapGetters(['users', 'transporters', 'origin', 'user', 'destination', 'receiver'])
  },
  methods: {
    ...mapActions(['continueToSendItemStep', 'fetchUsers', 'fetchTransporters', 'createNotification', 'saveReceiver', 'saveDestination', 'finalizeLink']),
    connect(e) {
      if(!this.destination && !this.receiver) {
        this.createNotification({content: "Please select a receiver and destination", type: `warning`});
        return;
      }
      if(!this.destination) {
        this.createNotification({content: "Please select a destination", type: `warning`});
        return;
      }
      if(!this.receiver) {
        this.createNotification({content: "Please select a receiver", type: `warning`});
        return;
      }
      this.finalizeLink();
    },
    onPreviousStep(e) {
      this.continueToSendItemStep(2);
    },
    transporterRadioListItems() {
      return this.transporters.filter(transporter => transporter.id !== this.origin.id).map(transporter => {
        return { value: transporter.id, label: transporter.name };
      });
    },
    onSelectTransporter(picked) {
      this.selectedDestination.id = picked.value;
      this.selectedDestination.name = picked.label;
      this.saveDestination(this.selectedDestination);
    },
    userRadioListItems() {
      return this.users.filter(user => user.id !== this.user.id).map(user => {
        return { value: user.id, label: `${user.firstname} ${user.lastname}`};
      });
    },
    onSelectUser(picked) {
      this.selectedReceiver.id = picked.value;
      this.selectedReceiver.name = picked.label;
      this.saveReceiver(this.selectedReceiver);
    },
    convertToRadioData(o) {
      return { value: o.id, label: o.name };
    },
    rerenderRadioLists() {
      this.radioListKey += 1;
    }
  },
  async created() {
    await this.fetchUsers();
    await this.fetchTransporters();

    if(this.destination) {
      this.selectedDestination = this.destination;
    }
    if(this.receiver) {
      this.selectedReceiver = this.receiver;
    }

    // recreate radio lists to update selected value with value from vuex
    this.rerenderRadioLists();

  }
};
</script>

<style scoped lang="scss">

.destination-details-wrapper {
  height: 100%;
}

.destinations-container {
  flex: 1 1 100%;
}

.receivers-container {
  flex: 1 1 100%;
}

</style>
