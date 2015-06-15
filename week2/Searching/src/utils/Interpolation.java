package utils;

public class Interpolation {
    
    public static final int NOT_FOUND = -1;

    public static int search(int[] arr, int key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        double k = 0.0;
        
        while (hi - lo >= 0) {
            // prevent division by zero
            if (arr[lo] == arr[hi]) {
                if (arr[lo] == key) {
                    return lo;
                } else {
                    return NOT_FOUND;
                }
            }
            
            k = (key - arr[mid])*(arr[hi] - arr[lo]);
            // guarantee index in bounds <=> k in [0, 1]
            if (k < 0.0 || k > 1.0) {
                return NOT_FOUND;
            }
            mid = (int) (lo + Math.round(k * (hi - lo)));
            
            // decide new bounds
            // TODO
        }
        
        return NOT_FOUND;
    }
    
}
