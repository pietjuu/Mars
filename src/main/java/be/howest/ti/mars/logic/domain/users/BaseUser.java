package be.howest.ti.mars.logic.domain.users;

import java.util.Objects;

public class BaseUser {

    private final String id;
    private final String firstname;
    private final String lastname;

    protected BaseUser(String id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseUser baseUser = (BaseUser) o;
        return id.equals(baseUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
