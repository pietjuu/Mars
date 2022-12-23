<template>
  <select :name="name" class="box">
    <option v-for="value in values" :value="value" :selected="value === selected" @click="selected = value">{{ value }}</option>
  </select>
</template>

<script>
export default {
  name: "Dropdown",
  props: {
    name: String,
    values: Array
  },
  data() {
    return {
      selected: this.values[0]
    };
  },
  methods: {
    getSelected() {
      this.$emit('selected', this.selected);
    }
  }
};
</script>
