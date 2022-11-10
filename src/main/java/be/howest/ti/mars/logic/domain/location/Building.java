package be.howest.ti.mars.logic.domain.location;

import java.util.UUID;

public class Building {

    private final String id;
    private final TypeOfLocation typeOfLocation;

    public Building(TypeOfLocation typeOfLocation){
        this.id = UUID.randomUUID().toString();
        this.typeOfLocation = typeOfLocation;
    }

    public Building(String id, TypeOfLocation typeOfLocation){
        this.id = id;
        this.typeOfLocation = typeOfLocation;
    }

}
