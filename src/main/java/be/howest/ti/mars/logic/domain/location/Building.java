package be.howest.ti.mars.logic.domain.location;

import java.util.UUID;

public class Building {

    private final String id;
    private final TypeOfLocation typeOfLocation;
    private final Coordinates coordinates;

    public Building(TypeOfLocation typeOfLocation, Coordinates coordinates){
        this.id = UUID.randomUUID().toString();
        this.typeOfLocation = typeOfLocation;
        this.coordinates = coordinates;
    }

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
}
