<template>
  <Load v-if="loading"/>
  <div class="destination-validation flex-gap-col" v-if="!loading">
    <div class="box checks-display">
      <ul class="checks">
        <li v-for="check in this.sendChecks">
          {{ check.check }} <span>{{ check.value ? 'OK' : 'NO' }}</span>
        </li>
      </ul>
      <div class="check-icon flex-center-col">
        <Icon :icon="valid ? 'check_circle' : 'error'"/>
      </div>
    </div>

    <div class="bottom-buttons valid-buttons" v-if="valid">
      <TextIconButton :content="`Next Step`" :icon="`arrow_forward_ios`" :width="`8rem`" :height="`2.3rem`" @click="onNextStep" :flexDirection="`row-reverse`"/>
      <TextIconButton :content="`Previous Step`" :icon="`arrow_back_ios`" :width="`10rem`" :height="`2.3rem`" @click="onPreviousStep"/>
    </div>

    <div class="bottom-buttons invalid-buttons" v-if="!valid">
      <div class="flex-gap-row">
        <TextIconButton :content="`Previous Step`" :icon="`arrow_back_ios`" :width="`10rem`" :height="`2.3rem`" @click="onPreviousStep"/>
        <TextIconButton :content="`Retry Connection`" :icon="`restart_alt`" :width="`12rem`" :height="`2.3rem`" @click="onRetryConnection"/>
      </div>
    </div>

  </div>
</template>

<script>
import TextIconButton from "@/components/Button/TextIconButton.vue";
import {mapActions, mapGetters} from "vuex";
import Icon from "@/components/Icon/Icon.vue";
import Load from "@/components/Load/Load.vue";

export default {
  name: "DestinationValidationView",
  components: {Load, Icon, TextIconButton},
  props: {
    loading: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      valid: undefined
    };
  },
  computed: {
    ...mapGetters(['sendChecks']),
  },
  methods: {
    ...mapActions(['continueToSendItemStep', 'finalizeLink']),
    onRetryConnection(e) {
      this.finalizeLink();
    },
    onPreviousStep(e) {
      this.continueToSendItemStep(3);
    },
    onNextStep(e) {
      this.continueToSendItemStep(5);
    },
    isValid(checks) {
      return !checks.some(check => check.value === false);
    }
  },
  created() {
    if(this.sendChecks){
      this.valid = this.isValid(this.sendChecks);
    }
  },
  watch: {
    sendChecks(nw, old) {
      this.valid = this.isValid(nw);
    }
  }
};
</script>

<style scoped lang="scss">

.destination-validation {
  height: 100%;

  .checks-display {
    height: 80%;
    font-size: var(--medium-text-size);
    position: relative;

    .checks {
      position: absolute;
    }

    span {
      font-weight: bold;
    }

  }
}

.check-icon {
  position: center;
  overflow: hidden;
  height: 100%;
  width: 100%;
  .icon {
    scale: 15;
  }
}

.invalid-buttons {
  flex-direction: row;
}
</style>
