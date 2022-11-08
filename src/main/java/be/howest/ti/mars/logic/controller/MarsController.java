package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.users.User;

public interface MarsController {
    User createUser(String firstname, String lastname, String pricePlan);
}
