/**
 * Class Dime defines the getValue and getName methods for a dime which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */
public class Dime implements Coin{
    /**
     * Gets the value of a dime
     * @return the value of a dime
     */
    public double getValue() {
        return 0.1;
    }
    /**
     * Gets the name of a dime
     * @return the name of a dime
     */
    public String getName() {
        return "dime";
    }
}
