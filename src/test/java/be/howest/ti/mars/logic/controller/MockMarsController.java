package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.link.LinkStatus;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.*;

public class MockMarsController implements MarsController {


    @Override
    public User createUser(String firstname, String lastname, String pricePlan) {
        try{
            return new User("testID01", firstname, lastname, PricePlan.valueOf(pricePlan));
        } catch (IllegalArgumentException ex){
            throw new NoSuchElementException(String.format("No such element %s", pricePlan));
        }


    }

    @Override
    public Set<BaseUser> getUsers() {
        Set<User> result = new HashSet<>();
        result.add(new User("Thibo", "Verbeerst", PricePlan.BUSINESS));
        result.add(new User("Glenn", "Callens", PricePlan.BUSINESS));
        return new HashSet<>(result);
    }

    @Override
    public User getUser(String userID) {
        return new User(userID, "Glenn", "Callens", PricePlan.BUSINESS);
    }

    @Override
    public void deleteUser(String userID) {
    }

    @Override
    public List<String> getShippertBlacklist() {
        Blacklist blacklist = new Blacklist();
        blacklist.addItem(new Item("Gun"));

        return Collections.singletonList(blacklist.getItems().get(0).getName());
    }

    @Override
    public List<String> getUserBlacklist(String userID) {
        UserBlacklist userBlacklist = new UserBlacklist(userID);
        userBlacklist.addItem(new Item("Bananas"));

        return Collections.singletonList(userBlacklist.getItems().get(0).getName());
    }

    @Override
    public void addItemToUserBlacklist(String itemName, String userID) {
        UserBlacklist userBlacklist = new UserBlacklist(userID);
        userBlacklist.addItem(new Item(itemName));
    }

    @Override
    public void deleteItemToUserBlacklist(String itemName, String userID) {
        UserBlacklist userBlacklist = new UserBlacklist(userID);
        Item i = new Item(itemName);
        userBlacklist.addItem(i);
        userBlacklist.removeItem(i);
    }

    @Override
    public Size createSize(Double[] size) {
        return new Size(size[0], size[1], size[2]);
    }

    @Override
    public Coordinates createCoordinates(Float[] coords) {
        return new Coordinates(coords[0], coords[1]);
    }

    @Override
    public String createTransporter(String name, Size size, Coordinates coordinates, String typeOfBuilding, String ipAddress) {
        return new Transporter("TTT-1", name, size, new Building(TypeOfLocation.valueOf(typeOfBuilding), coordinates), ipAddress).getId();
    }

    @Override
    public List<Transporter> getTransporters() {
        return List.of(new Transporter("TTT-1", "Kitchen", new Size(1,1,1), new Building(TypeOfLocation.valueOf("RESIDENCE"), new Coordinates(1f, 1f)), "192.168.0.1"));
    }

    @Override
    public Transporter getTransporter(String transporterID) {
        return new Transporter(transporterID, "Kitchen", new Size(1,1,1), new Building(TypeOfLocation.valueOf("RESIDENCE"), new Coordinates(1f, 1f)), "192.168.0.1");
    }

    @Override
    public Transporter updateTransporter(String id, String name, Size size, Coordinates coordinates, String typeOfBuilding, String ipAddress) {
        return new Transporter(id, name, size, new Building(TypeOfLocation.valueOf(typeOfBuilding), coordinates), ipAddress);
    }

    @Override
    public void deleteTransporter(String transporterID) {

    }

    @Override
    public void addBuilding(String typeLocation, Coordinates coordinates) {

    }

    @Override
    public double calculatePrice(String transporterID) {
        return 1;
    }

    @Override
    public Link getLink(String linkID) {
        return new Link(linkID, null, null, null, null, LinkStatus.ERROR, null);
    }

    @Override
    public Map<String, String> initConnection(String transporterID) {
        Map<String, String> result = new HashMap<>();
        result.put("linkID", "IDK-1");
        result.put("price", "151");

        return result;
    }

    @Override
    public void setLink(String linkID, String senderUser, String senderTransporterID, String receiverUserID, String receiverTransporterID, String itemName) {

    }

    @Override
    public void deleteLink(String transporterID, String linkID) {

    }

    @Override
    public void sendPackage(String transporterID, String linkID) {

    }

    @Override
    public List<Link> getLinksSent(String userID) {
        return null;
    }
}
