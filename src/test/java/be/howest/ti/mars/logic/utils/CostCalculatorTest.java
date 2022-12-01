package be.howest.ti.mars.logic.utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostCalculatorTest {

    @Test
    void exceptions() {
        assertThrows(NumberFormatException.class, () -> CostCalculator.calculateCostOfMol("20"));
        assertThrows(NumberFormatException.class, () -> CostCalculator.calculateCostOfMol("H"));
    }

}