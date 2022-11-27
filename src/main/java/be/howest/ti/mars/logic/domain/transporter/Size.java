package be.howest.ti.mars.logic.domain.transporter;

/**
 * Size class
 */
public class Size {

    private final double height;
    private final double length;
    private final double width;

    /**
     * Constructor
     * @param height double
     * @param length double
     * @param width double
     */
    public Size(double height, double length, double width) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "Height: " + height + " \n" +
                "Width: " + width + " \n" +
                "Length: " + length + " \n";
    }
}
