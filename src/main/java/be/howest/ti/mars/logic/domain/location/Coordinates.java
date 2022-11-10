package be.howest.ti.mars.logic.domain.location;

public class Coordinates {

    private final float longitude;
    private final float latitude;

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
}
