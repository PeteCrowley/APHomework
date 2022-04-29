/**
 * Class Waypoint is a class to store the data of various locations on the appalachain trail
 * @version 4/27/22
 * @author Pete Crowley
 */

public class Waypoint implements Comparable<Waypoint>{
    private String type, name, state;
    private int elevation;
    private double toSpringer, toKatahdin;

    /**
     * Constructor for Class waypoint
     * @param t type of waypoint
     * @param n name of waypoint
     * @param s state of waypoint
     * @param e elevation of waypoint
     * @param spr distance to Springer from waypoint
     * @param kat distance to Katahdin from waypoing
     */
    public Waypoint(String t, String n, String s, int e, double spr, double kat){
        type = t;
        name = n;
        state = s;
        elevation = e;
        toSpringer = spr;
        toKatahdin = kat;
    }

    /**
     * Accessor method for type
     * @return the type of the waypoint
     */
    public String getType() {
        return type;
    }

    /**
     * Accessor method for name
     * @return the name of the waypoint
     */
    public String getName() {
        return name;
    }
    /**
     * Accessor method for state
     * @return the state of the waypoint
     */
    public String getState() {
        return state;
    }
    /**
     * Accessor method for elevation
     * @return the elevation of the waypoint
     */
    public int getElevation() {
        return elevation;
    }
    /**
     * Accessor method for toSpringer
     * @return the distance to Springer
     */
    public double getToSpringer() {
        return toSpringer;
    }
    /**
     * Accessor method for toKatahdin
     * @return the distance to Katahdin
     */
    public double getToKatahdin() {
        return toKatahdin;
    }

    /**
     * The string representation of a waypoint
     * @return the string representation of a waypoint
     */
    public String toString() {
        return "Waypoint{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", elevation=" + elevation +
                ", toSpringer=" + toSpringer +
                ", toKatahdin=" + toKatahdin +
                '}';
    }

    public int compareTo(Waypoint o) {
        return (name.compareTo(o.name));
    }
}
