package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserBlacklistTest {

    @Test
    void removeItem() {
        UserBlacklist ub = new UserBlacklist("1");
        Item i = new Item("Apple", new Size(1,1,1));

        ub.addItem(i);

        assertEquals(1, ub.getItems().size());

        ub.removeItem(i);
        assertEquals(0, ub.getItems().size());

    }

    @Test
    void getUserID() {
        UserBlacklist ub = new UserBlacklist("1");

        assertEquals("1", ub.getUserID());
    }
}