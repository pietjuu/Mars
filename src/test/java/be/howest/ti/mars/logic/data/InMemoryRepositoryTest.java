package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.link.LinkStatus;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    @Test
    void addUser() {
        InMemoryRepository repo = new InMemoryRepository();

        int oldSize = repo.getUsers().size();
        repo.addUser(new User("Glenn", "Callens", PricePlan.STANDARD));
        assertEquals(oldSize+1, repo.getUsers().size());
    }

    @Test
    void getUser() {
        InMemoryRepository repo = new InMemoryRepository();

        repo.addUser(new User("1", "Glenn", "Callens", PricePlan.STANDARD));

        assertEquals("Glenn", repo.getUser("1").getFirstname());
    }

    @Test
    void getUserNull(){
        InMemoryRepository repo = new InMemoryRepository();
        assertNull(repo.getUser("blabla"));
    }

    @Test
    void deleteUser() {
        InMemoryRepository repo = new InMemoryRepository();

        repo.addUser(new User("1", "Glenn", "Callens", PricePlan.STANDARD));

        assertEquals("Glenn", repo.getUser("1").getFirstname());

        int oldSize = repo.getUsers().size();

        repo.deleteUser("1");

        assertEquals(oldSize-1, repo.getUsers().size());
    }

    @Test
    void getShippertBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals(2, repo.getShippertBlacklist().getItems().size());
    }

    @Test
    void getUserBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals(1, repo.getUserBlacklist("T-1").getItems().size());
    }

    @Test
    void createUserBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();

        repo.createUserBlacklist("T-5");

        assertFalse(repo.isUserBlackListExist("T-5"));
    }

    @Test
    void addItemToUserBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("TE-1", "Glenn", "Callens", PricePlan.STANDARD);
        Item item = new Item("Airpods");

        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(item, user.getId());

        assertEquals("Airpods", repo.getUserBlacklist(user.getId()).getItems().get(0).getName());
    }

    @Test
    void removeItemFroBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("TE-1", "Glenn", "Callens", PricePlan.STANDARD);
        Item item = new Item("Airpods");

        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(item, user.getId());

        assertEquals("Airpods", repo.getUserBlacklist(user.getId()).getItems().get(0).getName());

        repo.removeItemToUserBlacklist(item, user.getId());

        assertEquals(0, repo.getUserBlacklist(user.getId()).getItems().size());
    }

    @Test
    void getTransporters(){
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals(5, repo.getTransporters().size());
    }

    @Test
    void getTransporter(){
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals("TT-1", repo.getTransporter("TT-1").getId());
    }

    @Test
    void addTransporter(){
        InMemoryRepository repo = new InMemoryRepository();
        Transporter transport = new Transporter("TT-9", "TT-9", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://local.sonar.is.aan.het.zagen");

        repo.addTransporter(transport);

        assertEquals(6, repo.getTransporters().size());
    }

    @Test
    void updateTransporter(){
        InMemoryRepository repo = new InMemoryRepository();
        Transporter transport = new Transporter("TT-1", "TT-HA", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://local.sonar.is.aan.het.zagen");
        repo.addTransporter(transport);
        transport.setName("BA");
        repo.updateTransporter(transport);
        assertEquals("BA", repo.getTransporter("TT-1").getName());
    }

    @Test
    void deleteTransporter(){
        InMemoryRepository repo = new InMemoryRepository();

        repo.deleteTransporter(repo.getTransporter("TT-1"));

        assertEquals(4, repo.getTransporters().size());
    }

    @Test
    void addAndGetBuilding(){
        InMemoryRepository repo = new InMemoryRepository();
        Building building = new Building("TBT1", TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f));

        repo.addBuilding(building);
        assertNotNull(repo.getBuilding("TBT1"));
    }

    @Test
    void removeBuilding(){
        InMemoryRepository repo = new InMemoryRepository();
        repo.removeBuilding(repo.getBuilding("TB-1"));

        assertNull(repo.getBuilding("TB-1"));
    }

    @Test
    void getBuildingFromCoordinates(){
        InMemoryRepository repo = new InMemoryRepository();
        assertEquals("TB-2", repo.getBuildingFromCoordinates(new Coordinates(50.175351f, 5.985122f)).getId());
    }

    @Test
    void getBuildingFromCoordinatesNull(){
        InMemoryRepository repo = new InMemoryRepository();
        assertNull(repo.getBuildingFromCoordinates(new Coordinates(99999999f, 999f)));
    }

    @Test
    void getAllLinks(){
        InMemoryRepository repo = new InMemoryRepository();
        assertEquals(2, repo.getAllLinks().size());
    }

    @Test
    void getLink(){
        InMemoryRepository repo = new InMemoryRepository();
        assertEquals(LinkStatus.SENT, repo.getLink("TL-1").getLinkStatus());
    }

    @Test
    void addLink(){
        InMemoryRepository repo = new InMemoryRepository();
        int oldSize = repo.getAllLinks().size();

        Link link = new Link(repo.getTransporter("TT-1"));
        repo.addLink(link);

        assertEquals(oldSize+1, repo.getAllLinks().size());
    }

    @Test
    void deleteLink(){
        InMemoryRepository repo = new InMemoryRepository();
        int oldSize = repo.getAllLinks().size();

        Link link = new Link(repo.getTransporter("TT-1"));
        repo.addLink(link);

        assertEquals(oldSize+1, repo.getAllLinks().size());

        repo.deleteLink(link);

        assertEquals(oldSize, repo.getAllLinks().size());
    }
}