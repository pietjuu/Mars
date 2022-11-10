package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is a repo just for when testing.
 */
public class InMemoryRepository implements MarsRepositories{

    Set<User> users = new HashSet<>();
    Set<Item> shippertBlacklist = new HashSet<>();

    public InMemoryRepository(){
        addUser(new User("Thibo", "Verbeerst", PricePlan.BUSINESS));
        addUser(new User("Pieter", "Verheye", PricePlan.PREMIUM));
        addUser(new User("Delia", "Vervaeke", PricePlan.STANDARD));
        addUser(new User("Wiebe", "Desmadryl", PricePlan.STANDARD));
        addUser(new User("Glenn", "Callens", PricePlan.PREMIUM));
        shippertBlacklist.add(new Item("AK-47", new Size(0.3f, 0.8f, 0.2f)));
        shippertBlacklist.add(new Item("Coke", new Size(0.1f, 0.1f, 0.1f)));
    }

    @Override
    public void addUser(User user) {
        users.add(user);
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
    public List<Item> getShippertBlacklist() {
        return new ArrayList<>(shippertBlacklist);
    }
}
