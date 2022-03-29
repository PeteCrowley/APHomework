/**
 * Class Penny defines the getValue and getName methods for a penny which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */

public class Penny implements Coin{
    /**
     * Gets the value of a penny
     * @return the value of a penny
     */
    public double getValue() {
        return 0.01;
    }
    /**
     * Gets the name of a penny
     * @return the name of a penny
     */
    public String getName() {
        return "penny";
    }
}
