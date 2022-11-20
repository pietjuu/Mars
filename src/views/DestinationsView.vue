<template>
  <div class="destinations">
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
  </div>
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
  display: flex;
  flex-direction: row;
  gap: 1rem;
}

.map-box {
  flex: 1 1 75%;
  min-height: 40rem;
}

.location-filter-and-details {
  flex: 1 1 25%;
}


</style>
