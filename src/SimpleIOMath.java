import java.util.Scanner;

/**
 * Class SimpleIOMath takes some string and numerical input, manipulates it and prints out the result.
 * Extras of validating numerical input and finding the generation based on age.
 * @version 9/20/21
 * @author 23crowley
 */
public class SimpleIOMath{
    private String name;
    private int age;
    private int fav_num;
    private String generation;

    /**
     * Gets generation based on age
     * Generation Years according to this website: https://www.beresfordresearch.com/age-range-by-generation/
     */
    private void getGeneration(){
        if (age > 98){
            generation = "Very very old";
        }
        else if (age > 93){
            generation = "WWII";
        }
        else if (age > 75){
            generation = "Post War";
        }
        else if (age > 66){
            generation = "Boomers I";
        }
        else if (age > 56){
            generation = "Boomers II";
        }
        else if (age > 40){
            generation = "Gen X";
        }
        else if (age > 24){
            generation = "Millennial";
        }
        else if (age > 9){
            generation = "Gen Z";
        }
        else{
            generation = "Too Young";
        }
    }

    public static int getFirstPrime(int num){
        for (int i = 2; i < Math.sqrt(num)+1; i++) {
            if (num % i == 0) {
                return i;
            }
        }
        return num;
    }

    /**
     * A Helper function which ask the user questions and takes input.
     */
    public void promptUser(){
        String question1 = "Question 1: What is your name? ";
        String question2 = "Question 2: How old are you? ";
        String question3 = "Question 3: What is your favorite number? ";
        Scanner ez_scan = new Scanner(System.in);
        System.out.print(question1);
        name = ez_scan.nextLine();
        while (age == 0){
            System.out.print(question2);
            try {age = ez_scan.nextInt();}
            catch(Exception e){
                System.out.println("Please enter a non-zero integer.");
                ez_scan.next();
                // Previous line added to avoid infinite loop, help from the link below
                // https://stackoverflow.com/questions/47852298/how-to-keep-looping-with-try-and-catch-to-get-correct-input
            }
        }
        while (fav_num == 0){
            System.out.print(question3);
            try {fav_num = ez_scan.nextInt();}
            catch(Exception e){
                System.out.println("Please enter a non-zero integer.");
                ez_scan.next();
            }
        }
        ez_scan.close();
    }

    /**
     * A helper function to print out information once it has been received from the user.
     */
    public void printInfo(){
        System.out.println("""
                Let me tell you what it's all about
                Reading, writing, arithmetic
                Are the branches of the learning tree""");
        System.out.println("Your name is: " + name);
        System.out.println("Your age is: " + age);
        System.out.println("Your generation is: " + generation);
        System.out.println("At your next birthday, you will turn " + (age+1) + ".");
        System.out.println("The first prime factor of " + age + " is: " + getFirstPrime(age));
        System.out.println("Your favorite number is: " + fav_num);
        System.out.println("Your favorite number squared is: " + (fav_num*fav_num));
        System.out.println("* end of program *");
    }

    /**
     * Main entry point for class SimpleIOMath.
     * @param args command line arguments if needed.
     */
    public static void main(String[] args){
        System.out.println("* Sit yourself down, take a seat *\n"
                + "* All you gotta do is repeat after me *");
        SimpleIOMath program = new SimpleIOMath();
        program.promptUser();
        program.getGeneration();
        program.printInfo();
    }
}
