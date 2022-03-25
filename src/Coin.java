public abstract class Coin {
    public abstract double getValue(); // returns .01, .05, ..., .5, 1.0 based on
    // implementing class object's value
    public abstract String getName(); // returns "penny", "nickel", ...,
    // "half dollar", "dollar"
    public String getPluralName(){
        return getName();
    }
}
