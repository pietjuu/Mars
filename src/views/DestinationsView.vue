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
        <ul>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`badge`"/>
              <p class="flex-center-vertical">ID: </p>
            </div>
            <span class="location-id location-value flex-center-vertical">T-1</span>
          </li>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`description`"/>
              <p class="flex-center-vertical">Name: </p>
            </div>
            <span class="location-name location-value flex-center-vertical">Farm 200 D2</span>
          </li>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`category`"/>
              <p class="flex-center-vertical">Type: </p>
            </div>
            <span class="location-type location-value flex-center-vertical">Garbage Point</span>
          </li>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`square_foot`"/>
              <p class="flex-center-vertical">Size: </p>
            </div>
            <span class="location-size location-value flex-center-vertical">
              <span class="location-size-length">40cm</span>x<span class="location-size-width">50cm</span>x<span class="location-size-depth">50cm</span>
            </span>
          </li>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`my_location`"/>
              <p class="flex-center-vertical">Longitude: </p>
            </div>
            <span class="location-longitude location-value flex-center-vertical">090DFJDD</span>
          </li>
          <li>
            <div class="icon-text-wrapper">
              <Icon :icon="`my_location`"/>
              <p class="flex-center-vertical">Latitude: </p>
            </div>
            <span class="location-latitude location-value flex-center-vertical">090DFJDD</span>
          </li>
        </ul>
      </div>
    </div>
  </main>
</template>

<script>
import HeaderContent from "@/components/Header/HeaderContent";
import DestinationMap from "@/components/Map/DestinationMap";
import IconAndText from "@/components/Item/IconAndText";
import Icon from "@/components/Icon/Icon";

export default {
  name: "DestinationsView",
  components: {
    IconAndText,
    HeaderContent,
    DestinationMap,
    Icon
  }
};
</script>

<style scoped lang="scss">

main {
  /* 5rem is height from content header */
  height: calc(100% - 5.1rem); /* IMPORTANT FOR SCROLL ON OVERFLOW!!! */
  min-height: 40rem; /* IMPORTANT FOR SCROLL ON OVERFLOW!!! */
  min-width: 40rem; /* IMPORTANT FOR SCROLL ON OVERFLOW!!! */
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

  ul {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
  }

  li {
    display: flex;
    gap: 0.25rem;
  }

}

.location-value {
  font-weight: bold;
}

.icon-text-wrapper {
  display: flex;
  justify-content: start;
  gap: 0.5rem;
}

.location-size {
  display: flex;
  gap: 0.25rem;
}

</style>
