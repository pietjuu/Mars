package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistTest {

    @Test
    void addItem() {
        Blacklist b = new Blacklist();
        Item i = new Item("AK-47", new Size(1,1,1));

        b.addItem(i);
        assertEquals(1, b.getItems().size());
    }
}