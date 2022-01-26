import java.util.*;
import java.io.*;



public class SubWordFinder implements WordFinder{
    private ArrayList<ArrayList<String>> dictionary; //jagged list
    private final String refString = "abcdefghijklmnopqrstuvwxyz";

    public SubWordFinder(){
        dictionary = new ArrayList<>(refString.length()); // 26 buckets, one for each letter
        for(int i = 0; i < refString.length(); i++)
            dictionary.set(i, new ArrayList<>());



    }

    public void populateDictionary(){
        try{
            Scanner dictReader = new Scanner(new File("words_all_os.txt"));
            while(dictReader.hasNext()){
                String word = dictReader.nextLine();
                dictionary.get(refString.indexOf(word.charAt(0))).add(word);
            }
            dictReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<SubWord> getSubWords() {
        return null;
    }

    @Override
    public boolean inDictionary(String word) {
        return false;
    }

}

