package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.NoSuchElementException;

public class MockMarsController implements MarsController {


    @Override
    public User createUser(String firstname, String lastname, String pricePlan) {
        try{
            return new User(firstname, lastname, PricePlan.valueOf(pricePlan));
        } catch (IllegalArgumentException ex){
            throw new NoSuchElementException(String.format("No such element %s", pricePlan));
        }


    }
}
