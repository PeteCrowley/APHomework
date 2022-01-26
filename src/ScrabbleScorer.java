import java.io.File;
import java.util.*;

/**
 * Class ScrabbleScorer validates and scores user-given words based on their values
 * in teh game scrabble
 * @version 1/18/22
 * @author 23crowley
 */

public class ScrabbleScorer {

    private final ArrayList<String> dictionary;
    private final int[] points;
    private final String quitWord;
    private final String refString;

    private int letterToVal(char letter){
        return points[refString.indexOf(letter)];
    }

    /**
     * Constructor for class Scrabble Scorer
     * Initializes the point values for letters and builds the dictionary of valid words
     */
    public ScrabbleScorer(){
        points = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        quitWord = "0";
        refString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        dictionary = new ArrayList<>();
        buildDictionary();
    }

    /**
     * Builds the dictionary of valid words by reading words from the file SCRABBLE_WORDS.txt
     */
    public void buildDictionary(){
        try{
            Scanner fileReader = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while (fileReader.hasNextLine()){
                String word = fileReader.nextLine();
                dictionary.add(word);
            }
            fileReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Collections.sort(dictionary);
    }

    /**
     * Checks if a word is valid by testing whether it is in the previously built dictionary
     * @param word the word whose validity will be tested
     * @return true if the word is valid, otherwise false
     */
    public boolean isValidWord(String word){
        return Collections.binarySearch(dictionary, word) >= 0;
    }

    /**
     * Calculates the score for a given word
     * @param word the word whose score will be calculated
     * @return the integer score of the word
     */
    public int getWordScore(String word){
        int score = 0;
        for(int i = 0; i<word.length(); i++)
            score += letterToVal(word.charAt(i));
        return score;
    }
    /**
     * The main entry point for class ScrabbleScorer.
     * @param args command line arguments if needed.
     */
    public static void main(String[] args){
        ScrabbleScorer scorer = new ScrabbleScorer();
        boolean running = true;
        Scanner daScanner = new Scanner(System.in);
        do{
            System.out.print("Enter a word to be scored (0 to quit): ");
            String word = daScanner.nextLine();
            if (word.equals(scorer.quitWord)) {
                System.out.println("Exiting the program thanks for playing");
                running = false;
            }
            else{
                word = word.toUpperCase();
                if (!scorer.isValidWord(word))
                    System.out.println(word + " is not a valid word in the dictionary");
                else
                    System.out.printf("%s = %d points\n", word, scorer.getWordScore(word));

            }
        }while (running);

    }

}
