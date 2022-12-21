package be.howest.ti.mars.logic.domain.location;

import java.util.Objects;
import java.util.UUID;

/**
 * Building class
 */
public class Building {

    private final String id;
    private final TypeOfLocation typeOfLocation;
    private final Coordinates coordinates;

    /**
     * Constructor
     * @param typeOfLocation {@link TypeOfLocation} enum
     * @param coordinates {@link Coordinates}
     */
    public Building(TypeOfLocation typeOfLocation, Coordinates coordinates){
        this.id = UUID.randomUUID().toString();
        this.typeOfLocation = typeOfLocation;
        this.coordinates = coordinates;
    }

    /**
     * Constructor - this should only be used when importing
     * @param id uuid
     * @param typeOfLocation {@link TypeOfLocation} enum
     * @param coordinates {@link Coordinates}
     */
    public Building(String id, TypeOfLocation typeOfLocation , Coordinates coordinates){
        this.id = id;
        this.typeOfLocation = typeOfLocation;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public TypeOfLocation getTypeOfLocation() {
        return typeOfLocation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return id.equals(building.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Building: " + id + "\n" +
                "typeOfLocation" + typeOfLocation + "\n" +
                "coordinates: " + coordinates;
    }
}