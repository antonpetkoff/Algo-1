package needle.haystack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NeedleHaystack {

    public static final int B = 31;         // BASE
    public static final int M = 9999047;    // MODULO
    
    // 'a' must be 1
    // [i, j]
    public static int hash(String str, int i, int j) {
        int hash = 0;
        for (int k = i; k <= j; ++k) {
            hash = ( hash * B % M + (str.charAt(k) - 'a' + 1) ) % M;
        }
        return hash;
    }
    
    public static int pow(int base, int power) {
        int result = base;
        for (int i = 1; i < power; ++i) {
            result = (result * base) % M;
        }
        return result;
    }
    
    // TODO: fix if haystack is ddog, and needle is dog it doesn't find it
    public static List<Integer> rabbinKarp(String haystack, String needle) {
        List<Integer> result = new ArrayList<Integer>();
        
        int hashHaystack = hash(haystack, 0, needle.length() - 1),
            hashNeedle = hash(needle, 0, needle.length() - 1),
            topPower = pow(B, needle.length() - 1);
        
        for (int i = 0; i < haystack.length() - needle.length(); ++i) {
            if (hashNeedle == hashHaystack) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    result.add(i);
                }
            }
            hashHaystack = ( (hashHaystack - (haystack.charAt(i) - 'a' + 1) * topPower) * B % M
                    + (haystack.charAt(i + needle.length()) - 'a' + 1) ) % M;
        }
        
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String haystack = scanner.nextLine(), needle = scanner.nextLine();
        
//        System.out.println(hash("bbb", 0, 2));
        List<Integer> result = rabbinKarp(haystack, needle);
        
        for (Integer integer : result) {
            System.out.println(integer);
        }
        
        
        scanner.close();
    }
    
}
