package be.howest.ti.mars.logic.domain.users;

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
}
