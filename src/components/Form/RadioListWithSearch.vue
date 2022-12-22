<template>
  <form action="#">
    <label :for="idSearchBox">{{ label }} <span>*</span></label>
    <div class="flex-gap-col">
      <input v-model="search" type="text" :id="idSearchBox" :name="idSearchBox" autocomplete="off" :placeholder="placeholderSearchBox"/>
      <RadioList :selected="selected" :name="name" :items="this.filterItems()" :maxHeight="radioListMaxHeight" @input="onSelect"/>
      <p v-if="picked.label" class="selected-value box">Selected: <span>{{ picked.label }}</span></p>
    </div>
  </form>
</template>

<script>
import RadioList from "@/components/Form/RadioList.vue";
import {containsQuery} from "@/assets/js/helper";

export default {
  name: "RadioListWithSearch",
  components: {RadioList},
  props: {
    radioListMaxHeight: {
      type: String,
      required: false
    },
    items: {
      type: Array,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    placeholderSearchBox: {
      type: String,
      required: true
    },
    label: {
      type: String,
      required: true
    },
    selected: {
      type: Object,
      default: undefined,
      required: false
    }
  },
  data() {
    return {
      idSearchBox: `search-radio-list-${this.name}`,
      search: '',
      picked: {
        value: undefined,
        label: undefined
      }
    };
  },
  methods: {
    filterItems() {
      return this.items.filter(item => {
        return containsQuery(item.label.toLowerCase(), this.search.toLowerCase());
      });
    },
    onSelect(e) {
      this.picked.value = e.target.value;
      this.picked.label = e.target.dataset.label;
      this.$emit('select', this.picked);
    }
  },
  created() {
    if(this.selected) {
      this.picked = this.selected;
    }
  }
};
</script>

<style scoped lang="scss">

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

</style>
