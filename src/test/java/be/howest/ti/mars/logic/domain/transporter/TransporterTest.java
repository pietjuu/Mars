package be.howest.ti.mars.logic.domain.transporter;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransporterTest {

    @Test
    void constructorOne(){
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter t = new Transporter("Kitchen", new Size(5, 5,5), building, "192.168.0.1");

        assertEquals("Kitchen", t.getName());
        assertEquals(5, t.getSize().getLength());
        assertEquals(TypeOfLocation.RESIDENCE, t.getBuilding().getTypeOfLocation());
        assertEquals("192.168.0.1", t.getIp());
    }

    @Test
    void constructorTwo(){
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter t = new Transporter("TE-1", "Kitchen", new Size(5, 5,5), building, "192.168.0.1");

        assertEquals("Kitchen", t.getName());
        assertEquals(5, t.getSize().getLength());
        assertEquals(TypeOfLocation.RESIDENCE, t.getBuilding().getTypeOfLocation());
        assertEquals("192.168.0.1", t.getIp());
        assertEquals("TE-1", t.getId());
    }

    @Test
    void setters(){
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter t = new Transporter("TE-1", "Kitchen", new Size(5, 5,5), building, "192.168.0.1");

        assertEquals("Kitchen", t.getName());
        assertEquals(5, t.getSize().getLength());
        assertEquals(TypeOfLocation.RESIDENCE, t.getBuilding().getTypeOfLocation());
        assertEquals("192.168.0.1", t.getIp());
        assertEquals("TE-1", t.getId());

        Building newBuilding = new Building(TypeOfLocation.PICKUP, new Coordinates(1f, 1f));

        t.setBuilding(newBuilding);
        t.setIp("10.10.0.1");
        t.setName("Basement");
        t.setSize(new Size(10,10,10));

        assertEquals("Basement", t.getName());
        assertEquals(10, t.getSize().getLength());
        assertEquals(TypeOfLocation.PICKUP, t.getBuilding().getTypeOfLocation());
        assertEquals("10.10.0.1", t.getIp());
    }

    @Test
    void itemFits(){
        Item item = new Item("TV", new Size(25, 100, 1));
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter t = new Transporter("TE-1", "Kitchen", new Size(5, 5,5), building, "192.168.0.1");

        assertFalse(t.itemFits(item));
    }

    @Test
    void transporterEquals(){
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter t = new Transporter("TE-1", "Kitchen", new Size(5, 5,5), building, "192.168.0.1");
        Transporter t2 = new Transporter("TE-1", "Kitchen", new Size(5, 5,5), building, "192.168.0.1");
        Set<Transporter> transporters = new HashSet<>();
        transporters.add(t);
        transporters.add(t2);

        assertEquals(1, transporters.size());
        assertEquals(t, t2);

    }

    @Test
    void transportToString(){
        String coordinates = "Longitude: " + 2f + ", Latitude: " + 2f;
        String size = "Height: " + 5f + " \n" +
                "Width: " + 5f + " \n" +
                "Length: " + 5f + " \n";
        String building = "Building: " + "TE-3" + "\n" +
                "typeOfLocation" + "RESIDENCE" + "\n" +
                "coordinates: " + coordinates;
        String res =  "Transporter " + "Basement" + "\n" +
                "ID: " + "TE-1" + "\n" +
                "Size: " + size + "\n" +
                "Building " + building + "\n" +
                "IP: " + "192.168.0.1";

        Building b = new Building("TE-3", TypeOfLocation.RESIDENCE, new Coordinates(2f, 2f));
        Transporter t = new Transporter("TE-1", "Basement", new Size(5, 5, 5), b, "192.168.0.1");

        assertEquals(res, t.toString());
    }
}