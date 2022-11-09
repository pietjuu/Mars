package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.BaseUser;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.Set;

public interface MarsController {
    User createUser(String firstname, String lastname, String pricePlan);

    Set<BaseUser> getUsers();

    User getUser(String userID);
}
