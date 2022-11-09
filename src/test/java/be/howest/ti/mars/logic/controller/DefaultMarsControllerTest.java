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

}
