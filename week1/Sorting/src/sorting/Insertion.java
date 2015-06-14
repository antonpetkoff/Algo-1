package sorting;

import static utilities.Utils.*;

public class Insertion {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int j = i;
            T hand = arr[j];
            while (j > 0 && lessThan(hand, arr[j - 1])) {
                arr[j] = arr[j - 1];
                --j;
            }
            arr[j] = hand;
        }
    }

}
