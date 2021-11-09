import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BaseConverter {

    public BaseConverter(){

    }

    public int strToInt(String num, String fromBase)  {
        int numFromBase = Integer.parseInt(num);
        int base = Integer.parseInt(fromBase);
        int base10num = 0;
        int i = 0;
        while(numFromBase > 0){
            base10num +=  numFromBase % 10 * Math.pow(base, i);
            numFromBase /= 10;
            i++;
        }
        System.out.println(base10num);
        return base10num;
    }

    public String intToStr(int num, int toBase){
        return "newString";
    }

    public void inputConvertPrintWrite() throws FileNotFoundException {
        Scanner daScanner = new Scanner(System.in);
        try{
            daScanner = new Scanner(new File("src/values.dat"));
        }
        catch (Exception e){
            System.err.println("File Not Found");
        }
        while (daScanner.hasNextLine()) {
            String line = daScanner.nextLine();
            System.out.println(line);
        }
        System.out.println("printing data");
    }

    public static void main(String[] args) throws FileNotFoundException {
        BaseConverter converter = new BaseConverter();
        converter.inputConvertPrintWrite();
    }
}
