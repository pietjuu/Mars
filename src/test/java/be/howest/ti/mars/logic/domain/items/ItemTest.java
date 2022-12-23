package be.howest.ti.mars.logic.domain.items;

import be.howest.ti.mars.logic.utils.MockInformation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testConstructorWithoutID(){
        Item i = new Item("Apple");

        assertNotNull(i.getId());
    }

    @Test
    void testConstructorWithID(){
        Item i = new Item("Test", "Apple", MockInformation.getMoleculesSummary(), null);

        assertEquals("Test", i.getId());
    }

    @Test
    void testStatus(){
        Item i = new Item("Apple", MockInformation.getMoleculesSummary());

        assertEquals(ItemStatus.UNDEFINED, i.getStatus());

        i.setStatus(ItemStatus.ERROR);

        assertEquals(ItemStatus.ERROR, i.getStatus());
    }

    @Test
    void testErrorStatus(){
        Item i = new Item("Apple", MockInformation.getMoleculesSummary());

        assertEquals(ItemStatus.UNDEFINED, i.getStatus());

        assertThrows(IllegalStateException.class, () -> i.setStatus(ItemStatus.COMPLETED));
    }

    @Test
    void testSendTime(){
        Item i = new Item("Apple", MockInformation.getMoleculesSummary());

        assertNull(i.getSendTime());

        i.setSendTime(LocalDateTime.of(2053, 12, 31, 11, 15));

        assertEquals(LocalDateTime.of(2053, 12, 31, 11, 15), i .getSendTime());
    }

    @Test
    void testReceivedTime(){
        Item i = new Item("Apple", MockInformation.getMoleculesSummary());

        assertNull(i.getReceivedTime());

        i.setReceivedTime(LocalDateTime.of(2053, 12, 31, 11, 15));

        assertEquals(LocalDateTime.of(2053, 12, 31, 11, 15), i .getReceivedTime());
    }

    @Test
    void testToString(){
        Item i = new Item("1", "Apple", MockInformation.getMoleculesSummary(), null);
        i.setReceivedTime(LocalDateTime.of(2053, 12, 31, 11, 15));
        i.setSendTime(LocalDateTime.of(2053, 12, 31, 11, 15));

        String sizeCheck = "Height: " + 1f + " \n" +
                "Width: " + 1f + " \n" +
                "Length: " + 1f + " \n";

        String check = "Apple" + ": \n" +
                "id: " + "1" + " \n" +
                "size: " + sizeCheck + " \n" +
                "status: " + ItemStatus.UNDEFINED + " \n" +
                "SendTime: " + LocalDateTime.of(2053, 12, 31, 11, 15) + " \n" +
                "ReceivedTime: " + LocalDateTime.of(2053, 12, 31, 11, 15) + " \n";

        assertEquals(check, i.toString());
    }

    @Test
    void testEquals(){
        Item i = new Item("1", "Apple", MockInformation.getMoleculesSummary(), null);
        Item i2 = new Item("1", "Apple", MockInformation.getMoleculesSummary(), null);
        Set<Item> items = new HashSet<>();
        items.add(i);
        items.add(i2);

        assertEquals(1, items.size());
    }

    @Test
    void testNotEqual(){
        Item i = new Item("1", "Apple", MockInformation.getMoleculesSummary(), null);
        Item i2 = new Item("2", "Banana", MockInformation.getMoleculesSummary(), null);
        Set<Item> items = new HashSet<>();
        items.add(i);
        items.add(i2);

        assertNotEquals(i, i2);
        assertEquals(2, items.size());
    }

    @Test
    void testMolecules(){
        Item i = new Item("Apple", MockInformation.getMoleculesSummary());

        assertEquals(2, i.getMolecules().getMolecules().size());
    }
}