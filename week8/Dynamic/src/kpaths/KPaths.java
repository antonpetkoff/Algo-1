package kpaths;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KPaths {

    public static final int WHITE = 0;
    public static final int GREY = 1;
    public static final int BLACK = 2;
    public static final int SIZE = 1000;
    
    public static int[] visited;
    public static LinkedList<Integer> sorted;
    
    public static LinkedList<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        visited = new int[graph.size()];
        sorted = new LinkedList<>();
        
        for (Integer node : graph.keySet()) {
            if (visited[node] == WHITE) {
                visit(graph, node);
            }
        }
        
        return sorted;
    }
    
    public static void visit(Map<Integer, List<Integer>> graph, int node) {
        if (visited[node] == GREY) {
            return;
        }
        
        if (visited[node] == WHITE) {
            visited[node] = GREY;
            
            for (Integer neighbour : graph.keySet()) {
                if (visited[neighbour] == WHITE) {
                    visit(graph, neighbour);
                }
            }
            
            visited[node] = BLACK;
            sorted.addFirst(node);
        }
    }
    
    public static void putIfAbsent(Map<Integer, List<Integer>> map, int key, int value) {
        if (!map.containsKey(key)) {
            map.put(key, new LinkedList<Integer>());
        }
        map.get(key).add(value);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int edgesCount = scanner.nextInt();
        Map<Integer, List<Integer>> children = new HashMap<>(),
                parents = new HashMap<>();
        
        int p, q = 0;
        for (int i = 0; i < edgesCount; ++i) {
            p = scanner.nextInt();
            q = scanner.nextInt();
            putIfAbsent(children, p, q);
            putIfAbsent(parents, q, p);
        }
        
        int start = scanner.nextInt(), end = scanner.nextInt(), length = scanner.nextInt();
        LinkedList<Integer> result = topologicalSort(children);
        System.out.println(result);
        
        scanner.close();
    }
    
}
