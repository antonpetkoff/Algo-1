package sorting;

import java.util.Arrays;
import static utilities.Utils.*;

public class Counting {

    /**
     * Counting sort assumes that each of the n input elements is an integer in the range
     * 0 to k, for some integer k.
     * @param arr
     */
    public static <T extends Comparable<T>> void sort(Integer[] arr) {
        int N = arr.length, K = 1 + (int) Arrays.stream(arr).max((a, b) -> a.compareTo(b)).get();
        Integer[] buckets = new Integer[K];
        Integer[] sorted = new Integer[N];
        
        for (int i = 0; i < K; ++i) {
            buckets[i] = 0;
        }
        
        for (int i = 0; i < N; ++i) {
            ++buckets[arr[i]];
        }
        
        for (int i = 1; i < K; ++i) {
            buckets[i] += buckets[i - 1];
        }
        
        for (int i = N - 1; i >= 0; --i) {      // make stable
            sorted[buckets[arr[i]] - 1] = arr[i];
            --buckets[arr[i]];
        }
        
        for (int i = 0; i < N; ++i) {
            arr[i] = sorted[i];
        }
    }
    
    public static void main(String[] args) {
        Integer[] numbers = (Integer[]) Arrays.asList(1, 4, 65, 3, 65, 23, 98).toArray();
        sort(numbers);
        printArray(numbers);
    }
    
}
