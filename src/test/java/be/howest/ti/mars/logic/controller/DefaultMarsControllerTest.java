package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.BaseUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
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
}
