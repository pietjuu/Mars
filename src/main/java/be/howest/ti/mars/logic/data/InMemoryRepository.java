package be.howest.ti.mars.logic.data;

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
    Set<Item> shippertBlacklist = new HashSet<>();
    List<UserBlacklist> blackListUser = new ArrayList<>();

    public InMemoryRepository(){
        addUser(new User("T-1", "Thibo", "Verbeerst", PricePlan.BUSINESS));
        addUser(new User("T-2", "Pieter", "Verheye", PricePlan.PREMIUM));
        addUser(new User("T-3", "Delia", "Vervaeke", PricePlan.STANDARD));
        addUser(new User("T-4", "Wiebe", "Desmadryl", PricePlan.STANDARD));
        addUser(new User("T-5", "Glenn", "Callens", PricePlan.PREMIUM));
        shippertBlacklist.add(new Item("AK-47", new Size(0.3f, 0.8f, 0.2f)));
        shippertBlacklist.add(new Item("Coke", new Size(0.1f, 0.1f, 0.1f)));
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
    public List<Item> getShippertBlacklist() {
        return new ArrayList<>(shippertBlacklist);
    }

    @Override
    public List<Item> getUserBlacklist(String userID) {
        return Objects.requireNonNull(getObjUserBlackList(userID)).getItems();
    }

    @Override
    public void createUserBlacklist(String userID) {
        blackListUser.add(new UserBlacklist(userID));
    }

    @Override
    public void addItemToUserBlacklist(Item item, String userID) {
        int index = getIndexUserBlackList(userID);

        blackListUser.get(index).addItem(item);
    }

    @Override
    public void removeItemToUserBlacklist(Item item, String userID) {
        int index = getIndexUserBlackList(userID);

        blackListUser.get(index).removeItem(item);
    }

    //TODO write tests
    private UserBlacklist getObjUserBlackList(String userID){
        for (UserBlacklist userBlacklist : blackListUser){
            if (userBlacklist.getUserID().equals(userID)){
                return userBlacklist;
            }
        }
        return null;
    }

    @Override
    public int getIndexUserBlackList(String userID){
        int i = 0;
        for (UserBlacklist userBlacklist : blackListUser){
            if (userBlacklist.getUserID().equals(userID)){
                return i;
            }
            i += 1;
        }
        return -1;
    }
}
