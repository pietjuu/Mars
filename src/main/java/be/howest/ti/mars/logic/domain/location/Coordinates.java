package be.howest.ti.mars.logic.domain.location;

public class Coordinates {

    private final String longitude;
    private final String latitude;

    public Coordinates(String longitude, String latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
