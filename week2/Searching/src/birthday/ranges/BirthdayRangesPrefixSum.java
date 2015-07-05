package birthday.ranges;

import java.util.Scanner;

public class BirthdayRangesPrefixSum {

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

    public static final int DAYS_COUNT = 365;

    public static int[] birthdaysCount(int[] birthdays, Tuple[] ranges) {
        int[] prefixSum = new int[DAYS_COUNT + 1]; // one-based array (so include 365)

        for (int i = 0; i < birthdays.length; ++i) {
            ++prefixSum[birthdays[i]];
        }
        prefixSum[0] = 0; // nobody is born on day 0 (one-based)

        for (int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i] += prefixSum[i - 1];
        }

        int[] result = new int[ranges.length];

        for (int i = 0; i < ranges.length; ++i) {
            result[i] = prefixSum[ranges[i].right] - prefixSum[ranges[i].left - 1];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleCount = scanner.nextInt(), rangesCount = scanner.nextInt();
        int[] birthdays = new int[peopleCount];
        Tuple[] ranges = new Tuple[rangesCount];

        for (int i = 0; i < peopleCount; ++i) {
            birthdays[i] = scanner.nextInt();
        }

        for (int i = 0; i < rangesCount; ++i) {
            ranges[i] = new Tuple(scanner.nextInt(), scanner.nextInt());
        }

        scanner.close();

        int[] result = birthdaysCount(birthdays, ranges);

        for (int i = 0; i < result.length; ++i) {
            System.out.println(result[i]);
        }
    }

}
