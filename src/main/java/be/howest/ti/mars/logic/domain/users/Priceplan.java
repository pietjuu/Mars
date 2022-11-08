package be.howest.ti.mars.logic.domain.users;

public enum Priceplan {

    STANDARD("Standard", 10, 5),
    PREMIUM("Premium", 100, -1),
    BUSINESS("Business", -1, -1);

    private final String name;
    private final int maxItems;
    private final double costPerMol;

    Priceplan(String name, int maxItems, double costPerMol){
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
