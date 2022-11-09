package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.data.MarsRepositories;
import be.howest.ti.mars.logic.data.Repositories;
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
        return new HashSet<>(Repositories.getInMemoryRepository().getUsers());
    }
}