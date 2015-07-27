package change;

import java.util.Scanner;

public class Change {

    public static final int[] COINS = {1, 2, 5, 10, 20, 50, 100};
    
    public static int combinationsCount(int sum) {
        int[] sums = new int[sum + 1];
        sums[0] = 1;
        
        for (int c = 0; c < COINS.length; ++c) {
            for (int s = 0; s <= sum; ++s) {
                if (s + COINS[c] < sums.length) {
                    sums[s + COINS[c]] += sums[s];
                }
            }
        }

        return sums[sum];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(combinationsCount(scanner.nextInt()));
        scanner.close();
    }
    
}
