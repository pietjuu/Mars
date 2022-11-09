package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.BaseUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultMarsControllerTest {
    @Test
    void testCreateUser(){
        MockMarsController controller = new MockMarsController();

        assertEquals("Glenn", controller.createUser("Glenn", "Callens", "PREMIUM").getFirstname());

    }

    @Test
    void testGetUsers(){
        MockMarsController controller = new MockMarsController();

        assertEquals(2, controller.getUsers().size());
        List<BaseUser> list = new ArrayList<>(controller.getUsers());
        assertEquals("Thibo", list.get(0).getFirstname());
        assertEquals("Glenn", list.get(1).getFirstname());
    }

}
