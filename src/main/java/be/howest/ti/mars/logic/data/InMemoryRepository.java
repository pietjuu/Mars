package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.*;

/**
 * This is a repo just for when testing.
 */
public class InMemoryRepository implements MarsRepositories{

    Set<User> users = new HashSet<>();

    Map<String, UserBlacklist> usersBlacklists = new HashMap<>();
    Blacklist shippertBlacklist = new Blacklist();

    public InMemoryRepository(){
        addUser(new User("T-1", "Thibo", "Verbeerst", PricePlan.BUSINESS));
        addUser(new User("T-2", "Pieter", "Verheye", PricePlan.PREMIUM));
        addUser(new User("T-3", "Delia", "Vervaeke", PricePlan.STANDARD));
        addUser(new User("T-4", "Wiebe", "Desmadryl", PricePlan.STANDARD));
        addUser(new User("T-5", "Glenn", "Callens", PricePlan.PREMIUM));
        shippertBlacklist.addItem(new Item("AK-47", new Size(0.3f, 0.8f, 0.2f)));
        shippertBlacklist.addItem(new Item("Coke", new Size(0.1f, 0.1f, 0.1f)));
        usersBlacklists.put("T-1", new UserBlacklist("T-1"));
        usersBlacklists.get("T-1").addItem(new Item("Apple"));

        createUserBlacklist("T-1");
        createUserBlacklist("T-2");
        createUserBlacklist("T-3");
        createUserBlacklist("T-4");
        createUserBlacklist("T-5");
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
       return usersBlacklists.containsKey(userID);
    }
}
