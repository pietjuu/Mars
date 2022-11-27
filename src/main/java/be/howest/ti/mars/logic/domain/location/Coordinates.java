package be.howest.ti.mars.logic.domain.location;

import java.util.Objects;

/**
 * Coordinates class
 */
public class Coordinates {

    private final float longitude;
    private final float latitude;

    /**
     * Constructor
     * @param longitude float
     * @param latitude float
     */
    public Coordinates(float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Longitude: " + this.longitude + ", Latitude: " + this.latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.longitude, longitude) == 0 && Float.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
