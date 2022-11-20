<template>
  <HeaderContent :title="`Destinations`"/>
  <main>
    <div class="map-box box">
      <DestinationMap/>
    </div>
    <div class="map-information flex-space-between-col">
      <div class="legend box">
        <h2>Legend</h2>
        <ul>
          <li><IconAndText :icon="`location_on`" :title="`Transporter`" style="color: var(--color-secondary-soft)"/></li>
          <li><IconAndText :icon="`location_on`" :title="`Pick-Up Point`" style="color: var(--color-orange)"/></li>
          <li><IconAndText :icon="`location_on`" :title="`Garbage Point`"  style="color: var(--color-purple)"/></li>
        </ul>
      </div>
      <div class="location-details box">
        <h2>Location Details</h2>
      </div>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import DestinationMap from "@/components/Map/DestinationMap";
import IconAndText from "@/components/Item/IconAndText";

export default {
  name: "DestinationsView",
  components: {
    IconAndText,
    HeaderContent,
    DestinationMap
  }
};
</script>

<style scoped lang="scss">

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

  h2 {
    text-transform: uppercase;
  }

}

.legend {
  flex: 1 1 30%;
}

.location-details {
  flex: 1 1 70%;
}

</style>
