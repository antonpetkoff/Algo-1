package build.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BuildScripts {

    private static List<List<String>> adjacencyList;
    private static Map<String, Integer> indexOf;
    private static String[] vertices;
    private static boolean[] visited;
    private static String target;
    //private static final String BUILD_ERROR = "BUILD ERROR";
    
    private static Stack<String> sorted;
    
    public static void init() {
        Scanner scanner = new Scanner(System.in);
        int vertexCount = scanner.nextInt();
        
        adjacencyList = new ArrayList<List<String>>(vertexCount);
        indexOf = new HashMap<String, Integer>(vertexCount);
        vertices = new String[vertexCount];
        visited = new boolean[vertexCount];
        
        for (int i = 0; i < vertexCount; ++i) {
            String temp = scanner.next();
            adjacencyList.add(new ArrayList<String>());
            indexOf.put(temp, i);
            vertices[i] = temp;
            visited[i] = false;
        }
        
        target = scanner.next();
        
        for (int i = 0; i < vertexCount; ++i) {
            int N = scanner.nextInt();
            for(int j = 0; j < N; ++j) {
                adjacencyList.get(i).add(scanner.next());
            }
        }
        
        System.out.println(target);
        for (int i = 0; i < vertexCount; ++i) {
            System.out.println(adjacencyList.get(i));
        }
        
        System.out.println(indexOf);
        
        scanner.close();
    }
    
    public static String topologicalSort(String target) {
        sorted = new Stack<String>();
        
        visitDFS(target);
        
//        for (int i = 0; i < visited.length; ++i) {
//            if (!visited[i]) {
//                boolean hasCycle = visitDFS(vertices[i]);
//                if (hasCycle) {
//                    return BUILD_ERROR;
//                }
//            }
//        }
        
        StringBuilder sb = new StringBuilder();
        while (!sorted.isEmpty()) {
            sb.append(sorted.pop() + " ");
        }
        return sb.toString().trim();
    }
    
    public static boolean visitDFS(String vertex) {
        Stack<String> stack = new Stack<String>();
        boolean[] inPath = new boolean[vertices.length];
        
        stack.push(vertex);
        
        while (!stack.isEmpty()) {
            String x = stack.pop();
            sorted.push(x);
            System.out.println("sorted.push: " + x);
            inPath[indexOf.get(x)] = true;
            
            List<String> neighbours = adjacencyList.get(indexOf.get(x));
            for (int i = 0; i < neighbours.size(); ++i) {
                String neighbour = neighbours.get(i);
                
                // TODO fix cycles
//                if (inPath[indexOf.get(neighbour)]) {
//                    return true;    // cycle detected
//                }
                
                if (!visited[indexOf.get(neighbour)]) {
                    stack.push(neighbour);
                    visited[indexOf.get(neighbour)] = true;
                }
            }
            
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        init();
        String sorted = topologicalSort(target);
        System.out.println('\n' + sorted);
    }
    
}
