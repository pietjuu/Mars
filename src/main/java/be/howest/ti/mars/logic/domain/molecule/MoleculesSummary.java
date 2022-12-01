package be.howest.ti.mars.logic.domain.molecule;

import java.util.Map;
import java.util.Set;

public class MoleculesSummary {

    private final Map<String , Integer> summary;
    private final Set<Molecule> molecules;

    public MoleculesSummary(Map<String , Integer> summary, Set<Molecule> molecules){
        this.summary = summary;
        this.molecules = molecules;
    }

    public Map<String, Integer> getSummary() {
        return summary;
    }

    public Set<Molecule> getMolecules() {
        return molecules;
    }
}
