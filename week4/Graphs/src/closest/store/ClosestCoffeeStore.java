package closest.store;

import java.util.LinkedList;
import java.util.Scanner;

public class ClosestCoffeeStore {

    public static int closestCoffeeStore(int[][] graph, int[] isCoffeeStore, int startingPoint) {
    	LinkedList<Integer> currentLevel = new LinkedList<Integer>(),
    			nextLevel = new LinkedList<Integer>();
        boolean[] visited = new boolean[graph.length];
        int level = 1;
                
        currentLevel.addLast(startingPoint);

        while (!currentLevel.isEmpty()) {
            int vertex = currentLevel.removeFirst();
            visited[vertex] = true;

            for (int i = 0; i < graph.length; ++i) {
                if (graph[vertex][i] == 1 && !visited[i]) {
                    if (isCoffeeStore[i] == 1) {                        
                        return level;
                    }
                    nextLevel.add(i);
                }
            }
            
            if (currentLevel.isEmpty()) {
            	LinkedList<Integer> tempQueue = currentLevel;
            	currentLevel = nextLevel;
            	nextLevel = tempQueue;
            	++level;
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