package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRepository implements MarsRepositories{

    Set<User> users = new HashSet<>();

    public InMemoryRepository(){
        addUser(new User("Thibo", "Verbeerst", PricePlan.BUSINESS));
        addUser(new User("Pieter", "Verheye", PricePlan.PREMIUM));
        addUser(new User("Delia", "Vervaeke", PricePlan.STANDARD));
        addUser(new User("Wiebe", "Desmadryl", PricePlan.STANDARD));
        addUser(new User("Glenn", "Callens", PricePlan.PREMIUM));
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Set<User> getUsers() {
        return users;
    }
}
