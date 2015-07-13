package vitosha.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import navigation.Heap;

public class VitoshaRun {
    
    public static final class Vertex implements Comparable<Vertex> {
        private final int x;
        private final int y;
        
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return String.format("%d %d", x, y);
        }

        @Override
        public int compareTo(Vertex o) {
            return dist[this.x][this.y] - dist[o.x][o.y];
        }
    }

    public static int[][] matrix;
    public static int[][] dist;
    public static Heap<Vertex> queue = new Heap<Vertex>();
    
    public static final int INF = 100_000_000;
    
    public static void dijkstra(int startRow, int startCol) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                dist[row][col] = INF;
                queue.insert(new Vertex(row, col));
            }
        }
        dist[startRow][startCol] = 0;
        
        int compoundWeight = 0, edgeWeight = 0;
        while (queue.size() != 0) {
            Vertex v = queue.remove();
            
            for (Vertex u : getNeghbouringVertices(v)) {
                edgeWeight = 1 + Math.abs(matrix[v.x][v.y] - matrix[u.x][u.y]);
                compoundWeight = dist[v.x][v.y] + edgeWeight;
                if (compoundWeight < dist[u.x][u.y]) {
                    dist[u.x][u.y] = compoundWeight;
                    queue.insert(new Vertex(u.x, u.y));
                }
            }
        }
        
    }
    
    public static List<Vertex> getNeghbouringVertices(Vertex v) {
        List<Vertex> neighbours = new ArrayList<Vertex>();
        for (int row = v.x - 1; row < v.x + 2; row++) {
            for (int col = v.y - 1; col < v.y + 2; col++) {
                if (row >= 0 && row < matrix.length && col >= 0 && col < matrix.length
                        && row != v.x && col != v.y) {
                    neighbours.add(new Vertex(row, col));
                }
            }
        }
        return neighbours;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt(), startRow = scanner.nextInt(), startCol = scanner.nextInt(),
                endRow = scanner.nextInt(), endCol = scanner.nextInt();

        dist = new int[vertexCount][vertexCount];
        matrix = new int[vertexCount][vertexCount];
        for (int row = 0; row < vertexCount; row++) {
            for (int col = 0; col < vertexCount; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        
        dijkstra(startRow, startCol);
        
        System.out.println(dist[endRow][endCol]);
        
        scanner.close();
    }

}
