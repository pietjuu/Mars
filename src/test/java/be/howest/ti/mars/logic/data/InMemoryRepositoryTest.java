package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
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

        assertEquals(2, repo.getShippertBlacklist().getItems().size());
    }

    @Test
    void getUserBlacklist() {
        InMemoryRepository repo = new InMemoryRepository();

        assertEquals(1, repo.getUserBlacklist("T-1").getItems().size());
    }

    @Test
    void createUserBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();

        repo.createUserBlacklist("T-5");

        assertTrue(repo.isUserBlackListExist("T-5"));
    }

    @Test
    void addItemToUserBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("TE-1", "Glenn", "Callens", PricePlan.STANDARD);
        Item item = new Item("Airpods");

        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(item, user.getId());

        assertEquals("Airpods", repo.getUserBlacklist(user.getId()).getItems().get(0).getName());
    }

    @Test
    void removeItemFroBlacklist(){
        InMemoryRepository repo = new InMemoryRepository();
        User user = new User("TE-1", "Glenn", "Callens", PricePlan.STANDARD);
        Item item = new Item("Airpods");

        repo.createUserBlacklist(user.getId());
        repo.addItemToUserBlacklist(item, user.getId());

        assertEquals("Airpods", repo.getUserBlacklist(user.getId()).getItems().get(0).getName());

        repo.removeItemToUserBlacklist(item, user.getId());

        assertEquals(0, repo.getUserBlacklist(user.getId()).getItems().size());
    }
}