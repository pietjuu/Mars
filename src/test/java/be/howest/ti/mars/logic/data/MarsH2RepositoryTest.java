package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.notifications.SystemNotification;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.utils.MockInformation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MarsH2RepositoryTest {

    @Test
    void getUsers() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        assertNotNull(marsH2Repository.getUsers());
    }

    @Test
    void addAndDeleteUser() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        int oldValue = marsH2Repository.getUsers().size();
        User user = new User("Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        assertEquals(oldValue+1, marsH2Repository.getUsers().size());

        marsH2Repository.deleteUser(user.getId());

        assertEquals(oldValue, marsH2Repository.getUsers().size());
    }

    @Test
    void getUser() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        int oldValue = marsH2Repository.getUsers().size();
        User user = new User("Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        assertEquals(oldValue+1, marsH2Repository.getUsers().size());

        assertEquals("Henk", marsH2Repository.getUser(user.getId()).getFirstname());
        assertEquals(PricePlan.PREMIUM, marsH2Repository.getUser(user.getId()).getPricePlan());

        marsH2Repository.deleteUser(user.getId());

        assertEquals(oldValue, marsH2Repository.getUsers().size());
    }
    @Test
    void getShippertBlacklist() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        assertTrue(1 <= marsH2Repository.getShippertBlacklist().getItems().size());
    }

    @Test
    void getUserBlacklist() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Item item = new Item("Thibo");
        User user = new User("Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        marsH2Repository.addItemToUserBlacklist(item, user.getId());
        assertTrue(marsH2Repository.getUserBlacklist(user.getId()).getItems().size() >= 1);
    }

    @Test
    void addItemToUserBlacklist() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Item item = new Item("Thibo");
        User user = new User( "Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        marsH2Repository.addItemToUserBlacklist(item, user.getId());
    }

    @Test
    void removeItemToUserBlacklist() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Item item = new Item("Thibo");
        User user = new User( "Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        marsH2Repository.addItemToUserBlacklist(item, user.getId());
        int oldSize = marsH2Repository.getUserBlacklist(user.getId()).getItems().size();
        marsH2Repository.removeItemToUserBlacklist(item, user.getId());

        assertEquals(oldSize-1, marsH2Repository.getUserBlacklist(user.getId()).getItems().size());
    }

    @Test
    void addTransporter() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter transporter = new Transporter("TT-1", new Size(10f, 10f, 10f), building, "https://transporter1.thibo.cloud/");
        marsH2Repository.addBuilding(building);
        marsH2Repository.addTransporter(transporter);

        assertEquals("TT-1", marsH2Repository.getTransporter(transporter.getId()).getName());
    }

    @Test
    void getTransporters() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        assertTrue(1 <= marsH2Repository.getTransporters().size());
    }

    @Test
    void updateTransporter() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter transporter = new Transporter("TT-1", new Size(10f, 10f, 10f), building, "https://transporter1.thibo.cloud/");
        marsH2Repository.addBuilding(building);
        marsH2Repository.addTransporter(transporter);

        assertEquals("TT-1", marsH2Repository.getTransporter(transporter.getId()).getName());

        transporter.setIp("192.168.0.1");

        marsH2Repository.updateTransporter(transporter);

        assertEquals("192.168.0.1", marsH2Repository.getTransporter(transporter.getId()).getIp());

    }

    @Test
    void deleteTransporter() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        Transporter transporter = new Transporter("TT-1", new Size(10f, 10f, 10f), building, "https://transporter1.thibo.cloud/");
        marsH2Repository.addBuilding(building);
        marsH2Repository.addTransporter(transporter);

        assertEquals("TT-1", marsH2Repository.getTransporter(transporter.getId()).getName());

        int oldSize = marsH2Repository.getTransporters().size();

        marsH2Repository.deleteTransporter(transporter);

        assertEquals(oldSize-1, marsH2Repository.getTransporters().size());


    }

    @Test
    void addBuilding() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        marsH2Repository.addBuilding(building);
    }

    @Test
    void removeBuilding() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        marsH2Repository.removeBuilding(building);
    }

    @Test
    void getBuilding() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        marsH2Repository.addBuilding(building);
        assertEquals(TypeOfLocation.RESIDENCE, marsH2Repository.getBuilding(building.getId()).getTypeOfLocation());
    }

    @Test
    void getBuildingFromCoordinates() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        Building building = new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));
        marsH2Repository.addBuilding(building);
        assertEquals(TypeOfLocation.RESIDENCE, marsH2Repository.getBuildingFromCoordinates(new Coordinates(1f, 1f)).getTypeOfLocation());
    }

    @Test
    void getAllLinks() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        marsH2Repository.getAllLinks();
    }

    @Test
    void getShipNotifications() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        marsH2Repository.getShipNotifications();
    }

    @Test
    void getSystemNotifications() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        marsH2Repository.getSystemNotifications();
    }

    @Test
    void addSystemNotifications(){
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();
        marsH2Repository.addSystemNotification(new SystemNotification("test", 1, "test"));

        assertTrue(0 < marsH2Repository.getSystemNotifications().size());
        marsH2Repository.createUserBlacklist("test");
    }

    @Test
    void addAndSetItem(){
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        Item item = new Item("Gun", MockInformation.getMoleculesSummary());
        item.setSendTime(LocalDateTime.now());
        marsH2Repository.addItem(item);

        assertEquals("Gun", marsH2Repository.getItem(item.getId()).getName());
        assertEquals(item.getMolecules().getMolecules().toString(), marsH2Repository.getItem(item.getId()).getMolecules().getMolecules().toString());
    }
}