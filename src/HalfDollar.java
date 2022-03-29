/**
 * Class HalfDollar defines the getValue and getName methods for a half dollar which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */
public class HalfDollar implements Coin{
    /**
     * Gets the value of a half dollar
     * @return the value of a half dollar
     */
    public double getValue() {
        return 0.5;
    }
    /**
     * Gets the name of a half dollar
     * @return the name of a half dollar
     */
    public String getName() {
        return "half dollar";
    }
}
