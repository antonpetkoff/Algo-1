package sorting;

import utilities.Utils;

public class Merge {

    @SuppressWarnings("rawtypes")
    private static Comparable[] aux;
    
    public static <T extends Comparable<T>> void sort(T[] arr) {
        aux = new Comparable[arr.length];
        mergeSort(arr, 0, arr.length - 1);
    }
 
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        
        int mid = (lo + hi) / 2;
        
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;

        for (k = lo; k <= hi; ++k) {
            aux[k] = arr[k];
        }
        
        for (k = lo; k <= hi; ++k) {
            if (i > mid) {
                arr[k] = (T) aux[j++];
            } else if (j > hi) {
                arr[k] = (T) aux[i++];
            } else if (Utils.lessThan(aux[i], aux[j])) {
                arr[k] = (T) aux[i++];
            } else {
                arr[k] = (T) aux[j++];
            }
        }
    }
    
    
}
