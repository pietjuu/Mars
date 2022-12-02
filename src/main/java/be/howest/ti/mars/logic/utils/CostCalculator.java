package be.howest.ti.mars.logic.utils;

import java.util.Map;

/**
 * <h1>The Molecule formula</h1>
 * <p>To determinate the price we have a fictional formula , that works as follows:</p>
 * <i>Example: 3 molecules of water (H2O)</i>
 * <p>x = digits (3)</p>
 * <p>y = position of first letter in the alphabet. (8)</p>
 * <p>z = the position of the first digit (2)</p>
 * <p>p = the value of first digit (2)</p>
 *
 * <p></p><sup>x</sup>(&radic (y * p)<sup>z</sup>) / 10<sup>9</sup></p>
 *
 * In this case it would be:
 *
 * <p></p><sup>3</sup>(&radic (8 * 2)<sup>2</sup>) / 10<sup>9</sup></p>
 * <p><i>I feel like a mathematician although i'm very bad in it . Greetz Glenn.</i></p>
 */
public class CostCalculator {

    private CostCalculator(){}

    /**
     * Calculate the total cost
     * @param map Map with String (name of molecule ex. H20) and Integer (amount of molecule)
     * @return double
     */
    public static double calculateTotalCost(Map<String, Integer> map){
        double result = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            result += calculateCostOfMol(entry.getKey()) * entry.getValue();
        }

        return result;
    }

    /**
     * Calculate one molecule of a specific type
     * @param molecule name of molecule ex. H20
     * @return double
     */
    public static double calculateCostOfMol(String molecule){
        int x = molecule.length();
        int y = positionInAlphabet(getFirstLetter(molecule));
        int z = positionFirstDigit(molecule);
        int p = getFirstDigit(molecule);

        return Math.pow(Math.pow((y*p), z), 1f/x) / Math.pow(10, 9);
    }

    private static Character getFirstLetter(String molecule){
        for (int i = 0; i < molecule.length(); i++){
            if (Character.isLetter(molecule.charAt(i))){
                return molecule.charAt(i);
            }
        }
        throw new NumberFormatException("First Letter can't be null!");
    }

    private static Character getFirstDigit(String molecule){
        for (int i = 0; i < molecule.length(); i++){
            if (Character.isDigit(molecule.charAt(i))){
                return molecule.charAt(i);
            }
        }
        throw new NumberFormatException("First digit can't be null!");
    }

    private static int positionInAlphabet(Character ch){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++){
            if (ch.equals(alphabet.charAt(i))){
                return i;
            }
        }
        return 0;
    }

    private static int positionFirstDigit(String molecule){
        for (int i = 0; i < molecule.length(); i++){
            if (Character.isDigit(molecule.charAt(i))){
                return i;
            }
        }
        return 0;
    }
}

