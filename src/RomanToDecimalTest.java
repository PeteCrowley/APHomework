import static org.junit.Assert.*;

/**
 * Class RomanToDecimalTest is the JUnit test class for RomanToDecimal.
 * @version 10/1/21
 * @author 23crowley
 */
public class RomanToDecimalTest {
    /**
     * The tester method for the romanToDecimal method. It tests 10 valid, 4 illogical, and 4 invalid inputs.
     */
    @org.junit.Test
    public void romanToDecimal() {
        // valid
        assertEquals(RomanToDecimal.romanToDecimal("XI"), 11);
        assertEquals(RomanToDecimal.romanToDecimal("xiv"), 14);
        assertEquals(RomanToDecimal.romanToDecimal("XLIV"), 44);
        assertEquals(RomanToDecimal.romanToDecimal("MCMLXIV"), 1964);
        assertEquals(RomanToDecimal.romanToDecimal("m"), 1000);
        assertEquals(RomanToDecimal.romanToDecimal("CMXLIII"), 943);
        assertEquals(RomanToDecimal.romanToDecimal("mdclxvi"), 1666);
        assertEquals(RomanToDecimal.romanToDecimal("mix"), 1009);
        assertEquals(RomanToDecimal.romanToDecimal(""), 0);
        assertEquals(RomanToDecimal.romanToDecimal("MMCDLXXXVIII"), 2488);
        // illogical
        assertEquals(RomanToDecimal.romanToDecimal("IiIiV"), 7);
        assertEquals(RomanToDecimal.romanToDecimal("IVIV"), 8);
        assertEquals(RomanToDecimal.romanToDecimal("CMCDXCVII"), 1397);
        assertEquals(RomanToDecimal.romanToDecimal("MCCCCCCCXXXXXXXIIIIII"), 1776);
        // invalid
        assertEquals(RomanToDecimal.romanToDecimal("Hello"), -1);
        assertEquals(RomanToDecimal.romanToDecimal("XVIZ"), -1);
        assertEquals(RomanToDecimal.romanToDecimal("AP Computer Science is Fun"), -1);
        assertEquals(RomanToDecimal.romanToDecimal("100"), -1);
        // assertNotEquals
        assertNotEquals(RomanToDecimal.romanToDecimal("IXIX"), 20);
        assertNotEquals(RomanToDecimal.romanToDecimal("xicivi"), -1);
    }
}