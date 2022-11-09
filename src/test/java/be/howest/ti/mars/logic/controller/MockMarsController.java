package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class MockMarsController implements MarsController {


    @Override
    public User createUser(String firstname, String lastname, String pricePlan) {
        try{
            return new User(firstname, lastname, PricePlan.valueOf(pricePlan));
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
}
