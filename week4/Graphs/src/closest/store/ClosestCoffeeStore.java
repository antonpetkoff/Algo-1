package closest.store;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ClosestCoffeeStore {

    // Finds the closest coffee store to a point.
    public static int closestCoffeeStore(int[][] graph, int[] isCoffeeStore, int startingPoint) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[graph.length];
                
        int[] dist = new int[graph.length];         // dist[i] = dist(i, startingPoint);
        dist[startingPoint] = 0;
        
        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            visited[vertex] = true;

            for (int i = 0; i < graph.length; ++i) {
                if (graph[vertex][i] == 1 && !visited[i]) {
                	dist[i] = dist[vertex] + 1;
                    if (isCoffeeStore[i] == 1) {                        
                        return dist[i];
                    }
                    queue.add(i);
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	int N = scanner.nextInt();
    	
    	int[][] graph = new int[N][N];
    	
    	for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = scanner.nextInt();
			}
		}
    	
    	int startingPoint = scanner.nextInt();
    	
    	int[] isCoffeeStore = new int[N];
    	for (int i = 0; i < graph.length; ++i) {
    		isCoffeeStore[i] = scanner.nextInt();
    	}
    	
    	System.out.println(closestCoffeeStore(graph, isCoffeeStore, startingPoint));
    	
    	scanner.close();
    }
    
}