package quadruplets;

import java.util.Arrays;
import java.util.Scanner;

public class Quadruplets {

    public static final int LIST_COUNT = 4;
    
    public static int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
        Arrays.sort(d);
        int count = 0;
        
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                for (int k = 0; k < c.length; ++k) {
                    if (Arrays.binarySearch(d, - (a[i] + b[j] + c[k])) >= 0) {
                        ++count;
                    }
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int size = scanner.nextInt();
        
        int[][] lists = new int[LIST_COUNT][size];
        
        for (int i = 0; i < LIST_COUNT; ++i) {
            for (int j = 0; j < size; ++j) {
                lists[i][j] = scanner.nextInt();
            }
        }
        
        int result = zeroQuadrupletsCount(lists[0], lists[1], lists[2], lists[3]);
        
        System.out.println(result);
        
        scanner.close();
    }
    
}
