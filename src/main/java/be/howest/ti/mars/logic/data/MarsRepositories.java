package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
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
     * @return {@link User}
     */
    User getUser(String userID);

    /**
     * Delete a specific user
     * @param userID uuid
     */
    void deleteUser(String userID);

    /**
     * Get the Shippert blacklist
     * @return {@link Blacklist}
     */
    Blacklist getShippertBlacklist();

    /**
     * Get the user blacklist
     * @param userID User ID
     * @return {@link UserBlacklist}
     */
    UserBlacklist getUserBlacklist(String userID);

    /**
     * Create user blacklist
     * @param userID UUID
     */
    void createUserBlacklist(String userID);

    /**
     * Add an item to a user blacklist
     * @param item {@link Item}
     * @param userID UUID
     */
    void addItemToUserBlacklist(Item item, String userID);

    /**
     * Delete an item to a user blacklist
     * @param item {@link Item}
     * @param userID UUID
     */
    void removeItemToUserBlacklist(Item item, String userID);

    /**
     * Does the user have a blacklist?
     * @param userID uuid
     * @return boolean
     */
    boolean isUserBlackListExist(String userID);

    /**
     * Get transporters
     * @return Set of {@link Transporter}
     */
    Set<Transporter> getTransporters();

    /**
     * Add Transporter
     * @param transporter {@link Transporter}
     */
    void addTransporter(Transporter transporter);

    /**
     * Get specific transporter
     * @param transporterID uuid
     * @return {@link Transporter}
     */
    Transporter getTransporter(String transporterID);

    /**
     * Update the transporter
     * @param transporter {@link Transporter}
     */
    void updateTransporter(Transporter transporter);

    /**
     * Delete transporter
     * @param transporter {@link Transporter}
     */
    void deleteTransporter(Transporter transporter);

    /**
     * Add building
     * @param building {@link Building}
     */
    void addBuilding(Building building);

    /**
     * Remove building
     * @param building {@link Building}
     */
    void removeBuilding(Building building);

    /**
     * Get building
     * @param buildingID uuid
     * @return building {@link Building}
     */
    Building getBuilding(String buildingID);

    /**
     * Get building from coordinates
     * @param coordinates {@link Coordinates}
     * @return {@link Building}
     */
    Building getBuildingFromCoordinates(Coordinates coordinates);

    /**
     * Get all links
     * @return Set of {@link Link}
     */
    Set<Link> getAllLinks();

    /**
     * Get a link
     * @param linkID {@link Link} ID
     * @return {@link Link}
     */
    Link getLink(String linkID);

    /**
     * Add a link
     * @param link {@link Link}
     */
    void addLink(Link link);

    /**
     * Delete a link
     * @param link {@link Link}
     */
    void deleteLink(Link link);
}
