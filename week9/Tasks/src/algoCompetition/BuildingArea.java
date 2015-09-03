package algoCompetition;

import java.util.Scanner;

public class BuildingArea {

    public static int maxLen = 0;
    public static int tempMax = 0;
    public static String[] rows;
    
    public static boolean isFree(int row, int col) {
        return rows[row].charAt(col) == 'O';
    }
    
    public static void findMax(int row, int col) {
        if (row + 1 == rows.length || col + 1 == rows.length) {
            return;
        }
        
        if (isFree(row + 1, col + 1) && isFree(row + 1, col) && isFree(row, col + 1)) {
            ++tempMax;
            findMax(row + 1, col + 1);
            findMax(row + 1, col);
            findMax(row, col + 1);
        }
    }
    
    public static int area() {
        for (int row = 0; row < rows.length; ++row) {
            for (int col = 0; col < rows[row].length(); ++col) {
                if (isFree(row, col)) {
                    findMax(row, col);
                    maxLen = Math.max(maxLen, tempMax);
                    tempMax = 0;
                }
            }
        }
        return maxLen;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        rows = new String[N];
        
        for (int i = 0; i < N; ++i) {
            rows[i] = scanner.nextLine();
        }
        
        System.out.println(area());
        
        scanner.close();
    }
    
}
