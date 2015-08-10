package kpaths;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class KPaths {

    public static final int SIZE = 1000;
    public static boolean[][] matrix = new boolean[SIZE][SIZE];
    
//    public static List<Integer> topologicalSort() {
//        boolean[] visited = new boolean[SIZE];
//        Stack<Integer> stack = new Stack<Integer>();
//        
//    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int edgesCount = scanner.nextInt();
        
        for (int i = 0; i < edgesCount; ++i) {
            matrix[scanner.nextInt()][scanner.nextInt()] = true;
        }
        
        
        scanner.close();
    }
    
}
