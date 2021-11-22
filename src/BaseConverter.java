import java.util.Scanner;
import java.io.*;
import javax.swing.*;

/**
 * BaseConverter opens a data file, reads, converts numbers, then prints out info
 * Extra of JFileChooser
 * Extra of adding a GUI to do Base Conversion
 * @version Thursday 11/18/2021
 * @author 23crowley
 */
public class BaseConverter {
    private final String char_values;
    private File input_file;
    /**
     * Constructor for class BaseConverter.
     * Chooses a File to use for Base Conversion.
     */
    public BaseConverter(){
        char_values = "0123456789ABCDEF";
        JFileChooser fc = new JFileChooser("datafiles");
        int choice = fc.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION)
            input_file = fc.getSelectedFile();

    }

    /**
     * Converts a string number of base fromBase
     * @param num number to be converted
     * @param fromBase the base the current number is in
     * @return the base 10 integer of that number
     */
    public int strToInt(String num, String fromBase)  {
        int base10 = 0;
        for(int i=num.length()-1; i>-1; i--)
            base10 += char_values.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), num.length() - i- 1);
        return base10;
    }

    /**
     * Converts a base-10 integer to a string of a specified base
     * @param num the base-10 integer that will be converted
     * @param toBase the new base for the number
     * @return the base-10 integer converted to a specified base
     */
    public String intToStr(int num, int toBase){
        if(num == 0)
            return "0";
        String base_n_num = "";
/*
        Silly Algo which builds from beginning and is probably slightly slower:
        for(int x=1; num>0; x++){
            base_n_num = char_values.charAt((int) (num%Math.pow(toBase, x)/Math.pow(toBase, x-1))) + base_n_num;
            num -= num%Math.pow(toBase, x);
        }
*/
        while(num>0){
            base_n_num = char_values.charAt(num%toBase) + base_n_num;
            num /= toBase;
        }
        return base_n_num;
    }

    /**
     * Opens the file stream, inputs data one line at a time, converts,
     * prints the result to the console window, and writes data to the output stream
     */
    public void inputConvertPrintWrite() {
        Scanner daScanner = null;
        try{
            daScanner = new Scanner(input_file);
            PrintWriter daWriter = new PrintWriter(new File("datafiles/converted.dat"));
            PrintWriter newWriter = new PrintWriter(new File("datafiles/printouts.txt"));
            while (daScanner.hasNextLine()) {
                String[] line = daScanner.nextLine().split("\t");
                String num = line[0], from_base = line[1];
                int to_base = Integer.parseInt(line[2]);
                if(Integer.parseInt(from_base)<2 || Integer.parseInt(from_base) > 16){
                    System.out.println("Invalid input base " + from_base);
                    newWriter.println("Invalid input base " + from_base);
                    continue;
                }
                if(to_base<2 || to_base > 16){
                    System.out.println("Invalid output base " + to_base);
                    newWriter.println("Invalid output base " + to_base);
                    continue;
                }
                int base10_num = strToInt(num, from_base);
                String num_base_n = intToStr(base10_num, to_base);
                System.out.println(num + " base " + from_base + " = " + num_base_n + " base " + to_base);
                newWriter.println(num + " base " + from_base + " = " + num_base_n + " base " + to_base);
                daWriter.println(num + '\t' + from_base + '\t' + num_base_n + '\t' + to_base);
            }
            newWriter.close();
            daWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (daScanner != null)
            daScanner.close();
    }

    /**
     * Runs a GUI that can convert a number from any base between 2 and 16 to any other base between 2 and 16
     * For full code coverage the GUI needs to be tested with some invalid input: a random string for number,
     * values either above or below 2 for both toBase and fromBase, a random string for either base, and inputs that
     * work properly.
     */
    public void runGUI(){
        JFrame frame = new JFrame("The Base Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        JPanel panel = new JPanel();
        JLabel num_label = new JLabel("Enter Number:");
        JTextField orig_num = new JTextField(10);
        JLabel from_base_label = new JLabel("From Base:");
        JTextField fromBase = new JTextField(2);
        JLabel to_base_label = new JLabel("To Base:");
        JTextField toBase = new JTextField(2);
        toBase.setSize(40, 20);
        JButton run = new JButton("Run!");
        run.setSize(30, 10);
        JLabel output_label = new JLabel("Output: ");
        JTextArea output = new JTextArea();

        panel.add(num_label);
        panel.add(orig_num);
        panel.add(from_base_label);
        panel.add(fromBase);
        panel.add(to_base_label);
        panel.add(toBase);
        panel.add(run);
        panel.add(output_label);
        panel.add(output);
        frame.add(panel);
        frame.setVisible(true);

        run.addActionListener(e -> {
            try {
                String num = orig_num.getText(), from_base = fromBase.getText();
                int to_base = Integer.parseInt(toBase.getText());
                for(int i=num.length()-1; i>-1; i--){
                    if(char_values.indexOf(num.charAt(i)) == -1){
                        output.setText("Invalid Input");
                        return;
                    }
                }
                if (Integer.parseInt(from_base) < 2 || Integer.parseInt(from_base) > 16) {
                    output.setText("Invalid input base " + from_base);
                    return;
                }
                if (to_base < 2 || to_base > 16) {
                    output.setText("Invalid output base " + to_base);
                    return;
                }
                int base10_num = strToInt(num, from_base);
                String num_base_n = intToStr(base10_num, to_base);
                output.setText(num + " base " + from_base + " = " + num_base_n + " base " + to_base);
            }
            catch (Exception ex){
                output.setText("Invalid Input");
            }
        });
    }

    /**
     * Main method for class BaseConverter.
     * @param args command line arguments if needed.
     */
    public static void main(String[] args) {
        BaseConverter converter = new BaseConverter();
        converter.inputConvertPrintWrite();
        Scanner quickScan = new Scanner(System.in);
        System.out.print("\n\nDo you want to run the GUI? (y/n): ");
        if (quickScan.nextLine().equals("y"))
            converter.runGUI();
    }
}