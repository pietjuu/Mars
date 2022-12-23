<template>
  <article class="box">
    <header>
      <h2>{{ notification.title }}</h2>
      <h3>Sent on: <span>{{ notification.time }}</span></h3>
    </header>
    <main>
      <p v-for="paragraph in notification.message.split('\n')">{{ paragraph }}</p>
    </main>
  </article>
</template>

<script>
export default {
  name: "NotificationItem",
  props: {
    notification: {
      type: Object,
      required: true
    }
  }
};
</script>

<style scoped lang="scss">

header {
  margin-bottom: 0.5rem;

  h2 {
    font-size: var(--medium-text-size);
    margin: 0;
  }
  h3 {
    font-size: 0.8rem;
    margin-bottom: 0.5rem;
  }
}

main {
  p {
    font-size: 0.8rem;
  }
}

</style>
