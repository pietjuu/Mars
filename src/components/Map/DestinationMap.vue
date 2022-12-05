<template>
  <div id="destination-map">

  </div>
</template>

<script>
import { Map, View, Feature } from 'ol';
import { Tile as TileLayer, Vector as layerVector} from 'ol/layer';
import { OSM, Vector as sourceVector } from 'ol/source';
import { Style, Icon } from 'ol/style';
import { fromLonLat } from 'ol/proj';
import { Point } from 'ol/geom';
import {mapActions, mapGetters} from "vuex";

// Marker Images
const blueMarker = require(`@/assets/media/blue_marker.png`);
const orangeMarker = require(`@/assets/media/orange_marker.png`);
const purpleMarker = require(`@/assets/media/purple_marker.png`);


export default {
  name: "DestinationMap",
  methods: {
    ...mapActions(["fetchTransporters", "setClickedLocation"]),
    createMarkerLayer(vectors) {
      return new layerVector({
        source: vectors
      });
    },
    createMap(target, lon = 4.34878, lat = 50.85045, zoom = 8) {

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
    },
    markerClicked(evt, map) {
      const feature = map.forEachFeatureAtPixel(evt.pixel, function (feature) {
        return feature;
      });
      if (!feature) {
        return;
      }
      this.setClickedLocation({ ...feature.values_ });
    }
  },
  computed: {
    ...mapGetters(["transporters"])
  },
  async mounted() {
    const map = this.createMap("destination-map");
    await this.fetchTransporters();

    const blueMarkerStyle = new Style({
      image: new Icon({
        src: blueMarker,
        anchor: [0.5, 1]
      })
    });
    const orangeMarkerStyle = new Style({
      image: new Icon({
        src: orangeMarker,
        anchor: [0.5, 1]
      })
    });
    const purpleMarkerStyle = new Style({
      image: new Icon({
        src: purpleMarker,
        anchor: [0.5, 1]
      })
    });


    const markers = [];
    this.transporters.forEach(transporter => {
      let style;
      switch (transporter.location.building.typeOfBuilding) {
        case "PICKUP":
          style = orangeMarkerStyle;
          break;
        case "GARBAGE":
          style = purpleMarkerStyle;
          break;
        default:
          style = blueMarkerStyle;
          break;
      }

      const coordinates = transporter["location"]["coordinates"];
      const feature = this.createFeature([coordinates.longitude, coordinates.latitude]);
      feature.setId(transporter.id);
      feature.setProperties(transporter);
      feature.setStyle(style);
      markers.push(feature);

    });

    const markerVectors = new sourceVector({
      features: markers
    });

    const markerLayer = this.createMarkerLayer(markerVectors);
    markerLayer.setStyle(purpleMarkerStyle);

    map.addLayer(markerLayer);

    map.on('click', (e) => this.markerClicked(e, map));
  }
};
</script>

<style scoped lang="scss">
#destination-map {
  @include size(100%);
}
</style>
