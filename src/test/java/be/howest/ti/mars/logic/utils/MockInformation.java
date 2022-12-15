package be.howest.ti.mars.logic.utils;

import be.howest.ti.mars.logic.domain.molecule.Molecule;
import be.howest.ti.mars.logic.domain.molecule.MoleculesSummary;
import be.howest.ti.mars.logic.domain.transporter.Size;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MockInformation {

    public static Map<String, Integer> getSummary(){
        Map<String, Integer> map = new HashMap<>();
        map.put("H20", 5000);
        map.put("O2", 1000);

        return map;
    }

    public static Set<Molecule> getMolecules(){
        Set<Molecule> molecules = new HashSet<>();
        Molecule molecule = new Molecule(1, "H20", 118, 119);
        Molecule molecule2 = new Molecule(2, "O2", 18, 11);
        molecules.add(molecule);
        molecules.add(molecule2);

        return molecules;
    }

    public static Size getSize(){
        return new Size(1,1,1);
    }

    public static MoleculesSummary getMoleculesSummary(){
        return new MoleculesSummary(getSummary(), getMolecules(), getSize());
    }

}
