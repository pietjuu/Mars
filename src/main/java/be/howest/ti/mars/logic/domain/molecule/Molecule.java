package be.howest.ti.mars.logic.domain.molecule;

import java.util.Objects;

public class Molecule {

    private final int id;
    private final String type;
    private final int xPosition;
    private final int yPosition;

    public Molecule(int id, String type, int xPosition, int yPosition) {
        this.id = id;
        this.type = type;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Molecule molecule = (Molecule) o;
        return id == molecule.id && xPosition == molecule.xPosition && yPosition == molecule.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xPosition, yPosition);
    }
}
