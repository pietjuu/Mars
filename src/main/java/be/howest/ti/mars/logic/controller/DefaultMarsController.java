package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.data.MarsRepositories;
import be.howest.ti.mars.logic.data.Repositories;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * DefaultMarsController is the default implementation for the MarsController interface.
 * The controller shouldn't even know that it is used in an API context..
 * This class and all other classes in the logic-package (or future sub-packages)
 * should use 100% plain old Java Objects (POJOs). The use of Json, JsonObject or
 * Strings that contain encoded/json data should be avoided here.
 * Keep libraries and frameworks out of the logic packages as much as possible.
 * Do not be afraid to create your own Java classes if needed.
 */
public class DefaultMarsController implements MarsController {

    public static final String USERID_BLACKLIST_DOESNT_EXIST = "User ID doesn't exist in user blacklists!";
    MarsRepositories repository = Repositories.getInMemoryRepository();

    @Override
    public User createUser(String firstname, String lastname, String subscription) {

        if (StringUtils.isBlank(firstname) || StringUtils.isBlank(lastname) || StringUtils.isBlank(subscription)){
            throw new IllegalArgumentException("An empty argument is not allowed!");
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
        if (!repository.isUserBlackListExist(userID)){
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
        if (!repository.isUserBlackListExist(userID)){
            throw new NoSuchElementException(USERID_BLACKLIST_DOESNT_EXIST);
        }

        Item i = new Item(itemName);

        if (!repository.getUserBlacklist(userID).containsItem(itemName)){
            throw new IllegalArgumentException("Item didn't exist in user blacklist!");
        }

        repository.removeItemToUserBlacklist(i, userID);
    }
}