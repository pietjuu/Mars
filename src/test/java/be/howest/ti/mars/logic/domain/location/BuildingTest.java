package be.howest.ti.mars.logic.domain.location;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    @Test
    void testBuildingWithoutId(){
        Building building = new Building(TypeOfLocation.PICKUP, new Coordinates(48.36769f,  2.96351f));

        assertNotNull(building.getId());
        assertEquals(TypeOfLocation.PICKUP, building.getTypeOfLocation());
        assertEquals(48.36769f, building.getCoordinates().getLongitude());
        assertEquals(2.96351f, building.getCoordinates().getLatitude());
    }

    @Test
    void testBuildingWithId(){
        Building building = new Building("aa", TypeOfLocation.RESIDENCE, new Coordinates(48.36769f,  2.96351f));

        assertEquals("aa", building.getId());
        assertEquals(TypeOfLocation.RESIDENCE, building.getTypeOfLocation());
        assertEquals(48.36769f, building.getCoordinates().getLongitude());
        assertEquals(2.96351f, building.getCoordinates().getLatitude());
    }

    @Test
    void testBuildingEquals(){
        Building building1 = new Building("aa", TypeOfLocation.RESIDENCE, new Coordinates(48.36769f,  2.96351f));
        Building building2 = new Building("aa", TypeOfLocation.RESIDENCE, new Coordinates(48.36769f,  2.96351f));
        Set<Building> buildings = new HashSet<>();
        buildings.add(building1);
        buildings.add(building2);

        assertEquals(1, buildings.size());
    }

    @Test
    void testCoordsToString(){
        Building building = new Building("aa", TypeOfLocation.RESIDENCE, new Coordinates(1f,  2f));

        assertEquals("Longitude: " + 1f + ", Latitude: " + 2f, building.getCoordinates().toString());
    }

    @Test
    void testBuildingsNotEqual(){
        Building building1 = new Building("aa", TypeOfLocation.RESIDENCE, new Coordinates(48.36769f,  2.96351f));
        Building building2 = new Building("aaa", TypeOfLocation.RESIDENCE, new Coordinates(47.36769f,  2.96351f));
        Set<Building> buildings = new HashSet<>();
        buildings.add(building1);
        buildings.add(building2);

        assertNotEquals(building1, building2);
        assertEquals(2 , buildings.size());
    }

}