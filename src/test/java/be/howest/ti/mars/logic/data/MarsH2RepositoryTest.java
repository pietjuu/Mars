package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsH2RepositoryTest {

    @Test
    void getUsers() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        assertNotNull(marsH2Repository.getUsers());
    }

    @Test
    void addAndDeleteUser() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        int oldValue = marsH2Repository.getUsers().size();
        User user = new User("Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        assertEquals(oldValue+1, marsH2Repository.getUsers().size());

        marsH2Repository.deleteUser(user.getId());

        assertEquals(oldValue, marsH2Repository.getUsers().size());
    }

    @Test
    void getUser() {
        MarsH2Repository marsH2Repository = Repositories.getH2Repo();

        int oldValue = marsH2Repository.getUsers().size();
        User user = new User("Henk", "De Steen", PricePlan.PREMIUM);

        marsH2Repository.addUser(user);

        assertEquals(oldValue+1, marsH2Repository.getUsers().size());

        assertEquals("Henk", marsH2Repository.getUser(user.getId()).getFirstname());
        assertEquals(PricePlan.PREMIUM, marsH2Repository.getUser(user.getId()).getPricePlan());

        marsH2Repository.deleteUser(user.getId());

        assertEquals(oldValue, marsH2Repository.getUsers().size());
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getShippertBlacklist() {
    }

    @Test
    void getUserBlacklist() {
    }

    @Test
    void createUserBlacklist() {
    }

    @Test
    void addItemToUserBlacklist() {
    }

    @Test
    void removeItemToUserBlacklist() {
    }

    @Test
    void isUserBlackListExist() {
    }

    @Test
    void getTransporters() {
    }

    @Test
    void addTransporter() {
    }

    @Test
    void getTransporter() {
    }

    @Test
    void updateTransporter() {
    }

    @Test
    void deleteTransporter() {
    }

    @Test
    void addBuilding() {
    }

    @Test
    void removeBuilding() {
    }

    @Test
    void getBuilding() {
    }

    @Test
    void getBuildingFromCoordinates() {
    }

    @Test
    void getAllLinks() {
    }

    @Test
    void getLink() {
    }

    @Test
    void addLink() {
    }

    @Test
    void deleteLink() {
    }

    @Test
    void getShipNotifications() {
    }

    @Test
    void getSystemNotifications() {
    }

    @Test
    void addShipNotification() {
    }

    @Test
    void addSystemNotification() {
    }
}