import java.util.Comparator;

/**
 * Class waypoint comparator is used to compare waypoints based on different categories
 */
public class WaypointComparator implements Comparator<Waypoint> {
    private int category;
    private boolean ascending;

    /**
     * Parameterized constructor for class WaypointComparator
     * @param c category
     * @param a isAscending
     */
    public WaypointComparator(int c, boolean a){
        category = c;
        ascending = a;
    }
    /**
     * Parameterized constructor for class WaypointComparator
     * @param c category
     */
    public WaypointComparator(int c){
        category = c;
        ascending = true;
    }

    /**
     * Default constructor for class WaypointComparator
     */
    public WaypointComparator(){
        category = 5;
        ascending = true;
    }

    /**
     * Compares one waypoint to another based on a predetermined sorting categary
     * @param o1 first waypoint
     * @param o2 second waypoint
     * @return if ascending: a negative number if o1 is less, 0 if they are equal, and a positive number if o1 is greater
     * if not ascending: a positive number if o1 is less, 0 if they are equal, and a negative number if o1 is greater
     */
    public int compare(Waypoint o1, Waypoint o2) {
        int diff = switch (category){
            case 1 -> o1.getType().compareTo(o2.getType());
            case 2 -> o1.getName().compareTo(o2.getName());
            case 3 -> o1.getState().compareTo(o2.getState());
            case 4 -> (o1.getElevation() - o2.getElevation());
            case 6 -> Double.compare(o1.getToKatahdin(), o2.getToKatahdin());
            default -> Double.compare(o1.getToSpringer(), o2.getToSpringer());

        };
        return (ascending) ? diff : -diff;
    }
}
