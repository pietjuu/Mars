package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.List;
import java.util.Set;

public interface MarsRepositories {

    /**
     * Add user into database
     * @param user user object
     */
    void addUser(User user);

    /**
     * Returns a set of all users.
     * @return Set of all users
     */
    Set<User> getUsers();

    /**
     * Get a specific user
     * @param userID uuid
     * @return User object
     */
    User getUser(String userID);

    /**
     * Delete a specific user
     * @param userID uuid
     */
    void deleteUser(String userID);

    List<Item> getShippertBlacklist();

    List<Item> getUserBlacklist(String userID);

    UserBlacklist createUserBlacklist(String userID);

    void addItemToUserBlacklist(Item item, String userID);

    void removeItemToUserBlacklist(Item item, String userID);
}
