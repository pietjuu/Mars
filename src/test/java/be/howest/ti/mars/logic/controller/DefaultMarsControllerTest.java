package be.howest.ti.mars.logic.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultMarsControllerTest {
    @Test
    void testCreateUser(){
        MockMarsController controller = new MockMarsController();

        assertEquals("Glenn", controller.createUser("Glenn", "Callens", "PREMIUM").getFirstname());

    }

}
