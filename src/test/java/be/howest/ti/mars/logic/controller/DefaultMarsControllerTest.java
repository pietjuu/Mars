package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.utils.MockInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultMarsControllerTest {
    @Test
    void testCreateUser(){
        MarsController controller = new DefaultMarsController();

        assertEquals("Bob", controller.createUser("Bob", "Friet", "PREMIUM").getFirstname());

    }

    @Test
    void testCreateUserWithEmptyFields(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.createUser("", "", "BUSINESS"));
        assertThrows(IllegalArgumentException.class, () -> controller.createUser("BOE", "BOE", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.createUser("", "BOE", "BOE"));
    }

    @Test
    void testCreateUserWithWrongPricePlan(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.createUser("Glenn", "Callens", "KOFFIE"));
    }

    @Test
    void testGetUsers(){
        MarsController controller = new DefaultMarsController();

        controller.createUser("Bob", "Friet", "PREMIUM");
        controller.createUser("Stevie", "Wonder", "PREMIUM");

        List<BaseUser> list = new ArrayList<>(controller.getUsers());
        assertTrue(list.size() >= 2);
    }

    @Test
    void testGetUser(){
        MarsController controller = new DefaultMarsController();

        // First add user and determinate the id.
        controller.createUser( "Bob", "Friet", "PREMIUM");
        String id = null;
        for (BaseUser user : controller.getUsers()){
            if (user.getFirstname().equals("Bob")){
                id = user.getId();
            }
        }

        assertEquals("Bob", controller.getUser(id).getFirstname());
    }

    @Test
    void testGetUserWithEmptyArgument(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.getUser(""));
    }

    @Test
    void testGetUserWithFalseID(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.getUser("CANT_EXIST"));
    }

    @Test
    void testDeleteUser(){
        MarsController controller = new DefaultMarsController();

        controller.createUser( "Bob", "Friet", "PREMIUM");
        int oldSize = controller.getUsers().size();
        String id = null;
        for (BaseUser user : controller.getUsers()){
            if (user.getFirstname().equals("Bob")){
                id = user.getId();
            }
        }

        BaseUser oldUser = controller.getUser(id);
        controller.deleteUser(id);
        assertEquals(oldSize - 1, controller.getUsers().size());
        assertFalse(controller.getUsers().contains(oldUser));
    }

    @Test
    void testDeleteUserWithEmptyArgument(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.deleteUser(""));
    }

    @Test
    void testGetShippertBlacklist(){
        MarsController controller = new DefaultMarsController();

        assertEquals(2, controller.getShippertBlacklist().size());
    }

    @Test
    void testGetUserBlackList(){
        MarsController controller = new DefaultMarsController();

        assertEquals(1, controller.getUserBlacklist("T-1").size());
    }

    @Test
    void testGetUserBlacklistWithNonExistingUser(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.getUserBlacklist("notExists"));
    }

    @Test
    void testAddBlacklistItem(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createUser("Glenn", "Callens", "STANDARD").getId();
        controller.addItemToUserBlacklist("Apple", id);

        assertEquals(1, controller.getUserBlacklist(id).size());
    }

    @Test
    void testAddBlackListItemWithNonExistingUser(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.addItemToUserBlacklist("Apple", "NotExist"));
    }

    @Test
    void testAddBlackListItemWithDuplicateItem(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createUser("Glenn", "Callens", "STANDARD").getId();
        controller.addItemToUserBlacklist("Apple", id);

        assertEquals(1, controller.getUserBlacklist(id).size());
        assertThrows(IllegalArgumentException.class, () -> controller.addItemToUserBlacklist("Apple", id));
    }

    @Test
    void testDeleteBlacklistItem(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createUser("Glenn", "Callens", "STANDARD").getId();
        controller.addItemToUserBlacklist("Apple", id);

        assertEquals(1, controller.getUserBlacklist(id).size());

        controller.deleteItemToUserBlacklist("Apple", id);

        assertEquals(0, controller.getUserBlacklist(id).size());
    }

    @Test
    void testDeleteBlackListItemWithNonExistingUser(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.deleteItemToUserBlacklist("Apple", "NotExist"));
    }

    @Test
    void testDeleteBlackListItemWithNonExistingItem(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createUser("Glenn", "Callens", "STANDARD").getId();

        assertThrows(IllegalArgumentException.class, () -> controller.deleteItemToUserBlacklist("Apple", id));
    }

    @Test
    void testCreateSize(){
        MarsController controller = new DefaultMarsController();

        Double[] size = new Double[]{1.2, 1.1, 1.0};
        Size size1 = controller.createSize(size);

        assertEquals(1.1, size1.getLength());
        assertEquals(1.0, size1.getWidth());
        assertEquals(1.2, size1.getHeight());
    }

    @Test
    void testCreateCoordinates(){
        MarsController controller = new DefaultMarsController();

        Float[] fl = new Float[]{1.2f, 1.1f};
        Coordinates coordinates = controller.createCoordinates(fl);

        assertEquals(1.2f, coordinates.getLongitude());
        assertEquals(1.1f, coordinates.getLatitude());
    }

    @Test
    void testCreateTransporter(){
        MarsController controller = new DefaultMarsController();

        assertNotNull(controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips"));
    }

    @Test
    void testCreateTransporterEmptyArgs(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.createTransporter("", null, null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.createTransporter("Kitchen", null, null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", ""));
    }

    @Test
    void testGetTransporters(){
        MarsController controller = new DefaultMarsController();

        int oldSize = controller.getTransporters().size();

        assertNotNull(controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips"));

        assertNotEquals(controller.getTransporters().size(), oldSize);
    }

    @Test
    void testGetTransporter(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips");

        assertEquals(id, controller.getTransporter(id).getId());
    }

    @Test
    void testGetTransporterBlankArgument(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.getTransporter(""));
    }

    @Test
    void testGetTransporterNonExist(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.getTransporter("NOT_EXIST"));
    }

    @Test
    void testUpdateTransporter(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips");

        assertEquals("Kitchen", controller.getTransporter(id).getName());

        controller.updateTransporter(id, "Bob", new Size(1.0,1.0,1.0), new Coordinates(9999.13f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips");

        assertEquals("Bob", controller.getTransporter(id).getName());
    }

    @Test
    void testUpdateTransporterEmptyArgs(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips");

        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter("","", null, null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter(id,"", null, null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter(id,"Kitchen", null, null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter(id,"Kitchen", new Size(1.0,1.0,1.0), null, "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter(id,"Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "", ""));
        assertThrows(IllegalArgumentException.class, () -> controller.updateTransporter(id,"Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", ""));
    }

    @Test
    void testDeleteTransporter(){
        MarsController controller = new DefaultMarsController();

        String id = controller.createTransporter("Kitchen", new Size(1.0,1.0,1.0), new Coordinates(9999.12f, 9999.12f), "RESIDENCE", "https://local.notexist.must.do.this.cause.sonar.even.is.shitting.about.test.ips");

        controller.deleteTransporter(id);
        assertThrows(NoSuchElementException.class, () -> controller.getTransporter(id));
    }

    @Test
    void testDeleteTransporterEmptyArgs(){
        MarsController controller = new DefaultMarsController();

        assertThrows(IllegalArgumentException.class, () -> controller.deleteTransporter(""));
    }

    @Test
    void testDeleteTransporterNonExist(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.deleteTransporter("NON_EXIST"));
    }

    @Test
    void testAddBuilding(){
        MarsController controller = new DefaultMarsController();

        controller.addBuilding("RESIDENCE", new Coordinates(99999.6f, 99999.34f));
    }

    @Test
    void testAddBuildingAlreadyExist(){
        MarsController controller = new DefaultMarsController();

        controller.addBuilding("RESIDENCE", new Coordinates(99999.69f, 99999.69f));

        assertThrows(IllegalArgumentException.class, () -> controller.addBuilding("RESIDENCE", new Coordinates(99999.69f, 99999.69f)));
    }

    @Test
    void testAddBuildingWithNonExistingType(){
        MarsController controller = new DefaultMarsController();

        assertThrows(NoSuchElementException.class, () -> controller.addBuilding("BLABLA",new Coordinates(99999.11f, 99999.69f)));
    }

    @Test
    void testCalculatePrice(){
        MarsController controller = new DefaultMarsController();

        assertTrue(0 < controller.calculatePrice("TT-1"));
    }

    @Test
    void testInitConnection(){
        MarsController controller = new DefaultMarsController();

        Map<String, String> result = controller.initConnection("TT-1");

        assertNotNull(result.get("price"));
        assertNotNull(result.get("linkID"));
    }

    @Test
    void testGetLink(){
        MarsController controller = new DefaultMarsController();

        String id = controller.initConnection("TT-1").get("linkID");

        assertEquals("TT-1", controller.getLink(id).getSender().getId());
    }

    @Test
    void testSetLink(){
        MarsController controller = new DefaultMarsController();

        Item item = new Item("Peer", MockInformation.getMoleculesSummary());

        String id = controller.initConnection("TT-1").get("linkID");
        controller.getLink("TT-1").setItem(item);

        assertEquals("Peer", controller.getLink(id).getItem().getName());
    }

    @Test
    void testDeleteLink(){
        MarsController controller = new DefaultMarsController();

        String id = controller.initConnection("TT-1").get("linkID");
        controller.deleteLink("TT-1", id);

        assertThrows(NoSuchElementException.class, () -> controller.getLink(id));
    }
}
