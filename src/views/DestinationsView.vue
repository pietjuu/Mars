<template>
  <HeaderContent :title="`Destinations`"/>
  <main>
    <div class="map-box box">
      <DestinationMap/>
    </div>
    <div class="map-information flex-space-between-col">
      <div class="legend box">

      </div>
      <div class="location-details box">

      </div>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import DestinationMap from "@/components/Map/DestinationMap";

export default {
  name: "DestinationsView",
  components: {
    HeaderContent,
    DestinationMap
  }
};
</script>

<style scoped>

main {
  /* 5rem is height from content header */
  height: calc(100% - 5.1rem); /* IMPORTANT FOR SCROLL ON OVERFLOW!!! */
  min-height: 40rem; /* IMPORTANT FOR SCROLL ON OVERFLOW!!! */
  display: flex;
  flex-direction: row;
  gap: 1rem;
}

.map-box {
  flex: 1 1 75%;
}

.map-information {
  flex: 1 1 25%;
  gap: 1rem;
}

.legend {
  height: 100%;
}

.location-details {
  height: 100%;
}

</style>
