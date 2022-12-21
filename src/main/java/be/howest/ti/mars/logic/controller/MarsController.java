package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.List;
import java.util.Map;
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
     * @return the {@link User} object that is created.
     */
    User createUser(String firstname, String lastname, String pricePlan);

    /**
     * Get a set of all users
     * @return Set with all users in {@link BaseUser} format.
     */
    Set<BaseUser> getUsers();

    /**
     * Get a specific user
     * @param userID uuid
     * @return {@link User} object.
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

    /**
     * Create a size object
     * @param size array with doubles
     * @return the {@link Size} object
     */
    Size createSize(Double[] size);

    /**
     * Create coordinates object
     * @param coords array with floats
     * @return the {@link Coordinates} object
     */
    Coordinates createCoordinates(Float[] coords);

    /**
     * Create transporter object
     * @param name name of transporter
     * @param size {@link Size} of transporter
     * @param coordinates {@link Coordinates} of transporter
     * @param typeOfBuilding String of type of building based on enum: {@link be.howest.ti.mars.logic.domain.location.TypeOfLocation}
     * @param ipAddress String with the ip address of the transporter
     * @return uuid of {@link Transporter} object
     */
    String createTransporter(String name, Size size , Coordinates coordinates, String typeOfBuilding, String ipAddress);

    /**
     * Returns all the transporters
     * @return List of {@link Transporter}
     */
    List<Transporter> getTransporters();

    /**
     * Get a specific transporter
     * @param transporterID uuid
     * @return {@link Transporter} object
     */
    Transporter getTransporter(String transporterID);

    /**
     * Update transporter object
     * @param id uuid
     * @param name name of transporter
     * @param size {@link Size} of transporter
     * @param coordinates {@link Coordinates} of transporter
     * @param typeOfBuilding String of type of building based on enum: {@link be.howest.ti.mars.logic.domain.location.TypeOfLocation}
     * @param ipAddress String with the ip address of the transporter
     * @return {@link Transporter} object
     */
    Transporter updateTransporter(String id, String name, Size size , Coordinates coordinates, String typeOfBuilding, String ipAddress);

    /**
     * Delete a transporter
     * @param transporterID uuid
     */
    void deleteTransporter(String transporterID);

    /**
     * Add a building
     * @param typeLocation String of typeLocation based on enum {{@link be.howest.ti.mars.logic.domain.location.TypeOfLocation}}
     * @param coordinates {@link Coordinates}
     */
    void addBuilding(String typeLocation, Coordinates coordinates);

    /**
     * Get the calculated price
     * @param transporterID The id of the transporter
     * @return the price Double
     */
    double calculatePrice(String transporterID);

    /**
     * Get a specific link
     * @param linkID linkID
     * @return {@link Link}
     */
    Link getLink(String linkID);

    /**
     * Init the connection
     * @param transporterID the id of the sender transporter
     * @return Map with follow entries: <linkID: String, price: Double>
     */
    Map<String, String> initConnection(String transporterID);

    /**
     * Set the link
     * @param linkID id of the link
     * @param senderUser {@link User} ID - Sender
     * @param senderTransporterID {@link Transporter} ID - sender
     * @param receiverUserID {@link User} ID - receiver
     * @param receiverTransporterID {@link Transporter} ID - receiver
     * @param itemName {@link be.howest.ti.mars.logic.domain.items.Item} item name
     */
    void setLink(String linkID, String senderUser, String senderTransporterID, String receiverUserID, String receiverTransporterID, String itemName);

    /**
     * Delete a link
     * @param transporterID {@link Transporter} ID - transporter
     * @param linkID {@link Link} ID - link
     */
    void deleteLink(String transporterID, String linkID);

    /**
     * Send a package
     * @param transporterID {@link Transporter} ID - transporter}
     * @param linkID {@link Link} ID - link
     */
    void sendPackage(String transporterID, String linkID);

    /**
     * Get all the links a user has sent today
     * @param userID {@link User} user ID
     * @return int
     */
    int getLinksSentToday(String userID);

    /**
     * Get all the links a user has sent
     * @param userID {@link User} user ID
     * @return int
     */
    int getLinksSent(String userID);

    /**
     * Get all the links a user has received
     * @param userID {@link User} user ID
     * @return int
     */
    int getLinksReceived(String userID);

    Map<Link, Item> getItems(String userID);

    Item getItem(String userID, String itemID);
}
