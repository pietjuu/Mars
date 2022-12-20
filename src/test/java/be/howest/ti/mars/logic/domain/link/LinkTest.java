package be.howest.ti.mars.logic.domain.link;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    @Test
    void constructorDefault(){
        Link l1 = new Link(new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"));

        assertEquals(LinkStatus.INITIALIZED, l1.getLinkStatus());
    }

    @Test
    void constructorImport(){
        Link l1 = new Link("TL-1", new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"), new Transporter("TT-2", "TT-2", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"), LinkStatus.SENT, new Item("Apple"));

        assertEquals(LinkStatus.SENT, l1.getLinkStatus());
    }

}