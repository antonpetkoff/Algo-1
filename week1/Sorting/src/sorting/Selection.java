package sorting;

import utilities.Utils;

public class Selection {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int min = 0;
        
        for (int i = 0; i < arr.length - 1; ++i) {
            min = i;
            
            for (int j = i + 1; j < arr.length; ++j) {
                if (Utils.lessThan(arr[j], arr[min])) {
                    min = j;
                }
            }
            
            if (min != i) {
                Utils.swap(arr, min, i);
            }
        }
    }
    
}
