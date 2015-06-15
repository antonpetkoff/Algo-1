package utils;

import java.util.Random;

public class Utils {

    public static <T> void swap(final T[] array, final int a, final int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static <T extends Comparable<T>> boolean lessThan(T arr, T arr2) {
        return arr.compareTo(arr2) < 0;
    }
    
    public static <T> void shuffle(T[] array) {
        Random rand = new Random();
        
        for (int i = 0; i < array.length; ++i) {
            swap(array, i, i + rand.nextInt(array.length - i));
        }
    }
    
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        if (array.length < 2) {
            return true;
        }
        
        for (int i = 1; i < array.length; ++i) {
            if (array[i-1].compareTo(array[i]) > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static <T> void printArray(T[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }
    
}
