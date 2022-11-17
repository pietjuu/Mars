package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.User;
import java.util.Set;

/**
 * Interface for all controllers
 * DefaultMarsController and MockMarsController
 */
public interface MarsController {

    /**
     * Create a user
     * @param firstname firstname
     * @param lastname lastname
     * @param pricePlan Price plan enum
     * @return the User object that is created.
     */
    User createUser(String firstname, String lastname, String pricePlan);

    /**
     * Get a set of all users
     * @return Set with all users in BaseUser format.
     */
    Set<BaseUser> getUsers();

    /**
     * Get a specific user
     * @param userID uuid
     * @return User object.
     */
    User getUser(String userID);

    /**
     * Delete a user
     * @param userID uuid
     */
    void deleteUser(String userID);

    Blacklist getShippertBlacklist();

    UserBlacklist getUserBlacklist(String userID);

    void addItemToUserBlacklist(String itemName, String userID);

    void deleteItemToUserBlacklist(String itemName, String userID);

}
