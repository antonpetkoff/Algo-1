package sorting;

import utilities.Utils;

public class Quick {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    
    public static <T extends Comparable<T>> void quickSort(T[] arr, int lo, int hi) {
        // pivot
        T pivot = arr[(lo + hi) / 2];
        
        // swaps
        int i = lo, j = hi;
        
        while (i <= j) {
            while (Utils.lessThan(arr[i], pivot)) {
                ++i;
            }
            
            while (Utils.lessThan(pivot, arr[j])) {
                --j;
            }
            
            if (i <= j) {
                Utils.swap(arr, i, j);
                ++i;
                --j;
            }
        }
        
        // recur
        if (i < hi) {
            quickSort(arr, i, hi);
        }
        if (lo < j) {
            quickSort(arr, lo, j);
        }
    }
    
}
