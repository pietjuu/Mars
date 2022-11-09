package be.howest.ti.mars.logic.domain.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testCreateUser(){
        User u1 = new User("Glenn", "Callens", PricePlan.STANDARD);

        assertNotNull(u1.getId());
        assertEquals(PricePlan.STANDARD, u1.getPricePlan());
        u1.setPricePlan(PricePlan.PREMIUM);
        assertEquals(PricePlan.PREMIUM, u1.getPricePlan());
    }

    @Test
    void testEqual(){
        User u1 = new User("1", "Glenn", "Callens", PricePlan.STANDARD);
        User u2 = new User("1","Glenn", "Callens", PricePlan.STANDARD);

        assertEquals(u1, u2);
    }

    @Test
    void testToString(){
        User u1 = new User("1", "Glenn", "Callens", PricePlan.STANDARD);

        assertEquals("Glenn Callens", u1.toString());
    }
}