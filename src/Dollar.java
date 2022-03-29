/**
 * Class Dollar defines the getValue and getName methods for a dollar which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */
public class Dollar implements Coin {
    /**
     * Gets the value of a dollar
     * @return the value of a dollar
     */
    public double getValue() {
        return 1;
    }
    /**
     * Gets the name of a dollar
     * @return the name of a dollar
     */
    public String getName() {
        return "dollar";
    }
}
