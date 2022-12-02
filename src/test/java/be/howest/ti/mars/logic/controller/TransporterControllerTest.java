package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.exceptions.TransporterException;
import be.howest.ti.mars.web.exceptions.TransporterAPIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransporterControllerTest {

    @Test
    void getTransportLocation(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(4.110492743655409f, 50.89675963769386f)), "https://transporter1.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertNotNull(transporterController.getTransporterLocation(t));
    }

    @Test
    void getTransporterStatus(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertTrue(transporterController.getTransporterStatus(t));
    }

    @Test
    void getTransporterScan(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertNotNull(transporterController.getTransporterScan(t).getMolecules());
    }

    @Test
    void getTransporterScanWithNonExistingTransporter(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter99.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertThrows(TransporterAPIException.class, () -> transporterController.getTransporterScan(t));
    }

    @Test
    void getTransporterScanWithNonReadyTransporter(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter6.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertThrows(TransporterException.class, () -> transporterController.getTransporterScan(t));
    }

    @Test
    void sendItem(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertNotNull(transporterController.sendItemInTransporter(t).getMolecules());
    }

    @Test
    void sendItemWithNonExistingTransporter(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter99.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertThrows(TransporterAPIException.class, () -> transporterController.sendItemInTransporter(t));
    }

    @Test
    void sendItemWithNonReadyTransporter(){
        Transporter t = new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "transporter6.thibo.cloud/");
        TransporterController transporterController = new TransporterController();

        assertThrows(TransporterException.class, () -> transporterController.sendItemInTransporter(t));
    }

}