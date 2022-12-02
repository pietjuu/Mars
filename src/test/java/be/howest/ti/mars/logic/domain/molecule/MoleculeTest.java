package be.howest.ti.mars.logic.domain.molecule;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MoleculeTest {

    @Test
    void testGetters(){
        Molecule molecule1 = new Molecule(1, "H20", 5,4);
        assertEquals(1, molecule1.getId());
        assertEquals("H20", molecule1.getType());
        assertEquals(5, molecule1.getxPosition());
        assertEquals(4, molecule1.getyPosition());
    }

    @Test
    void testEqual(){
        Molecule molecule1 = new Molecule(1, "H20", 5,4);
        Molecule molecule2 = new Molecule(1, "H20", 5,4);

        Set<Molecule> molecules = new HashSet<>();
        molecules.add(molecule1);
        molecules.add(molecule2);

        assertEquals(1, molecules.size());
    }
}