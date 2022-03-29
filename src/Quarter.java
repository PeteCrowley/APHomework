/**
 * Class Quarter defines the getValue and getName methods for a quarter which implements the Coin interface
 * @version 3/29/22
 * @author 23crowley
 */
public class Quarter implements Coin{
    /**
     * Gets the value of a quarter
     * @return the value of a quarter
     */
    public double getValue() {
        return 0.25;
    }
    /**
     * Gets the name of a quarter
     * @return the name of a quarter
     */
    public String getName() {
        return "quarter";
    }
}
