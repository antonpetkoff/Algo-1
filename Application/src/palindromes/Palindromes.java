package palindromes;

import java.util.ArrayList;
import java.util.List;

public class Palindromes {

    public static boolean isPalindrome(char[] chars, int begin, int end) {
        int half = (end - begin) / 2;

        for (int i = 0; i < half; ++i) {
            if (chars[begin + i] != chars[end - i - 1]) {
                return false;
            }
        }

        return true;
    }

    public static List<String> generatePalindromes(String str) {
        List<String> generated = new ArrayList<String>();
        char[] chars = new char[str.length() * 2];

        for (int i = 0; i < str.length(); ++i) {
            chars[i] = str.charAt(i);
        }

        int begin = 0; // inclusive
        int end = str.length(); // exclusive

        for (int i = 0; i < str.length(); ++i) {
            if (isPalindrome(chars, begin, end)) {
                generated.add(new String(chars, begin, end - begin));
            }

            chars[end++] = chars[begin++]; // rotate string
        }

        return generated;
    }

    public static void main(String[] args) {
        List<String> generated = generatePalindromes("labalaa");

        if (generated.isEmpty()) {
            System.out.println("NONE");
        } else {
            generated.stream().forEach(s -> System.out.println(s));
        }
    }

}
