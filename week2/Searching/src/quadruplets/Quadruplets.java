package quadruplets;

import java.util.Arrays;

public class Quadruplets {
    
    public static int[] min(int[] a, int[] b) {
        return a.length < b.length ? a : b;
    }
    
    /**
     * lame O(N^3*logN)
     * @return  the number of quadruplets that sum to zero.
     */
    public static int zeroQuadrupletsCount(int[] a, int[] b, int[] c, int[] d) {
        //int[] min = min(a, min(b, min(c, d)));

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
    
}
