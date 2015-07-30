package longest.common.substring;

import java.util.Scanner;

public class LongestCommonSubstring {

    public static String longestCommonSubstring(String p, String q) {
        int len[][] = new int[p.length() + 1][q.length() + 1];
        
        for (int i = 0; i < len.length; ++i) {
            len[i][0] = 0;
        }
        
        for (int i = 0; i < len[0].length; ++i) {
            len[0][i] = 0;
        }
        
        int max = 0, maxIndex = 0;
        
        for (int i = 1; i < len.length; ++i) {
            for (int j = 1; j < len[i].length; ++j) {
                if (p.charAt(i - 1) == q.charAt(j - 1)) {
                    len[i][j] = len[i - 1][j - 1] + 1;
                    if (max < len[i][j]) {
                        max = len[i][j];
                        maxIndex = i;
                    }
                }
            }
        }
        
        return p.substring(maxIndex - max, maxIndex);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(longestCommonSubstring(scanner.nextLine(), scanner.nextLine()));
        
        scanner.close();
    }
    
}
