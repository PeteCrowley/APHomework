import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Trail Database reads in waypoint data about the appalachain trail from a file, stores it, and sorts it
 * according to the user's preference
 * @version 4/27/22
 * @author Pete Crowley
 */

public class TrailDatabase {
    private ArrayList<Waypoint> database;
    private final String input_file;
    private WaypointComparator wc;

    /**
     * Constructor for Class Trail Database
     */
    public TrailDatabase(){
        database = new ArrayList<>();
        input_file = "apptrailDB.txt";
        wc = new WaypointComparator(5, true);
        populateDatabase();
    }

    /**
     * Converts a string input into its integer category for sorting
     * @param input the input given by the user
     * @return the integer category for sorting
     */
    public int getSearchTerm(String input){
        return switch (input){
            case "TY" -> 1;
            case "NA" -> 2;
            case "DS" -> 5;
            case "DK" -> 6;
            case "EL" -> 4;
            default -> 0;
        };
    }

    /**
     * Reads in all the waypoints from the input file into the database
     */
    public void populateDatabase(){
        try{
            Scanner fr = new Scanner(new File(input_file));
            while (fr.hasNextLine()) {
                String[] line = fr.nextLine().split("\t");
                database.add(new Waypoint(line[0], line[1], line[2], Integer.parseInt(line[7]),
                        Double.parseDouble(line[5]), Double.parseDouble(line[6])));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Prints out the database
     */
    public void printDatabase(){
        for(Waypoint w : database){
            System.out.println(w);
        }
    }

    private void merge(int startIndex, int mid, int endIndex){
        int arrleftSize = mid - startIndex + 1;
        int arrRightSize = endIndex - mid;

        ArrayList<Waypoint> leftArr = new ArrayList<>(database.subList(startIndex, mid + 1));
        ArrayList<Waypoint> rightArr = new ArrayList<>(database.subList(mid+1, endIndex + 1));

        int leftArrInd = 0, rightArrInd = 0, mainArrInd = startIndex;

        while(leftArrInd < arrleftSize && rightArrInd < arrRightSize){
            if(wc.compare(leftArr.get(leftArrInd), (rightArr.get(rightArrInd))) <= 0){
                database.set(mainArrInd, leftArr.get(leftArrInd));
                leftArrInd++;
            }
            else{
                database.set(mainArrInd, rightArr.get(rightArrInd));
                rightArrInd++;
            }
            mainArrInd++;
        }

        while(leftArrInd < arrleftSize) {
            database.set(mainArrInd, leftArr.get(leftArrInd));
            mainArrInd++;
            leftArrInd++;
        }

        while(rightArrInd < arrRightSize) {
            database.set(mainArrInd, rightArr.get(rightArrInd));
            mainArrInd++;
            rightArrInd++;
        }
    }

    private void mergeSort(int startIndex, int endIndex){
        if(startIndex >= endIndex)
            return;
        int mid = startIndex + (endIndex - startIndex)/2;
        mergeSort(startIndex, mid);
        mergeSort(mid+1, endIndex);
        merge(startIndex, mid, endIndex);
    }

    private void mergeSort(){
        mergeSort(0, database.size() - 1);
    }

    /**
     * Sorts the database using a defined mergesort algorithm, based on the user's preference for sorting category
     */
    public void sortDB(){
        mergeSort();
    }

    /**
     * Main method for class TrailDatabse
     * @param args command line arguments if needed
     */
    public static void main(String[] args){
        TrailDatabase db = new TrailDatabase();
        String sortTerm, sortOrder;
        int category;
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("""
                    *** Welcome to the Appalachian Trail Database ***
                    + Menu of search terms to use for sorting waypoints +
                    \tTY: by type
                    \tNA: by name
                    \tDS: by distance to Springer
                    \tDK: by distance to Katahdin
                    \tEL: by elevation""");

            System.out.print("Enter your preferred sort by term or 'Q' to quit: ");
            sortTerm = in.nextLine();
            if(sortTerm.equals("Q"))
                break;
            category = db.getSearchTerm(sortTerm);
            if(category == 0) {
                System.out.println("Not a valid sorting category\n\n");
                continue;
            }
            System.out.print("Enter 'A' to sort in ascending order or 'D' to sort in descending order: ");
            sortOrder = in.nextLine();
            if(sortOrder.equals("A")){
                db.wc = new WaypointComparator(category, true);
            }else if(sortOrder.equals("D")){
                db.wc = new WaypointComparator(category, false);
            }else{
                System.out.println("Not a valid sorting order\n\n");
                continue;
            }
            db.sortDB();
            db.printDatabase();
            System.out.println("\n\n\n");
        }
        System.out.println("End of Program");
    }

}
