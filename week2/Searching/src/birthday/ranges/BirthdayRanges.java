package birthday.ranges;

import java.util.Arrays;

public class BirthdayRanges {

    /**
     * @param arr
     * @param key
     * @return the leftmost index of key
     */
    public static <T extends Comparable<T>> int searchGreaterOrEquals(T[] arr, T key) {
        int lo = 0, hi = arr.length - 1, mid = 0;

        while (hi - lo >= 0) {
            mid = lo + (hi - lo) / 2;
            if (arr[mid].compareTo(key) < 0) {
                lo = mid + 1;
            } else {
                if (hi - lo == 0) {
                    return mid;
                }
                hi = mid;
            }
        }

        return mid;
    }

    /**
     * @param birthdays
     * @param ranges
     * @return list of integers where for each I in the list I equals the number
     *         of people with birthdays in the range ranges[i].
     */
    public static Integer[] birthdaysCount(Integer[] birthdays, Range[] ranges) {
        Integer[] buckets = new Integer[ranges.length];
        int left = 0, right = 0;
        Arrays.sort(birthdays);

        for (int i = 0; i < ranges.length; ++i) {
            left = searchGreaterOrEquals(birthdays, ranges[i].left);
            right = searchGreaterOrEquals(birthdays, ranges[i].right);

            if (birthdays[right].compareTo(ranges[i].right) > 0) {
                --right;
            }

            if (right == left && !birthdays[left].equals(ranges[i].left)
                    && !birthdays[right].equals(ranges[i].right)) {
                buckets[i] = 0;
            } else {
                buckets[i] = right - left + 1;
            }
        }

        return buckets;
    }

}
