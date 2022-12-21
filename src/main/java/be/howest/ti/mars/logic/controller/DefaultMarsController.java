package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.data.MarsRepositories;
import be.howest.ti.mars.logic.data.Repositories;
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
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

/**
 * DefaultMarsController is the default implementation for the MarsController interface.
 * The controller shouldn't even know that it is used in an API context.
 * This class and all other classes in the logic-package (or future sub-packages)
 * should use 100% plain old Java Objects (POJOs). The use of Json, JsonObject or
 * Strings that contain encoded/json data should be avoided here.
 * Keep libraries and frameworks out of the logic packages as much as possible.
 * Do not be afraid to create your own Java classes if needed.
 */
public class DefaultMarsController implements MarsController {

    public static final String USERID_BLACKLIST_DOESNT_EXIST = "User ID doesn't exist in user blacklists!";
    public static final String AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED = "An empty argument is not allowed!";
    MarsRepositories repository = Repositories.getInMemoryRepository();

    @Override
    public User createUser(String firstname, String lastname, String subscription) {

        if (StringUtils.isBlank(firstname) || StringUtils.isBlank(lastname) || StringUtils.isBlank(subscription)){
            throw new IllegalArgumentException(AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED);
        }

        try {

            PricePlan pricePlan = PricePlan.valueOf(subscription);
            User user = new User(firstname, lastname, pricePlan);
            repository.addUser(user);

            return user;

        } catch (IllegalArgumentException e){
            throw new NoSuchElementException(String.format("No such element %s", subscription));
        }
    }

    @Override
    public Set<BaseUser> getUsers() {
        return new HashSet<>(repository.getUsers());
    }

    @Override
    public User getUser(String userID) {
        if (StringUtils.isBlank(userID)){
            throw new IllegalArgumentException("User ID is empty!");
        } else {
            User user = repository.getUser(userID);
            if (user == null){
                throw new NoSuchElementException(String.format("There is no user with id %s", userID));
            }

            return user;
        }
    }

    @Override
    public void deleteUser(String userID) {
        if (StringUtils.isBlank(userID)){
            throw new IllegalArgumentException("User ID is empty!");
        } else {
            repository.deleteUser(userID);
        }
    }

    @Override
    public List<String> getShippertBlacklist() {
        List<String> result = new ArrayList<>();
        for (Item i : repository.getShippertBlacklist().getItems()){
            result.add(i.getName());
        }

        return result;
    }

    @Override
    public List<String> getUserBlacklist(String userID) {
       if (repository.isUserBlackListExist(userID)){
           throw new NoSuchElementException(USERID_BLACKLIST_DOESNT_EXIST);
       }

       List<String> result = new ArrayList<>();
       for (Item i : repository.getUserBlacklist(userID).getItems()){
           result.add(i.getName());
       }

       return result;
    }

    @Override
    public void addItemToUserBlacklist(String itemName, String userID) {
        if (repository.isUserBlackListExist(userID)){
            throw new NoSuchElementException(USERID_BLACKLIST_DOESNT_EXIST);
        }

        Item i = new Item(itemName);

        if (repository.getUserBlacklist(userID).containsItem(itemName)){
            throw new IllegalArgumentException("Item already added in user blacklist!");
        }

        repository.addItemToUserBlacklist(i, userID);
    }

    @Override
    public void deleteItemToUserBlacklist(String itemName, String userID) {
        if (repository.isUserBlackListExist(userID)){
            throw new NoSuchElementException(USERID_BLACKLIST_DOESNT_EXIST);
        }

        Item i = new Item(itemName);

        if (!repository.getUserBlacklist(userID).containsItem(itemName)){
            throw new IllegalArgumentException("Item didn't exist in user blacklist!");
        }

        repository.removeItemToUserBlacklist(i, userID);
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

        if (StringUtils.isBlank(name) || size == null || coordinates == null || StringUtils.isBlank(typeOfBuilding) || StringUtils.isBlank(ipAddress)){
            throw new IllegalArgumentException(AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED);
        }

        if (repository.getBuildingFromCoordinates(coordinates) == null){
            addBuilding(typeOfBuilding, coordinates);
        }

        Building building = repository.getBuildingFromCoordinates(coordinates);
        Transporter transporter = new Transporter(name, size, building, ipAddress);
        repository.addTransporter(transporter);
        return transporter.getId();
    }

    @Override
    public List<Transporter> getTransporters() {
        return new ArrayList<>(repository.getTransporters());
    }

    @Override
    public Transporter getTransporter(String transporterID) {

        if (StringUtils.isBlank(transporterID)){
            throw new IllegalArgumentException(AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED);
        }

        if (repository.getTransporter(transporterID) == null){
            throw new NoSuchElementException("Transporter doesn't exists!");
        }

        return repository.getTransporter(transporterID);
    }

    @Override
    public Transporter updateTransporter(String id, String name, Size size, Coordinates coordinates, String typeOfBuilding, String ipAddress) {

        if (StringUtils.isBlank(id) || StringUtils.isBlank(name) || size == null || coordinates == null || StringUtils.isBlank(typeOfBuilding) || StringUtils.isBlank(ipAddress)){
            throw new IllegalArgumentException(AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED);
        }

        Transporter transporter = getTransporter(id);

        transporter.setName(name);
        transporter.setSize(size);

        if (repository.getBuildingFromCoordinates(coordinates) == null){
            addBuilding(typeOfBuilding, coordinates);
        }

        Building building = repository.getBuildingFromCoordinates(coordinates);

        transporter.setBuilding(building);
        transporter.setIp(ipAddress);

        repository.updateTransporter(transporter);
        return transporter;
    }

    @Override
    public void deleteTransporter(String transporterID) {

        if (StringUtils.isBlank(transporterID)){
            throw new IllegalArgumentException(AN_EMPTY_ARGUMENT_IS_NOT_ALLOWED);
        }

        if (repository.getTransporter(transporterID) == null){
            throw new NoSuchElementException("Transporter doesn't exists!");
        }

        Transporter transporter = getTransporter(transporterID);
        repository.deleteTransporter(transporter);
    }

    @Override
    public void addBuilding(String typeLocation, Coordinates coordinates) {
        if (repository.getBuildingFromCoordinates(coordinates) != null){
            throw new IllegalArgumentException("You tried to add a building on a location where there is already a building!");
        }
        try {
            TypeOfLocation typeOfLocation = TypeOfLocation.valueOf(typeLocation);
            repository.addBuilding(new Building(typeOfLocation, coordinates));
        } catch (IllegalArgumentException e){
            throw new NoSuchElementException(String.format("No such element %s", typeLocation));
        }
    }

    @Override
    public double calculatePrice(String transporterID) {
        Transporter transporter = this.getTransporter(transporterID);

        TransporterController transporterController = new TransporterController();
        return transporterController.getTransporterScan(transporter).getCost();
    }

    @Override
    public Link getLink(String linkID){
        Link link = repository.getLink(linkID);

        if (link == null){
            throw new NoSuchElementException("LinkID doesn't exists!");
        }

        return link;
    }

    @Override
    public Map<String, String> initConnection(String transporterID) {
        Link link = new Link(this.getTransporter(transporterID));

        repository.addLink(link);

        Map<String, String> result = new HashMap<>();
        result.put("linkID", link.getId());
        result.put("price", String.valueOf(this.calculatePrice(transporterID)));

        return result;
    }

    @Override
    public void setLink(String linkID, String senderUser, String senderTransporterID, String receiverUserID, String receiverTransporterID, String itemName) {
        Link link = this.getLink(linkID);
        User sendUser = this.getUser(senderUser);
        User receiverUser = this.getUser(receiverUserID);
        Transporter sendTransporter = this.getTransporter(senderTransporterID);
        Transporter receiverTransporter = this.getTransporter(receiverTransporterID);

        link.connectLink(sendUser, sendTransporter, receiverUser, receiverTransporter, itemName, this.getLinksSentToday(senderUser));
    }

    @Override
    public void deleteLink(String transporterID, String linkID) {
        Link link = this.getLink(linkID);
        Transporter transporter =  this.getTransporter(transporterID);

        if (!link.getSender().equals(transporter)){
            throw new NoSuchElementException("Can't find a link on that transporter!");
        }

        repository.deleteLink(link);
    }

    @Override
    public void sendPackage(String transporterID, String linkID) {
        Link link = this.getLink(linkID);
        Transporter transporter = this.getTransporter(transporterID);

        if (!link.getSender().equals(transporter)){
            throw new NoSuchElementException("Can't find a link on that transporter!");
        }

        link.sendLink();
    }

    @Override
    public int getLinksSentToday(String userID) {
        User user = this.getUser(userID);

        List<Link> result = new ArrayList<>();
        Set<Link> links = repository.getAllLinks();

        for(Link link : links){
            if (link.getSenderUser() != null && link.getSenderUser().equals(user) && link.getLinkStatus() == LinkStatus.SENT && !Objects.isNull(link.getItem().getSendTime()) && LocalDate.from(link.getItem().getSendTime()).equals(LocalDate.now())){
                result.add(link);
            }
        }

        return result.size();
    }

    @Override
    public int getLinksSent(String userID){
        User user = this.getUser(userID);

        List<Link> result = new ArrayList<>();
        Set<Link> links = repository.getAllLinks();

        for(Link link : links){
            if (link.getSenderUser() != null && link.getSenderUser().equals(user) && link.getLinkStatus() == LinkStatus.SENT){
                result.add(link);
            }
        }

        return result.size();
    }

    @Override
    public int getLinksReceived(String userID){
        User user = this.getUser(userID);

        List<Link> result = new ArrayList<>();
        Set<Link> links = repository.getAllLinks();

        for(Link link : links){
            if (link.getReceiverUser() != null && link.getReceiverUser().equals(user) && link.getLinkStatus() == LinkStatus.SENT){
                result.add(link);
            }
        }

        return result.size();
    }

    @Override
    public List<Link> getItems(String userID) {
        User user = this.getUser(userID);
        List<Link> result = new ArrayList<>();
        for (Link link : repository.getAllLinks()){
            if (checkIfEmptyUser(link) && (link.getSenderUser().equals(user) || link.getReceiverUser().equals(user)) && link.getLinkStatus().equals(LinkStatus.SENT)){
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Link getItem(String userID, String itemID) {
        User user = this.getUser(userID);

        for (Link link : repository.getAllLinks()){
            if (checkIfEmptyUser(link) && (link.getSenderUser().equals(user) || link.getReceiverUser().equals(user)) && link.getItem().getId().equals(itemID) && link.getLinkStatus().equals(LinkStatus.SENT)){
                return link;
            }
        }

        throw new NoSuchElementException("Can't find that item!");
    }

    /**
     * Check if the users inside a link are empty
     * @param link {@link Link}
     * @return boolean
     */
    private boolean checkIfEmptyUser(Link link){
        return !Objects.isNull(link.getSenderUser()) && !Objects.isNull(link.getReceiverUser());
    }
}