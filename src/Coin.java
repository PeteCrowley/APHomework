/**
 * Interface Coin defines which methods that each coin's class should implement
 * @version 3/29/22
 * @author 23crowley
 */
public interface Coin {
    /**
     * Gets the value of a coin
     * @return returns .01, .05, ..., .5, 1.0 based on implementing class object's value
     */
    double getValue();
    /**
     * Gets the name of the coin
     * @return "penny", "nickel", ..., "half dollar", "dollar" based on implementing class object's value
     */
    String getName();
}
