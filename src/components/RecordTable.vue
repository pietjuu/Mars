<script src="@/assets/js/components/record-table.js"></script>

<template>
  <table>
    <thead>
      <tr>
        <th>Name</th>
        <th>Weight</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="exercise in exercises" :key="exercise.id">
        <td class="exercise-name">{{ exercise.name }}</td>
        <td class="exercise-weight">{{ exercise.weight }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped lang="css">
@import "@/assets/css/components/record-table.css";
</style>
