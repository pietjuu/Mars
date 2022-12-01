package be.howest.ti.mars.logic.domain.molecule;

import be.howest.ti.mars.logic.utils.CostCalculator;

import java.util.Map;
import java.util.Set;

public class MoleculesSummary {

    private final Map<String, Integer> summary;
    private final Set<Molecule> molecules;

    private final double cost;

    public MoleculesSummary(Map<String, Integer> summary, Set<Molecule> molecules) {
        this.summary = summary;
        this.molecules = molecules;
        this.cost = CostCalculator.calculateTotalCost(this.summary);
    }

    public Map<String, Integer> getSummary() {
        return summary;
    }

    public Set<Molecule> getMolecules() {
        return molecules;
    }

    public double getCost() {
        return cost;
    }
}