package utils;

import java.util.Arrays;
import java.util.Random;

import static utils.Utils.*;

public class TestGenerator {

    public static Random rand = new Random();
    
    public static Integer[] generateSortedArrayLinear(int n) {
        Integer[] sorted = new Integer[n];
        int rangeLength = Integer.MAX_VALUE / n;
        
        sorted[0] = rand.nextInt(rangeLength);
        for (int i = 1; i < n; ++i) {
            sorted[i] = sorted[i - 1] + rand.nextInt(rangeLength);
        }
        
        return sorted;
    }
    
    public static Integer[] generateSortedArray(int n) {
        Integer[] sorted = new Integer[n];
        
        for (int i = 0; i < n; ++i) {
            sorted[i] = rand.nextInt();
        }
        
        Arrays.sort(sorted);
        
        return sorted;
    }
    
    public static int linearSearch(Integer[] arr, Integer key) {
        for (int i = 0; i < arr.length; ++i) {
            if ()
        }
    }
    
    public static void main(String[] args) {
        printArray(generateSortedArray(10));
    }
    
}
