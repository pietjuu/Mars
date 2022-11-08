package be.howest.ti.mars.logic.domain.users;

import java.util.UUID;

public class User {

    private final String id;
    private final String firstname;
    private final String lastname;
    private PricePlan priceplan;

    /**
     * User object - Constructor should only be used with DB.
     * @param id id of user
     * @param firstname firstname
     * @param lastname lastname
     * @param pricePlan PricePlan enum
     */
    public User(String id, String firstname, String lastname, PricePlan pricePlan) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.priceplan = pricePlan;
    }

    /**
     * Constructor
     * @param firstname firstname
     * @param lastname lastname
     * @param pricePlan enum Price Plan
     */
    public User(String firstname, String lastname, PricePlan pricePlan) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.priceplan = pricePlan;
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

    public PricePlan getPricePlan() {
        return priceplan;
    }

    public void setPricePlan(PricePlan priceplan) {
        this.priceplan = priceplan;
    }
}
