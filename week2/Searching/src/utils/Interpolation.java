package utils;

import java.util.Arrays;

public class Interpolation {
    
    public static final int NOT_FOUND = -1;

    public static int search(Integer[] arr, Integer key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        double k = 0.0;
        
        while (hi - lo >= 0) {
            // prevent division by zero
            if (arr[lo].equals(arr[hi])) {
                if (arr[lo].equals(key)) {
                    return lo;
                } else {
                    return NOT_FOUND;
                }
            }
            
            k = (key - arr[lo]) / (arr[hi] - arr[lo]);
            // guarantee index in bounds <=> k in [0, 1]
            if (k < 0.0 || k > 1.0) {
                return NOT_FOUND;
            }
            mid = (int) (lo + Math.round(k * (hi - lo)));
            
            if (arr[mid].compareTo(key) < 0) {
                lo = mid + 1;
            } else if (arr[mid].compareTo(key) > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        
        return NOT_FOUND;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = (Integer[]) Arrays.asList(1, 2, 3, 3, 3, 5, 5, 6).toArray();
        System.out.println(search(numbers, 6));
    }
    
}
