package be.howest.ti.mars.logic.domain.users;

public enum PricePlan {

    STANDARD("Standard", 10, 5),
    PREMIUM("Premium", 100, -1),
    BUSINESS("Business", -1, -1);

    private final String name;
    private final int maxItems;
    private final double costPerMol;

    /**
     * PricePlan
     * @param name name of plan
     * @param maxItems max items that can be sent / day, -1 = unlimited
     * @param costPerMol cost per molecule. -1 = unlimited.
     */
    PricePlan(String name, int maxItems, double costPerMol){
        this.name = name;
        this.maxItems = maxItems;
        this.costPerMol = costPerMol;
    }

    public String getName() {
        return name;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public double getCostPerMol() {
        return costPerMol;
    }
}
