package be.howest.ti.mars.logic.domain.users;

import java.util.Objects;
import java.util.UUID;

public class User extends BaseUser{

    private PricePlan priceplan;

    /**
     * User object - Constructor should only be used with DB.
     * @param id id of user
     * @param firstname firstname
     * @param lastname lastname
     * @param pricePlan PricePlan enum
     */
    public User(String id, String firstname, String lastname, PricePlan pricePlan) {
        super(id, firstname, lastname);
        this.priceplan = pricePlan;
    }

    /**
     * Constructor
     * @param firstname firstname
     * @param lastname lastname
     * @param pricePlan enum Price Plan
     */
    public User(String firstname, String lastname, PricePlan pricePlan) {
        super(UUID.randomUUID().toString(), firstname, lastname);
        this.priceplan = pricePlan;
    }

    public PricePlan getPricePlan() {
        return priceplan;
    }

    public void setPricePlan(PricePlan priceplan) {
        this.priceplan = priceplan;
    }

    @Override
    public String toString() {
        return this.getFirstname() + " " + this.getLastname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
