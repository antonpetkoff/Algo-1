package utils;

import static utils.Utils.*;

import java.util.Arrays;

public class Binary {

    public static final int NOT_FOUND = -1;
    
    /**
     * @param   arr
     * @param   key
     * @return  the leftmost index of key
     */
    public static <T extends Comparable<T>> int search(T[] arr, T key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        
        while (hi - lo >= 0) {
            mid = lo + (hi - lo)/2;
            if (lessThan(arr[mid], key)) {
                lo = mid + 1;
            } else {
                if (hi - lo == 0) {
                    return arr[mid].equals(key) ? mid : NOT_FOUND;
                }
                hi = mid;
            }
        }
        
        return NOT_FOUND;
    }
    
    public static void main(String[] args) {
        Integer[] numbers = (Integer[]) Arrays.asList(1, 2, 3, 3, 3, 5, 5, 6).toArray();
        System.out.println(search(numbers, 5));
    }
    
}
