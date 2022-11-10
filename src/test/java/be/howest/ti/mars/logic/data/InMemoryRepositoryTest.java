package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    @Test
    void addUser() {
        InMemoryRepository repo = new InMemoryRepository();

        int oldSize = repo.getUsers().size();
        repo.addUser(new User("Glenn", "Callens", PricePlan.STANDARD));
        assertEquals(oldSize+1, repo.getUsers().size());
    }

    @Test
    void getUser() {
        InMemoryRepository repo = new InMemoryRepository();

        repo.addUser(new User("1", "Glenn", "Callens", PricePlan.STANDARD));

        assertEquals("Glenn", repo.getUser("1").getFirstname());
    }

    @Test
    void getUserNull(){
        InMemoryRepository repo = new InMemoryRepository();
        assertNull(repo.getUser("blabla"));
    }

    @Test
    void deleteUser() {
        InMemoryRepository repo = new InMemoryRepository();

        repo.addUser(new User("1", "Glenn", "Callens", PricePlan.STANDARD));

        assertEquals("Glenn", repo.getUser("1").getFirstname());

        int oldSize = repo.getUsers().size();

        repo.deleteUser("1");

        assertEquals(oldSize-1, repo.getUsers().size());
    }

    @Test
    void getShippertBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals(2, repo.getShippertBlacklist().size());
    }

    @Test
    void getUserBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("1", "Glenn", "Callens", PricePlan.STANDARD);
        Item i = new Item("Apple", new Size(1,1,1));
        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(i, user.getId());
        assertEquals(1, repo.getUserBlacklist(user.getId()).size());
    }

    @Test
    void removeItemToUserBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("1", "Glenn", "Callens", PricePlan.STANDARD);
        Item i = new Item("Apple", new Size(1,1,1));
        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(i, user.getId());
        assertEquals(1, repo.getUserBlacklist(user.getId()).size());
        repo.removeItemToUserBlacklist(i, user.getId());
        assertEquals(0, repo.getUserBlacklist(user.getId()).size());
    }
}