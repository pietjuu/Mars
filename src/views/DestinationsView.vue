<template>
  <HeaderContent :title="`Destinations`"/>
  <main>
    <div class="map-box box">
      <DestinationMap/>
    </div>
    <div class="location-filter-and-details">
      <div class="location-filter box">

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
  height: calc(100% - 5.1rem);
  display: flex;
  flex-direction: row;
  gap: 1rem;
}

.map-box {
  min-height: 40rem;
  flex: 1 1 75%;
}

.location-filter-and-details {
  flex: 1 1 25%;
}


</style>
