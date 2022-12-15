package be.howest.ti.mars.logic.domain.molecule;

import be.howest.ti.mars.logic.utils.MockInformation;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MoleculesSummaryTest {

    @Test
    void testGetters(){
        Map<String, Integer> summary = new HashMap<>();
        Set<Molecule> molecules = new HashSet<>();

        summary.put("H20", 900);
        summary.put("O2", 500);

        MoleculesSummary moleculesSummary = new MoleculesSummary(summary, molecules, MockInformation.getSize());

        assertEquals(2, moleculesSummary.getSummary().size());
        assertEquals(0, moleculesSummary.getMolecules().size());
        assertEquals(1f, moleculesSummary.getSize().getHeight());

    }

    @Test
    void testCost(){
        Map<String, Integer> summary = new HashMap<>();
        Set<Molecule> molecules = new HashSet<>();

        summary.put("H20", 900);
        summary.put("O2", 500);

        MoleculesSummary moleculesSummary = new MoleculesSummary(summary, molecules, MockInformation.getSize());

        assertEquals(1.9571325783276617E-5, moleculesSummary.getCost());
    }

}