<template>
  <div id="destination-map">

  </div>
</template>

<script>
import { Map, View, Feature } from 'ol';
import { Tile as TileLayer, Vector } from 'ol/layer';
import { OSM } from 'ol/source';
import { Style, Icon } from 'ol/style';
import { fromLonLat } from 'ol/proj';
import { Point } from 'ol/geom';

export default {
  name: "DestinationMap",
  methods: {
    createMarkerLayer(vectors) {
      return new Vector({
        source: vectors,
        style: new Style({
          image: new Icon({
            src: "assets/images/marker.png",
            anchor: [0.5, 1]
          })
        })
      });
    },

    createMap(target, lon = 4.34878, lat = 50.85045, zoom = 10) {
      return new Map({
        target: target,
        layers: [
          new TileLayer({
            source: new OSM()
          })
        ],
        view: new View({
          center: fromLonLat([lon, lat]),
          zoom: zoom
        })
      });
    },

    createFeature(coordinate, type = 'marker') {
      return new Feature({
        type: type,
        geometry: new Point(fromLonLat(coordinate))
      });
    }
  },
  mounted() {
    let map = this.createMap("destination-map");

  }
}
</script>

<style scoped lang="scss">
#destination-map {
  width: 100%;
  height: 100%;
}
</style>