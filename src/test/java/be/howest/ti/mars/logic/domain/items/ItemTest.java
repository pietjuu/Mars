package be.howest.ti.mars.logic.domain.items;

import be.howest.ti.mars.logic.domain.transporter.Size;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testConstructorWithoutID(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertNotNull(i.getId());
    }

    @Test
    void testConstructorWithID(){
        Item i = new Item("Test", "Apple", new Size(1,1,1));

        assertEquals("Test", i.getId());
    }

    @Test
    void testStatus(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertEquals(ItemStatus.UNDEFINED, i.getStatus());

        i.setStatus(ItemStatus.ERROR);

        assertEquals(ItemStatus.ERROR, i.getStatus());
    }

    @Test
    void testErrorStatus(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertEquals(ItemStatus.UNDEFINED, i.getStatus());

        assertThrows(IllegalStateException.class, () -> i.setStatus(ItemStatus.COMPLETED));
    }

    @Test
    void testSendTime(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertNull(i.getSendTime());

        i.setSendTime(LocalDateTime.of(2053, 12, 31, 11, 15));

        assertEquals(LocalDateTime.of(2053, 12, 31, 11, 15), i .getSendTime());
    }

    @Test
    void testReceivedTime(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertNull(i.getReceivedTime());

        i.setReceivedTime(LocalDateTime.of(2053, 12, 31, 11, 15));

        assertEquals(LocalDateTime.of(2053, 12, 31, 11, 15), i .getReceivedTime());
    }

    @Test
    void testAtoms(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertEquals(0, i.getAtoms());

        i.setAtoms(10);

        assertEquals(10, i.getAtoms());
    }

    @Test
    void testSize(){
        Item i = new Item("Apple", new Size(1,1,1));

        assertEquals(1, i.getSize().getHeight());
        assertEquals(1, i.getSize().getWidth());
        assertEquals(1, i.getSize().getLength());
    }

    @Test
    void testToString(){
        Item i = new Item("1", "Apple", new Size(1,1,1));
        i.setReceivedTime(LocalDateTime.of(2053, 12, 31, 11, 15));
        i.setSendTime(LocalDateTime.of(2053, 12, 31, 11, 15));
        i.setAtoms(10);

        String sizeCheck = "Height: " + 1f + " \n" +
                "Width: " + 1f + " \n" +
                "Length: " + 1f + " \n";

        String check = "Apple" + ": \n" +
                "id: " + "1" + " \n" +
                "size: " + sizeCheck + " \n" +
                "status: " + ItemStatus.UNDEFINED + " \n" +
                "SendTime: " + LocalDateTime.of(2053, 12, 31, 11, 15) + " \n" +
                "ReceivedTime: " + LocalDateTime.of(2053, 12, 31, 11, 15) + " \n" +
                "Atoms: " + 10;

        assertEquals(check, i.toString());
    }

    @Test
    void testEquals(){
        Item i = new Item("1", "Apple", new Size(1,1,1));
        Item i2 = new Item("1", "Apple", new Size(1,1,1));
        Set<Item> items = new HashSet<>();
        items.add(i);
        items.add(i2);

        assertEquals(1, items.size());
    }
}