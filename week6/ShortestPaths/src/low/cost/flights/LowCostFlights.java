package low.cost.flights;

import java.util.Scanner;

public class LowCostFlights {

    public static int matrix[][];

    public static int INF = -1;

    public static void initMatrix() {
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[row].length; ++col) {
                if (row == col) {
                    matrix[row][col] = 0;
                } else {
                    matrix[row][col] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public static void floydWarshallShortestPaths() {
        for (int k = 0; k < matrix.length; ++k) {
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix.length; ++j) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF) {
                        if (matrix[i][j] != INF && matrix[i][j] > matrix[i][k] + matrix[k][j] || matrix[i][j] == INF) {
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        matrix = new int[N][N];

        // initialize matrix for Floyd-Warshall
        int input = 0;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                input = scanner.nextInt();
                if (input == 0) {
                    if (row == col) {
                        matrix[row][col] = 0; // vertex to itself
                    } else {
                        matrix[row][col] = INF; // no direct flight
                    }
                } else {
                    matrix[row][col] = input; // preserve weight
                }
            }
        }

        floydWarshallShortestPaths();

        int M = scanner.nextInt(), start = 0, end = 0;
        for (int i = 0; i < M; ++i) {
            start = scanner.nextInt();
            end = scanner.nextInt();
            System.out.println(matrix[start][end] == INF ? "NO WAY" : matrix[start][end]);
        }

        scanner.close();
    }

}
