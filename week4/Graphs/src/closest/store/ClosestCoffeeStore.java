package closest.store;

import java.util.LinkedList;
import java.util.Queue;

public class ClosestCoffeeStore {

    public static final int SIZE = 6;

    public static final boolean[][] graph = { { false, true, false, true, false, false },
            { true, false, true, false, false, false }, { false, true, false, false, true, false },
            { true, false, false, false, false, false }, { false, false, true, false, false, true },
            { false, false, false, false, true, false }, };

    public static final boolean[] isCoffeeStore = { false, false, true, false, false, true };

    // Finds the closest coffee store to a point.
    public static int closestCoffeeStore(boolean[][] graph, boolean[] isCoffeeStore, int startingPoint) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[SIZE];
                
//        int[] previous = new int[SIZE];     // previous[i] = parent(i);
//        for (int i = 0; i < previous.length; ++i) {
//            previous[i] = -1;
//        }
//        int[] dist = new int[SIZE];         // dist[i] = dist(i, startingPoint);
//        dist[startingPoint] = 0;
        
        queue.add(startingPoint);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            visited[vertex] = true;

            for (int i = 0; i < SIZE; ++i) {
                if (graph[vertex][i] == true && visited[i] == false) {
                    if (isCoffeeStore[i]) {                        
                        return i;
                    }
                    queue.add(i);
                    
                    //dist[i] = dist[vertex] + 1;
                    //previous[i] = vertex;
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        int result = closestCoffeeStore(graph, isCoffeeStore, 0);
        System.out.println(result);
    }
}