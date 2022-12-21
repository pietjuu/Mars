package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlacklistTest {

    @Test
    void addItem() {
        Blacklist b = new Blacklist();
        Item i = new Item("AK-47");

        b.addItem(i);
        assertEquals(1, b.getItems().size());
    }
}