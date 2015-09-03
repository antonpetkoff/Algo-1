package algoCompetition;

import java.util.Scanner;

public class DamWall {

    public static int damWall(int[][] map, int water) {
        int maxHeight = map[0][0], N = map.length;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        
        int capacity = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                water -= map[i][j];
                capacity += maxHeight - map[i][j];
            }
        }
        
        int result = 0;
        if (water > 0) {
            while (water > 0) {
                ++maxHeight;
                water -= N * N;
            }
            result = maxHeight + 1;
        } else {
            result = maxHeight + 1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt(), W = scanner.nextInt();
        int[][] map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                map[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println(damWall(map, W));
        
        scanner.close();
    }
    
}
