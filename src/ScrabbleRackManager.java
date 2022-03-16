import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScrabbleRackManager {
    private ArrayList<String> rack;
    private ArrayList<String> dictionary;
    //A-9, B-2, C-2, D-4, E-12, F-2, G-3, H-2, I-9, J-1, K-1, L-4, M-2, N-6, O-8, P-2, Q-1, R-6, S-4, T-6, U-4, V-2, W-2, X-1, Y-2, Z-1 and Blanks-2.
    private final int[] letterFreq = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    private final int[] points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private ArrayList<Character> letterBank;
    /**
     * Class Constructor
     */
    public ScrabbleRackManager(){
        rack = new ArrayList<>(7);
        generateRack();
        buildDictionary();
        generateLetterBank();
    }

    private void generateRack(){
        rack.add("O");
        rack.add("T");
        rack.add("A");
        rack.add("B");
        rack.add("I");
        rack.add("N");
        rack.add("S");
    }

    private void generateLetterBank(){
        letterBank = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            for (int x = 0; x < letterFreq[i]; x++)
                letterBank.add(alpha.charAt(i));
        }
    }

    private void buildDictionary(){
        dictionary = new ArrayList<>();
        String wordFilePath = "2019_collins_scrabble.txt";
        try{
            Scanner dictReader = new Scanner(new File(wordFilePath));
            while(dictReader.hasNext()){
                dictionary.add(dictReader.nextLine().toUpperCase());
            }
            dictReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Collections.sort(dictionary);
    }

    /**
     * Displays the contents of the player's tile rack
     */
    public void printRack(){
        System.out.println("Letters in the rack: " + rack);
    }

    private boolean isPlayable(String word){
        ArrayList<String> copiedRack = new ArrayList<>(rack);
        for (int i = 0; i < word.length(); i++){
            if (!copiedRack.remove(word.substring(i, i+1)))
                return false;
        }
        return true;
    }

    /** builds and returns an ArrayList of String objects that are values pulled from
     * the dictionary/database based on the available letters in the user's tile
     * rack
     */
    public ArrayList<String> getPlaylist(){
        ArrayList<String> playableWords = new ArrayList<String>();
        for(String word : dictionary){
            if (isPlayable(word))
                playableWords.add(word);
        }
        return playableWords;
    }

    private String convertToUniform(String word){
        if(word.length() == 7)
            word += "*";
//        for(int i=0; i < 15-word.length(); i+=3)
//            word += "\t";
        return word;
    }

    /** print all the playable words based on the letters in the tile rack */
    public void printMatches(){
        ArrayList<String> playableWords = getPlaylist();
        System.out.println("You can play the following words from the letters in your rack:");
        for(int i = 0; i < playableWords.size(); i++){
            System.out.println(convertToUniform(playableWords.get(i)));
//            if(i%9==0 & i != 0)
//                System.out.println();
        }
    }
    /** main method for the class; use only 3 command lines in main */
    public static void main(String[] args){
        ScrabbleRackManager app = new ScrabbleRackManager();
        System.out.println(app.letterBank);
        app.printRack();
        app.printMatches();

    }

}
