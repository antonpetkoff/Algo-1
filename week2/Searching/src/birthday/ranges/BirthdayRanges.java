package birthday.ranges;

import java.util.Arrays;
import java.util.Scanner;

public class BirthdayRanges {

    public static final class Tuple {

        public final int left;
        public final int right;

        public Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "[" + left + "," + right + "]";
        }

    }

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
    public static Integer[] birthdaysCount(Integer[] birthdays, Tuple[] ranges) {
        Integer[] buckets = new Integer[ranges.length];
        int left = 0, right = 0;
        Arrays.sort(birthdays);

        for (int i = 0; i < ranges.length; ++i) {
            left = searchGreaterOrEquals(birthdays, ranges[i].left);
            right = searchGreaterOrEquals(birthdays, ranges[i].right);

            if (birthdays[right].compareTo(ranges[i].right) > 0) {
                --right;
            }

            if (right == left && !birthdays[left].equals(ranges[i].left) && !birthdays[right].equals(ranges[i].right)) {
                buckets[i] = 0;
            } else {
                buckets[i] = right - left + 1;
            }
        }

        return buckets;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleCount = scanner.nextInt(), rangesCount = scanner.nextInt();
        Integer[] birthdays = new Integer[peopleCount];
        Tuple[] ranges = new Tuple[rangesCount];

        for (int i = 0; i < peopleCount; ++i) {
            birthdays[i] = scanner.nextInt();
        }

        for (int i = 0; i < rangesCount; ++i) {
            ranges[i] = new Tuple(scanner.nextInt(), scanner.nextInt());
        }

        scanner.close();

        Integer[] result = birthdaysCount(birthdays, ranges);

        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

}
