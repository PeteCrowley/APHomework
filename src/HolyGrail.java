import java.util.Scanner;
/**
 * Class HolyGrail takes some input from questions and prints out some info based on that input.
 * @version 09/15/21
 * @author 23crowley
 */
public class HolyGrail{
    /**
     * Main entry point for class HolyGrail.
     * @param args command line arguments, if needed.
     */
    public static void main(String[] args){
        String greeting = """
                * A chat with the bridge keeper *
                Who would cross the Bridge of Death must answer me these questions three, ere the other side he see.""";
        String question1 = "Question 1: What is your name? ";
        String question2 = "Question 2: What is your quest? ";
        String question3 = "Question 3: What is your favorite color? ";
        String arthur_wisdom = "King Arthur says, \"You have to know these things when you're a king, you know.\"";
        Scanner ez_scan = new Scanner(System.in);
        System.out.println(greeting);
        System.out.print(question1);
        String name = ez_scan.nextLine();
        System.out.print(question2);
        String quest = ez_scan.nextLine();
        System.out.print(question3);
        String fav_color = ez_scan.nextLine();
        System.out.println(arthur_wisdom);
        System.out.println("Your name is: " + name);
        System.out.println("Your quest is: " + quest);
        System.out.println("Your favorite color is: " + fav_color);
        ez_scan.close();
        System.out.println("* end of program *");
    }
}