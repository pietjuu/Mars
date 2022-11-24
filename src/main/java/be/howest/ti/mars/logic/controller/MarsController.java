package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.List;
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

    /**
     * Get the blacklist of Shippert, which every user is bound to.
     * @return List of string with item names.
     */
    List<String> getShippertBlacklist();

    /**
     * Get the blacklist of a user.
     * @param userID User id.
     * @return List of string with item names.
     */
    List<String> getUserBlacklist(String userID);

    /**
     * Add item to users blacklist
     * @param itemName Item name ex. gun
     * @param userID User ID
     */
    void addItemToUserBlacklist(String itemName, String userID);

    /**
     * Delete item from user blacklist.
     * @param itemName Item name ex. gun
     * @param userID User ID
     */
    void deleteItemToUserBlacklist(String itemName, String userID);

    Size createSize(double length, double width, double depth);

    Coordinates createCoordinates(float longitude, float latitude);

    String addTransporter(String name, Size size , Coordinates coordinates, String typeOfBuilding, String id, String ipAddress);

    List<Transporter> getTransporters();

    Transporter getTransporter(String transporterID);

    Transporter updateTransporter(String name, Size size , Coordinates coordinates, String typeOfBuilding, String id, String ipAddress);

    void deleteTransporter(String transporterID);
}
