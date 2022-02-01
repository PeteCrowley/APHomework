import java.util.*;
import java.io.*;

/**
 * Class SubWordFinder reads in words from a text file, finds all the words that can be seperated into
 * two sub words, and prints them out into a separate output file
 * @author 23crowley
 * @version 1/28/22
 */

public class SubWordFinder implements WordFinder{
    private final ArrayList<ArrayList<String>> dictionary; //jagged list
    private final String refString = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Constructor for Class SubWordFinder
     */
    public SubWordFinder(){
        dictionary = new ArrayList<>(refString.length()); // 26 buckets, one for each letter
        for(int i = 0; i < refString.length(); i++)
            dictionary.add(new ArrayList<>());
        populateDictionary();
    }

    /**
     * Populates the dictionary from the text file contents
     * The dictionary object contains 26 buckets, each
     * bucket filled with an ArrayList<String>
     * The String objects in the buckets are sorted A-Z
     */
    public void populateDictionary(){
        String wordFilePath = "words_all_os.txt"; // comment this line out and uncomment the next line for other word file
//        String wordFilePath = "SCRABBLE_WORDS.txt";
        try{
            Scanner dictReader = new Scanner(new File(wordFilePath));
            while(dictReader.hasNext()){
                String word = dictReader.nextLine().toLowerCase();
                dictionary.get(refString.indexOf(word.charAt(0))).add(word);
            }
            dictReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(ArrayList<String> bucket : dictionary)
            Collections.sort(bucket);
    }

    /**
     * Retrieve all SubWord objects from the dictionary.
     * A SubWord is defined as a word that can be split into two
     * words that are also found in the dictionary.  The words
     * are split evenly, e.g. no unused characters.
     * For example, "baseball" is a SubWord because it contains
     * "base" and "ball" (no unused characters)
     * To do this, it looks through every word in the dictionary
     * to see if it is a SubWord object
     * @return An ArrayList containing the SubWord objects
     * pulled from the given file
     */
    public ArrayList<SubWord> getSubWords() {
        String firstWord, secondWord;
        ArrayList<SubWord> subWordList = new ArrayList<>();
        for(ArrayList<String> bucket : dictionary){
            for(String word: bucket){
                for(int i = 2; i < word.length() - 1; i++){
                    firstWord = word.substring(0, i);
                    if(inDictionary(firstWord)){
                        secondWord = word.substring(i);
                        if(inDictionary(secondWord)){
                            subWordList.add(new SubWord(word, firstWord, secondWord));
                        }
                    }
                }
            }
        }
        return subWordList;
    }

    private static int indexOf(ArrayList<String> bucket, String word){
        int low = 0;
        int high = bucket.size() - 1;
        int mid, compareScore;
        while(high - low >= 0){
            mid = (low + high) / 2;
            compareScore = bucket.get(mid).compareTo(word);
            if(compareScore==0)
                return mid;
            else if(compareScore > 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    /**
     * Look through the entire dictionary object to see if
     * word exists in dictionary
     * Uses the explicitly defined indexOf method to utilize binary search, and efficiency of O(n)
     * @param word The item to be searched for in dictionary
     * @return true if word is in dictionary, false otherwise
     */
    public boolean inDictionary(String word) {
        ArrayList<String> bucket = dictionary.get(refString.indexOf(word.charAt(0)));
        return indexOf(bucket, word) >= 0;
    }

    private static void printSubWords(ArrayList<SubWord> subWordList){
        try {
            PrintWriter fileWriter = new PrintWriter(new File("foundSubWords.txt"));
            for(SubWord word: subWordList){
                System.out.println(word);
                fileWriter.println(word);
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void getCommonPrefix(ArrayList<SubWord> subWordList){
        ArrayList<String> prefixList = new ArrayList<>();
        ArrayList<Integer> prefixCounts = new ArrayList<>();
        int position;
        for(SubWord word : subWordList){
            position = prefixList.indexOf(word.getSub1());
            if(position < 0){
                prefixList.add(word.getSub1());
                prefixCounts.add(1);
            }
            else{
                prefixCounts.set(position, prefixCounts.get(position)+1);
            }
        }
        int maxVal = 0, maxInd = 0;
        for(int i = 0; i<prefixCounts.size(); i++){
            if (prefixCounts.get(i) > maxVal){
                maxInd = i;
                maxVal = prefixCounts.get(i);
            }
        }
        System.out.println(prefixList.get(maxInd) + " was the most common prefix with " + maxVal + " occurences");
    }

    private static void getCommonSuffix(ArrayList<SubWord> subWordList){
        ArrayList<String> suffixList = new ArrayList<>();
        ArrayList<Integer> suffixCounts = new ArrayList<>();
        int position;
        for(SubWord word : subWordList){
            position = suffixList.indexOf(word.getSub2());
            if(position < 0){
                suffixList.add(word.getSub2());
                suffixCounts.add(1);
            }
            else{
                suffixCounts.set(position, suffixCounts.get(position)+1);
            }
        }
        int maxVal = 0, maxInd = 0;
        for(int i = 0; i < suffixCounts.size(); i++){
            if (suffixCounts.get(i) > maxVal){
                maxInd = i;
                maxVal = suffixCounts.get(i);
            }
        }
        System.out.println(suffixList.get(maxInd) + " was the most common suffix with " + maxVal + " occurences");
    }


    /**
     * Main method for class SubWordFinder
     * @param args command line arguments if needed
     */
    public static void main(String[] args) {
        SubWordFinder swf = new SubWordFinder();
        ArrayList<SubWord> subWords = swf.getSubWords();
        getCommonPrefix(subWords);
        getCommonSuffix(subWords);
        printSubWords(subWords);




    }

}

