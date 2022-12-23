<template>
  <div class="typewriter">
    <h1>{{ content }} <span></span></h1>
  </div>
</template>

<script>

// Source: https://css-tricks.com/snippets/css/typewriter-effect/

export default {
  name: "TypeWriter",
  data() {
    return {
      content: ""
    };
  },
  props: {
    values: Array
  },
  methods: {
    typeWriter(text, i, fnCallback) {
      if (i < (text.length)) {
        this.content = text.substring(0, i + 1);

        setTimeout(() => this.typeWriter(text, i + 1, fnCallback), 100);
      }
      else {
        setTimeout(fnCallback, 1000);
      }
    },
    startTextAnimation(i) {
      if (typeof this.values[i] == 'undefined') {
        setTimeout(() => this.startTextAnimation(0), 5000);
      }
      else if (i < this.values[i].length) {
        this.typeWriter(this.values[i], 0, () => this.startTextAnimation(i + 1));
      }
    }
  },
  created() {
    this.startTextAnimation(0);
  }
};
</script>

<style scoped lang="scss">

.typewriter {
  width: fit-content;
}

h1 {
  font-size: 5em;
  color: var(--color-secondary);
  text-transform: uppercase;
}

span {
  border-right: 0.05em solid;
  animation: caret 1s steps(1) infinite;
}

@keyframes caret {
  50% {
    border-color: transparent;
  }
}

</style>
