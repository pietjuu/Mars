<template>
  <div class="load-wrapper">
    <div class="typewriter">
      <h1>{{ content }} <span></span></h1>
    </div>
  </div>
</template>

<script>

// Source: https://css-tricks.com/snippets/css/typewriter-effect/

export default {
  name: "TypeWriterLoad",
  data() {
    return {
      text: "Loading Shippert...",
      content: ""
    }
  },
  methods: {
    typeWriter(text, i) {
      if (i < (text.length)) {
        this.content = text.substring(0, i+1);

        setTimeout(() => {
          this.typeWriter(text, i + 1)
        }, 100);
      }
    }
  },
  created() {
    this.typeWriter(this.text, 0);
  }
}
</script>

<style scoped lang="scss">

.load-wrapper {
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}

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
