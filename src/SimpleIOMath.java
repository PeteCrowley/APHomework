import java.util.Scanner;

/**
 * Class SimpleIOMath takes some string and numerical input, manipulates it and prints out the result.
 * @version 9/20/21
 * @author 23crowley
 */
public class SimpleIOMath{
    private String name;
    private int age;
    private int fav_num;

    /**
     * Checks if a number is prime.
     * @param num the integer that is being checked.
     * @return true if prime, false if not prime.
     */
    public static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
    // Generation Years according to this website: https://www.beresfordresearch.com/age-range-by-generation/
    /**
     * Gets the generation of user based on their age
     * @param age the age of the user
     * @return the generation of the user based on the age
     */
    public static String getGeneration(int age){
        String generation;
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
        return generation;
    }

    /**
     * Finds the first prime factor of a given number.
     * @param num the integer of which you want to find the first prime factor.
     * @return the first prime factor.
     */
    public static int getFirstPrime(int num){
        for (int i = 2; i < num/3; i ++){
            if (num % i == 0){
                if (isPrime(i)){
                    return i;
                }
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
        System.out.print(question2);
        age = ez_scan.nextInt();
        System.out.print(question3);
        fav_num = ez_scan.nextInt();
        ez_scan.close();
    }

    /**
     * A helper function to print out information once it has been received from the user.
     */
    public void printInfo(){
        String limerick = """
                Let me tell you what it's all about
                Reading, writing, arithmetic
                Are the branches of the learning tree""";
        System.out.println(limerick);
        System.out.println("Your name is: " + name);
        System.out.println("Your age is: " + age);
        System.out.println("Your generation is: " + getGeneration(age));
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
        program.printInfo();
    }
}
