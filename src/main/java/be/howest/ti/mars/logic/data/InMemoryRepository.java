package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.users.User;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRepository implements MarsRepositories{

    Set<User> users = new HashSet<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
