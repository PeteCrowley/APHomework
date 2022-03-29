/**
 * Class Nickel defines the getValue and getName methods for a nickel which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */
public class Nickel implements Coin{
    /**
     * Gets the value of a nickel
     * @return the value of a nickel
     */
    public double getValue() {
        return 0.05;
    }
    /**
     * Gets the name of a nickel
     * @return the name of a nickel
     */
    public String getName() {
        return "nickel";
    }
}
