import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class CoinSorterMachine asks the user for a file, deposits coins based on the information in the data file, and
 * prints out a summary of the deposit
 * @version 3/29/22
 * @author 23crowley
 */
public class CoinSorterMachine {
    private final ArrayList<Coin> coins;
    /**
     * Constructor for Class CoinSorter Machine
     */
    public CoinSorterMachine(){
        coins = new ArrayList<>();
    }
    /**
     * Asks the user for an input datafile, and then deposits coins based on information in the file
     */
    public void depositCoins(){
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Enter the name of the data file for coin deposit: ");
        String filePath = inputReader.nextLine();
        try {
            readCoins(filePath);
        }catch (FileNotFoundException e){
            System.out.printf("File \"%s\" not found\n", filePath);
        }
    }

    private void readCoins(String filePath) throws FileNotFoundException{
        Scanner fileReader = new Scanner(new File(filePath));
        System.out.println("Depositing Coins . . .");
        while (fileReader.hasNext()){
            String line = fileReader.nextLine();
            switch (line) {
                case "100" -> coins.add(new Dollar());
                case "50" -> coins.add(new HalfDollar());
                case "25" -> coins.add(new Quarter());
                case "10" -> coins.add(new Dime());
                case "5" -> coins.add(new Nickel());
                case "1" -> coins.add(new Penny());
                default -> System.out.println("Coin value \"" + line + "\" not recognized");
            }
        }
        fileReader.close();
    }
    /**
     * Prints a summary of the coins deposited
     */
    public void printDepositSummary(){
        if (coins.isEmpty()){
            System.out.println("No Coins Deposited");
            return;
        }
        DecimalFormat df = new DecimalFormat("$0.00");
        System.out.println("Summary of Deposit: ");
        int[] coinCounter = {0, 0, 0, 0, 0, 0};
        for(Coin c: coins){
            if(c instanceof Penny)
                coinCounter[0] += 1;
            else if(c instanceof Nickel)
                coinCounter[1] += 1;
            else if(c instanceof Dime)
                coinCounter[2]+= 1;
            else if(c instanceof Quarter)
                coinCounter[3] += 1;
            else if(c instanceof HalfDollar)
                coinCounter[4] += 1;
            else
                coinCounter[5] += 1;
        }
        Coin[] compareCoin = {new Penny(), new Nickel(), new Dime(), new Quarter(), new HalfDollar(), new Dollar()};
        for (int i = 0; i < compareCoin.length; i++) {
            if (coinCounter[i] > 1)
                System.out.printf("\t%d %s %s\n", coinCounter[i], pluralCoinName(compareCoin[i]), df.format(coinCounter[i] * compareCoin[i].getValue()));
            else if (coinCounter[i] == 1)
                System.out.printf("\t%d %s %s\n", coinCounter[i], compareCoin[i].getName(), df.format(coinCounter[i] * compareCoin[i].getValue()));
        }
        System.out.println("TOTAL DEPOSIT: " + df.format(getTotalValue()));
    }
    /**
     * Finds the total value of the coins deposited
     * @return the total value of all Coin objects stored in coins as a double
     */
    public double getTotalValue(){
        double total = 0;
        for (Coin coin : coins)
            total += coin.getValue();
        return total;
    }

    private static String pluralCoinName(Coin c){
        String singleName = c.getName();
        if (singleName.charAt(singleName.length()-1) == 'y') {
            return singleName.substring(0, singleName.length()-1) + "ies";
        }
        return singleName + "s";
    }
    /**
     * Main method for class CoinSorterMachine
     * @param args command line arguments if needed
     */
    public static void main(String[] args){
        CoinSorterMachine app = new CoinSorterMachine();
        app.depositCoins();
        app.printDepositSummary();
    }
}
