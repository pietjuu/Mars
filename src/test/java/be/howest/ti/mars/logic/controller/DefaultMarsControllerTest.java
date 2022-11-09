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
}
