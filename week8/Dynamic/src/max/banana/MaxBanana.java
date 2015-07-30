package max.banana;

import java.util.Scanner;

public class MaxBanana {

    public static int maxBanana(int[][] values) {
        int left = 0, under = 0;
        for (int row = values.length - 1; row >= 0; --row) {
            for (int col = 0; col < values[row].length; ++col) {
                if (row + 1 < values.length) {
                    under = values[row + 1][col];
                }
                if (col - 1 >= 0) {
                    left = values[row][col - 1];
                }
                values[row][col] += Math.max(left, under);
                left = under = 0;
            }
        }
        
        return values[0][values.length - 1];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int size = scanner.nextInt();
        int values[][] = new int[size][size];
        
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                values[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println(maxBanana(values));
        
        scanner.close();
    }
    
}
