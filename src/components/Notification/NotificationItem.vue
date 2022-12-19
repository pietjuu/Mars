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
    notification: Object
  }
};
</script>

<style scoped lang="scss">

  header {
    margin-bottom: 0.5rem;

    h2 {
      margin: 0;
    }
  }

</style>
