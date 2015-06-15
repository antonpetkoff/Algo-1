package birthday.ranges;

import java.util.Arrays;

import static utils.Utils.*;

public class BirthdayRanges {

    /**
     * @param   arr
     * @param   key
     * @return  the leftmost index of key
     */
    public static <T extends Comparable<T>> int searchGreaterOrEquals(T[] arr, T key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        
        while (hi - lo >= 0) {
            mid = lo + (hi - lo)/2;
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
    
    // return rightmost index of key
    public static <T extends Comparable<T>> int searchLessOrEquals(T[] arr, T key) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        
        while (hi - lo >= 0) {
            mid = (hi - lo == 1) ? (1 +lo + (hi - lo)/2) : (lo + (hi - lo)/2); 
            
            if (arr[mid].compareTo(key) <= 0) {
                lo = mid;
                if (hi - lo == 0) {
                    return mid;
                }
            } else {
                hi = mid - 1;
            }
        }
        
        return mid;
    }
    
    /**
     * @param   birthdays
     * @param   ranges
     * @return  list of integers where for each I in the list I equals the
     * number of people with birthdays in the range ranges[i].
     */
    public static Integer[] birthdaysCount(Integer[] birthdays, Range[] ranges) {
        Integer[] buckets = new Integer[ranges.length];
        int left = 0, right = 0;
        Arrays.sort(birthdays);
        
        for (int i = 0; i < ranges.length; ++i) {
            left = searchGreaterOrEquals(birthdays, ranges[i].left);
            right = searchLessOrEquals(birthdays, ranges[i].right);
            
            buckets[i] = right - left + 1;
            
            if (right == left && !birthdays[left].equals(ranges[i].left)
                    && !birthdays[right].equals(ranges[i].right)) {
                buckets[i] = 0;
            }
        }
        
        return buckets;
    }
    
    public static void main(String[] args) {
        Integer[] birthdays = (Integer[]) Arrays.asList(1, 2, 3, 3, 6, 25, 52).toArray();
        Range[] ranges = new Range[] {new Range(2, 7), new Range(2, 80), new Range(0, 0), new Range(150, 160)};
        Integer[] counts = birthdaysCount(birthdays, ranges);
        
        printArray(counts);
        System.out.println();
    }
    
}
