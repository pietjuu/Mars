package be.howest.ti.mars.logic.domain.molecule;

import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.utils.CostCalculator;

import java.util.Map;
import java.util.Set;

/**
 * Molecules Summary
 */
public class MoleculesSummary {

    private final Map<String, Integer> summary;
    private final Set<Molecule> molecules;
    private final Size size;

    private final double cost;

    /**
     * Constructor
     * @param summary Map with String (name of molecule ex. H20) and Integer (amount of molecule)
     * @param molecules Set of unique molecules.
     */
    public MoleculesSummary(Map<String, Integer> summary, Set<Molecule> molecules, Size size) {
        this.summary = summary;
        this.molecules = molecules;
        this.cost = CostCalculator.calculateTotalCost(this.summary);
        this.size = size;
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

    public Size getSize() {
        return size;
    }
}