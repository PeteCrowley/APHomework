import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Class BMICalculator takes weight in lbs and height in feet and inches
 * and outputs the BMI in kg/m^2
 * @version 10/12/21
 * @author 23crowley
 */
public class BMICalculator {
    /**
     * Converts English to metric units, the BMI calculation.
     * @param inches height in inches as an integer
     * @param pounds weight in pounds as an integer
     * @return BMI as a double
     */
    public static double computeBMI(int inches, int pounds){
        if (inches <= 0 || pounds <= 0)
            return 0;
        return 0.454 * pounds / Math.pow(0.0254 * inches, 2);
    }

    /**
     * Main entry point for class BMICalculator
     * Uses a Scanner to prompt the user for info, processes the feet/inches
     * conversion, calls the computeBMI method and prints
     * the correct information.
     * Height inputs for 100% coverage: notanum, 6'5, -6'5", 6'13", 6'1", 0'0"
     * Weight inputs for 100% coverage: notanum, -15, 15
     * @param args command line arguments if needed
     */
    public static void main(String[] args) {
        DecimalFormat BMI_Form = new DecimalFormat("0.00");
        Scanner ez_scan = new Scanner(System.in);
        boolean running = true;
        while(running) {
            int feet = -1, inches = -1, weight = 0;
            while (feet <= 0 || (inches <= 0 || inches > 11)) {
                System.out.print("Enter your height in feet and inches (Ex 6'1\"): ");
                String height = ez_scan.nextLine();
                int feet_pos = height.indexOf("'");
                int inch_pos = height.indexOf("\"");
                try {
                    feet = Integer.parseInt(height.substring(0, feet_pos));
                    inches = Integer.parseInt(height.substring(feet_pos + 1, inch_pos));
                } catch (Exception e) {
                    System.out.println("Not a valid input, use the given form to enter an integer height greater than 0.");
                    continue;
                }
                if (feet <= 0 || inches <= 0) {
                    System.out.println("Enter values greater than 0.");
                } else if (inches > 11) {
                    System.out.println("Enter a value for inches less than 12");
                }
            }

            while (weight <= 0) {
                System.out.print("Enter your weight in pounds: ");
                try {
                    weight = ez_scan.nextInt();
                    ez_scan.nextLine();
                } catch (Exception e) {
                    ez_scan.nextLine();
                    System.out.println("Invalid input, please enter an integer.");
                    continue;
                }
                if (weight <= 0)
                    System.out.println("Your weight must be greater than 0");
            }
            double BMI = (computeBMI(12*feet+inches, weight));
            System.out.println("Your BMI, expressed as weight(kg)/height(m)^2: " + BMI_Form.format(BMI) + " kg/m^2");
            System.out.print("Another Number? (yes or no): ");
            if(!(ez_scan.nextLine().equalsIgnoreCase("yes")))
                running = false;
        }

    }
}
