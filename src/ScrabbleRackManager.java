import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Class ScrabbleRackManager builds a random tile rack, gets all possible words that
 * can be played, and displays them with their score
 * It handles blank tiles, and also sorts them in order of their score
 * @version 3/23/22
 * @author 23crowley
 */


public class ScrabbleRackManager {
    private final ArrayList<Character> rack;
    private ArrayList<ArrayList<String>> dictionary;
    //A-9, B-2, C-2, D-4, E-12, F-2, G-3, H-2, I-9, J-1, K-1, L-4, M-2, N-6, O-8, P-2, Q-1, R-6, S-4, T-6, U-4, V-2, W-2, X-1, Y-2, Z-1 and Blanks-2.
    private final int[] letterFreq = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2};
    private final int[] points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0};
    private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private ArrayList<Character> letterBank;
    private File textFile;
    /**
     * Class Constructor
     */
    public ScrabbleRackManager(){
        JFileChooser fc = new JFileChooser("./");
        int choice = fc.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION)
            textFile = fc.getSelectedFile();
        rack = new ArrayList<>(7);
        generateLetterBank();
        generateRack();
        buildDictionary();

    }

//    public ScrabbleRackManager(String filePath){
//        textFile = new File(filePath);
//        rack = new ArrayList<>(7);
//        generateLetterBank();
//        generateRack();
//        buildDictionary();
//    }

    private void generateRack(){
        for(int i = 0; i < 7; i++)
            addNewTileToRack();
    }


    private void addNewTileToRack(){
        rack.add(letterBank.remove((int) (Math.random() * letterBank.size())));
    }

    private void generateLetterBank(){
        letterBank = new ArrayList<>();
        for(int i = 0; i < alpha.length(); i++) {
            for (int x = 0; x < letterFreq[i]; x++)
                letterBank.add(alpha.charAt(i));
        }
        Collections.shuffle(letterBank);
    }

    private void buildDictionary(){

        dictionary = new ArrayList<>();
        for(int i=0; i<26; i++){
            dictionary.add(new ArrayList<>());
        }
        try{
            Scanner dictReader = new Scanner(textFile);
            while(dictReader.hasNext()){
                String word = dictReader.nextLine();
                dictionary.get(alpha.indexOf(word.charAt(0))).add(word);
            }
            dictReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(ArrayList<String> bucket : dictionary)
            Collections.sort(bucket);
    }

    /**
     * Displays the contents of the player's tile rack
     */
    public void printRack(){
        System.out.println("Letters in the rack: " + rack);
    }

    private boolean isPlayable(String word){
        ArrayList<Character> copiedRack = new ArrayList<>(rack);
        for (int i = 0; i < word.length(); i++){
            if (!copiedRack.remove((Character) word.charAt(i))) {
                if (!copiedRack.remove((Character) ' '))
                    return false;
            }
        }
        return true;
    }



    private int getScore(String word){
        ArrayList<Character> copiedRack = new ArrayList<>(rack);
        int score = 0;
        for (int i = 0; i < word.length(); i++){
            if (copiedRack.remove((Character) word.charAt(i))) {
                score += points[alpha.indexOf(word.charAt(i))];
            }

        }
        return score;
    }

    Comparator<String> compareByScore = new Comparator<String>() {
        public int compare(String o1, String o2) {
            return getScore(o2) - getScore(o1);
        }
    };


    /** builds and returns an ArrayList of String objects that are values pulled from
     * the dictionary/database based on the available letters in the user's tile
     * rack
     * @return an ArrayList of all playable words
     */
    public ArrayList<String> getPlaylist(){
        ArrayList<String> playableWords = new ArrayList<>();
        for(ArrayList<String> bucket : dictionary){
            if (!rack.contains(bucket.get(0).charAt(0)) && !rack.contains((Character) ' '))
                continue;
            for(String word: bucket) {
                if (word.length() <= 7 && isPlayable(word))
                    playableWords.add(word);
            }
        }
        return playableWords;
    }


    /** prints all the playable words based on the letters in the tile rack */
    public void printMatches(){
        String wordAndScore;
        ArrayList<String> playableWords = getPlaylist();

        if (playableWords.isEmpty())
            System.out.println("Sorry you can't play any words with those letters");
        else {
            playableWords.sort(compareByScore);
            boolean isBingo = false;
            System.out.printf("You can play the following %d words from the letters in your rack, \nin order of the most points to the least:\n", playableWords.size());
            for(int i = 0; i < playableWords.size(); i++){
                String word = playableWords.get(i);
                if(i%5 == 0 && i != 0)
                    System.out.println();
                if(word.length() == 7) {
                    wordAndScore = word + "*" + ": " + getScore(word);
                    isBingo = true;
                }
                else{
                    wordAndScore = word + ": " + getScore(word);
                }
                System.out.printf("%-15s ", wordAndScore);
            }
            System.out.println();
            if(isBingo)
                System.out.println("* denotes BINGO");
        }

    }

//    public void setCustomRack(String customRack){
//        for(int i=0; i<customRack.length(); i++){
//            rack.set(i, customRack.charAt(i));
//        }
//    }

    /** main method for the class; use only 3 command lines in main
     * @param args command line arguments if needed
     */
    public static void main(String[] args){
        for(int i=0; i<1; i++) {
//            ScrabbleRackManager app = new ScrabbleRackManager("SCRABBLE_WORDS.txt");

            ScrabbleRackManager app = new ScrabbleRackManager();
//            app.setCustomRack("       ");     // You can uncomment this method and use it to test things
            app.printRack();
            app.printMatches();
        }

    }

}
