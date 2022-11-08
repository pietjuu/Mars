package be.howest.ti.mars.logic.domain.users;

public class User {

    private String id;
    private String firstname;
    private String lastname;
    private Priceplan priceplan;

    public User(String id, String firstname, String lastname, Priceplan priceplan) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.priceplan = priceplan;
    }

    public User(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.priceplan = Priceplan.STANDARD;
    }
}
