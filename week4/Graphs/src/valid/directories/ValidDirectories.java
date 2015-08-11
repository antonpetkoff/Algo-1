package valid.directories;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class ValidDirectories {

    public static final int FOLDER = 1;
    public static final int FILE = 2;
    
    public static final int WHITE = 0;
    public static final int GREY = 1;
    public static final int BLACK = 2;

    public static boolean[] visited;

    public static boolean isValid(int[][] graph) {
    	visited = new boolean[graph.length];
    	
        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i]) {
        		boolean isValid = BFSValidation(graph, i);
        		if (!isValid) {
        			return false;
        		}
            }
        }

        return true;
    }
    
    public static boolean isRoot(int[][] graph, int v) {
    	for (int i = 0; i < graph.length; ++i) {
    		if (graph[i][v] != 0) {
    			return false;
    		}
    	}
    	return true;
    }

    public static boolean dfsColoringValidation(int[][] graph, int start) {
    	Stack<Integer> stack = new Stack<>();
    	int[] state = new int[graph.length];
    	stack.push(start);
    	
    	while (!stack.isEmpty()) {
    		int vertex = stack.pop();
    		state[vertex] = BLACK;
    		
    		for (int i = 0; i < graph.length; ++i) {
    			if (graph[vertex][i] == FOLDER) {
    				if (state[i] == BLACK) {
    					return false;
    				} else if (state[i] == WHITE) {
    					state[i] = GREY;
    					stack.push(i);
    				}
    			}
    		}
    	}
    	
    	return true;
    }
    
    public static boolean BFSValidation(int[][] graph, int start) {
    	LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] inPath = new boolean[graph.length];

        queue.addLast(start);

        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            visited[vertex] = true;
//            inPath[vertex] = true;

            for (int i = 0; i < graph.length; ++i) {
                if (graph[vertex][i] == FOLDER) {
                	if (inPath[i]) {
                		return false;
                	}
                	
                	inPath[i] = true;
                	queue.addLast(i);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
//    	Scanner scanner = new Scanner(System.in);
//    	
//    	int N = scanner.nextInt();
//    	int[][] graph = new int[N][N];
//    	
//    	for (int i = 0; i < N; ++i) {
//    		for (int j = 0; j < N; j++) {
//				graph[i][j] = scanner.nextInt();
//			}
//    	}
//    	
//    	scanner.close();
    	int[][] graph = new int[6][6];
//    	graph[0][1] = 1;
//    	graph[0][2] = 1;
//    	graph[1][3] = 1;
//    	graph[2][3] = 1;
//    	graph[4][5] = 1;
    	
    	graph[0][1] = 1;
    	graph[0][3] = 1;
    	graph[4][0] = 1;
    	
    	System.out.println(isValid(graph));
    }

}