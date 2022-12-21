package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.link.LinkStatus;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.molecule.MoleculesSummary;
import be.howest.ti.mars.logic.domain.notifications.ShipNotification;
import be.howest.ti.mars.logic.domain.notifications.SystemNotification;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This is a repo just for when testing.
 */
public class InMemoryRepository implements MarsRepositories{

    Set<User> users = new HashSet<>();

    Map<String, UserBlacklist> usersBlacklists = new HashMap<>();
    Blacklist shippertBlacklist = new Blacklist();
    Map<String, Transporter> transporters = new HashMap<>();

    Map<String, Building> buildings = new HashMap<>();

    Map<String, Link> links = new HashMap<>();

    List<ShipNotification> shipNotifications = new ArrayList<>();

    List<SystemNotification> systemNotifications = new ArrayList<>();

    public InMemoryRepository(){
        LocalDateTime exTime = LocalDateTime.of(2010, 12, 1, 10, 15);
        addUser(new User("T-1", "Thibo", "Verbeerst", PricePlan.BUSINESS));
        addUser(new User("T-2", "Pieter", "Verheye", PricePlan.PREMIUM));
        addUser(new User("T-3", "Delia", "Vervaeke", PricePlan.STANDARD));
        addUser(new User("T-4", "Wiebe", "Desmadryl", PricePlan.STANDARD));
        addUser(new User("T-5", "Glenn", "Callens", PricePlan.PREMIUM));
        shippertBlacklist.addItem(new Item("AK-47",  new MoleculesSummary(new HashMap<>(), new HashSet<>(), new Size(0.3f, 0.8f, 0.2f))));
        shippertBlacklist.addItem(new Item("Coke",new MoleculesSummary(new HashMap<>(), new HashSet<>(), new Size(0.1f, 0.1f, 0.1f))));
        usersBlacklists.get("T-1").addItem(new Item("Apple"));
        transporters.put("TT-1", new Transporter("TT-1", "TT-1", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter1.thibo.cloud/"));
        transporters.put("TT-4", new Transporter("TT-4", "TT-4", new Size(100f, 100f, 100f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter2.thibo.cloud/"));
        transporters.put("TT-5", new Transporter("TT-5", "TT-5", new Size(100f, 100f, 100f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter2.thibo.cloud/"));
        transporters.put("TT-2", new Transporter("TT-2","TT-2", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter5.thibo.cloud/"));
        transporters.put("TT-3", new Transporter("TT-3","TT-3", new Size(10f, 10f, 10f), new Building(TypeOfLocation.RESIDENCE, new Coordinates(1f, 1f)), "https://transporter9.thibo.cloud/"));
        buildings.put("TB-1", new Building("TB-1", TypeOfLocation.RESIDENCE, new Coordinates(50.095983f, 5.357552f)));
        buildings.put("TB-2", new Building("TB-2", TypeOfLocation.PICKUP, new Coordinates(50.175351f, 5.985122f)));
        buildings.put("TB-3", new Building("TB-3", TypeOfLocation.PICKUP, new Coordinates(51.365621f, 3.341908f)));
        links.put("TL-1", new Link("TL-1", this.getUser("T-1"), this.getUser("T-2") ,transporters.get("TT-1"), transporters.get("TT-2"), LinkStatus.SENT, new Item("Apple")));
        links.get("TL-1").setSendTime(exTime);
        links.put("TL-2", new Link("TL-2", this.getUser("T-3"), this.getUser("T-4") ,transporters.get("TT-3"), transporters.get("TT-1"), LinkStatus.SENT, new Item("Beer")));
        links.get("TL-2").setSendTime(exTime);
        shipNotifications.add(new ShipNotification("Alarm Clock from Thibo Verbeerst Sent to Glenn Callens", LocalDateTime.now().plusDays(1), "Status: DELIVERED \\nSent to Farm Dome 230 for Glenn Callens\\n Sent from Sleep Dome 8473 by Thibo Verbeerst", LocalDateTime.now(), this.getUser("T-2")));
        systemNotifications.add(new SystemNotification("Test", LocalDateTime.now().plusDays(1), "This is a test.", LocalDateTime.now()));
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        createUserBlacklist(user.getId());
    }

    @Override
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(String userID) {
        for(User user : users){
            if (user.getId().equals(userID)){
                return user;
            }
        }

        return null;
    }

    @Override
    public void deleteUser(String userID) {
        User deletedUser = null;
        for(User user : users){
            if (user.getId().equals(userID)){
                deletedUser = user;
            }
        }

        users.remove(deletedUser);
    }

    @Override
    public Blacklist getShippertBlacklist() {
        return shippertBlacklist;
    }

    @Override
    public UserBlacklist getUserBlacklist(String userID) {
        return usersBlacklists.get(userID);
    }

    @Override
    public void createUserBlacklist(String userID) {
        usersBlacklists.put(userID, new UserBlacklist(userID));
    }

    @Override
    public void addItemToUserBlacklist(Item item, String userID) {
        if (usersBlacklists.containsKey(userID)) {
            usersBlacklists.get(userID).addItem(item);
        }
    }

    @Override
    public void removeItemToUserBlacklist(Item item, String userID) {
        if (usersBlacklists.containsKey(userID)) {
            usersBlacklists.get(userID).removeItem(item);
        }
    }

    @Override
    public boolean isUserBlackListExist(String userID){
       return !usersBlacklists.containsKey(userID);
    }

    @Override
    public Set<Transporter> getTransporters() {
        return new HashSet<>(transporters.values());
    }

    @Override
    public void addTransporter(Transporter transporter) {
        transporters.put(transporter.getId(), transporter);
    }

    @Override
    public Transporter getTransporter(String transporterID) {
        return transporters.get(transporterID);
    }

    @Override
    public void updateTransporter(Transporter transporter) {
        transporters.replace(transporter.getId(), transporter);
    }

    @Override
    public void deleteTransporter(Transporter transporter) {
        transporters.remove(transporter.getId());
    }

    @Override
    public void addBuilding(Building building) {
        buildings.put(building.getId(), building);
    }

    @Override
    public void removeBuilding(Building building) {
        buildings.remove(building.getId());
    }

    @Override
    public Building getBuilding(String buildingID) {
        return buildings.get(buildingID);
    }

    @Override
    public Building getBuildingFromCoordinates(Coordinates coordinates) {
        for (Map.Entry<String, Building> entry : buildings.entrySet()){
            if (entry.getValue().getCoordinates().equals(coordinates)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Link> getAllLinks() {
        return new HashSet<>(links.values());
    }

    @Override
    public Link getLink(String linkID) {
        return links.get(linkID);
    }

    @Override
    public void addLink(Link link){
        links.put(link.getId(), link);
    }

    @Override
    public void deleteLink(Link link) {
        links.remove(link.getId());
    }

    @Override
    public List<ShipNotification> getShipNotifications(){
        return shipNotifications;
    }

    @Override
    public List<SystemNotification> getSystemNotifications(){
        return systemNotifications;
    }
}
