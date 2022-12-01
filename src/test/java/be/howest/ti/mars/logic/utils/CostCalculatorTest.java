package be.howest.ti.mars.logic.utils;

import be.howest.ti.mars.logic.domain.molecule.Molecule;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CostCalculatorTest {

    @Test
    void exceptions() {
        assertThrows(NumberFormatException.class, () -> CostCalculator.calculateCostOfMol("20"));
        assertThrows(NumberFormatException.class, () -> CostCalculator.calculateCostOfMol("H"));
    }

}