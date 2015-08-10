package castaway;

import java.util.Scanner;

public class Castaway {

    protected static char[][] matrix;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int rows = scanner.nextInt(), cols = scanner.nextInt();
//        int Sx = scanner.nextInt(), Sy = scanner.nextInt(), Fx = scanner.nextInt(), Fy = scanner.nextInt();
        matrix = new char[rows][cols];
        
        String temp = null;
        for (int row = 0; row < rows; row++) {
            temp = scanner.nextLine();
            for (int col = 0; col < temp.length(); ++col) {
                matrix[row][col] = temp.charAt(col);
            }
        }
        
        
        scanner.close();
    }
    
}
