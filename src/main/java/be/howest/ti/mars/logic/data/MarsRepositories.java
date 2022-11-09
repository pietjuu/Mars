package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.users.User;

import java.util.Set;

public interface MarsRepositories {

    void addUser(User user);

    Set<User> getUsers();
}
