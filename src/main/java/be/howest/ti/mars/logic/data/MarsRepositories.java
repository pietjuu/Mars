package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;

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

    /**
     * Get the Shippert blacklist
     * @return Blacklist
     */
    Blacklist getShippertBlacklist();

    /**
     * Get the user blacklist
     * @param userID User ID
     * @return UserBlacklist object
     */
    UserBlacklist getUserBlacklist(String userID);

    /**
     * Create user blacklist
     * @param userID UUID
     */
    void createUserBlacklist(String userID);

    /**
     * Add an item to a user blacklist
     * @param item item name
     * @param userID UUID
     */
    void addItemToUserBlacklist(Item item, String userID);

    /**
     * Delete an item to a user blacklist
     * @param item item name
     * @param userID UUID
     */
    void removeItemToUserBlacklist(Item item, String userID);

    /**
     * Does the user have a blacklist?
     * @param userID uuid
     * @return boolean
     */
    boolean isUserBlackListExist(String userID);

    Set<Transporter> getTransporters();

    void addTransporter(Transporter transporter);

    Transporter getTransporter(String transporterID);

    void updateTransporter(Transporter transporter);

    void deleteTransporter(Transporter transporter);

    void addBuilding(Building building);

    void removeBuilding(Building building);

    Building getBuilding(String buildingID);

    boolean isBuildingOnLocation(Coordinates coordinates);
}
