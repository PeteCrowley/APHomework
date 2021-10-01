/**
 * Class RomanToDecimal takes Roman Numerals and converts them to their decimal equivalent.
 * Extra of ensuring numerals are logical
 * @version 10/1/21
 * @author 23crowley
 */

public class RomanToDecimal {
    private static final String[] special_combos = {"IV", "IX", "XL", "XC", "CD", "CM"};
    private static final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] r_numerals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};


    // help from here: https://algorithms.tutorialhorizon.com/convert-integer-to-roman/
    private static String decimalToRoman(int decimal){
        StringBuilder roman = new StringBuilder();
        int num = decimal;
        for (int i = 0; i < values.length; i++){
            while (num >= values[i]){
                roman.append(r_numerals[i]);
                num -= values[i];
            }
        }
        return roman.toString();
    }

    private static boolean isLogical(String roman){
        String roman_upper;
        try{roman_upper = roman.toUpperCase();}
        catch(Exception e){return false;}
        int calculated_value = romanToDecimal(roman);
        if (calculated_value == -1) return false;
        return roman_upper.equals(decimalToRoman(calculated_value));
    }

    /**
     * Converts a roman numeral to its decimal equivalent.
     * @param roman the roman numeral you wish to find the value of.
     * @return -1 if invalid, otherwise the decimal equivalent of the numeral.
     */
    public static int romanToDecimal(String roman){
        String roman_upper;
        try{roman_upper = roman.toUpperCase();}
        catch(Exception e){return -1;}
        int decimal = 0;
        for (int i = 0; i < roman_upper.length(); i++){
            switch (roman_upper.substring(i, i + 1)) {      // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
                case "I":
                    decimal += 1;
                    break;
                case "V":
                    decimal += 5;
                    break;
                case "X":
                    decimal += 10;
                    break;
                case "L":
                    decimal += 50;
                    break;
                case "C":
                    decimal += 100;
                    break;
                case "D":
                    decimal += 500;
                    break;
                case "M":
                    decimal += 1000;
                    break;
                default:
                    return -1;
            }
        }
        int [] scValues = new int[]{2, 2, 20, 20, 200, 200};
        for(int i = 0; i < 6; i++) {
            String special_combo = special_combos[i];
            int char_value = scValues[i];
            String mini_string = roman_upper;
            while (mini_string.indexOf(special_combo) != -1) {
                decimal -= char_value;
                mini_string = mini_string.substring(mini_string.indexOf(special_combo) + 2);
            }
        }
        return decimal;
    }

    /**
     * The main entry point for class RomanToDecimal.
     * @param args command line arguments if needed.
     */
    public static void main(String[] args){
        for (String temp : args){
            int val = romanToDecimal(temp);
            if (val == -1){
                System.out.println("Input: " + temp + "=> output: invalid numeral");
            }
            else if (!isLogical(temp)){
                System.out.println("Input: " + temp + "=> output: " + romanToDecimal(temp) + ", illogical numeral");
            }
            else {
                System.out.println("Input: " + temp + "=> output: " + romanToDecimal(temp));
            }
        }
    }
}
