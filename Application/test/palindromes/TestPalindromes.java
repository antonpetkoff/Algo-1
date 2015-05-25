package palindromes;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class TestPalindromes {

    @Test
    public void testIsPalindrome() {
        char[] chars = "alenafanela".toCharArray();
        assertTrue(Palindromes.isPalindrome(chars, 0, chars.length));
    }

    @Test
    public void testIsNotPalindrome() {
        char[] chars = "neiko lapa slivi".toCharArray();
        assertFalse(Palindromes.isPalindrome(chars, 0, chars.length));
    }

    @Test
    public void testGeneratePalindromes() {
        List<String> generated1 = Palindromes.generatePalindromes("labalaa");
        assertTrue(generated1.size() == 1);
        assertTrue(generated1.contains("alabala"));

        List<String> generated2 = Palindromes.generatePalindromes("akawwaka");
        assertTrue(generated2.size() == 2);
        assertTrue(generated2.contains("wakaakaw"));
        assertTrue(generated2.contains("akawwaka"));
    }

    @Test
    public void testGeneratePalindromesNone() {
        List<String> generated3 = Palindromes.generatePalindromes("shakira");
        assertTrue(generated3.size() == 0);
    }
}
