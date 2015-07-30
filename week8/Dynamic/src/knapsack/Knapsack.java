package knapsack;

import java.util.Scanner;

public class Knapsack {

    public static int maxValueKnapsack(int capacity, int[] values, int[] weights) {
        final int N = values.length;
        int[][] price = new int[N + 1][capacity + 1];
        
        for (int i = 0; i < N + 1; ++i) {
            price[i][0] = 0;
        }

        for (int i = 0; i < capacity + 1; ++i) {
            price[0][i] = 0;
        }
        
        for (int n = 1; n < N + 1; ++n) {
            for (int w = 1; w < capacity + 1; ++w) {
                if (weights[n - 1] > w) {
                    price[n][w] = price[n - 1][w];
                } else {
                    price[n][w] = Math.max(price[n - 1][w],
                            price[n - 1][w - weights[n - 1]] + values[n - 1]);
                }
            }
        }
        
        return price[N][N];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // W = backpack weight capacity
        int N = scanner.nextInt(), W = scanner.nextInt();
        
        int[] weights = new int[N], values = new int[N];
        
        for (int i = 0; i < N; ++i) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }
        
        System.out.println(maxValueKnapsack(W, values, weights));
        
        scanner.close();
    }
    
}
