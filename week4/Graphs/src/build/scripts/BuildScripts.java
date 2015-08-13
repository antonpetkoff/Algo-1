package build.scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class BuildScripts {
	
	public static class MyScanner {
		BufferedReader br;
		StringTokenizer st;

		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

	}

	public static final int WHITE = 0;
	public static final int GREY = 1;
	public static final int BLACK = 2;
	public static final String BUILD_ERROR = "BUILD ERROR";
	
	public static LinkedList<String> sorted;
	public static Map<String, Integer> visited;
	
	public static LinkedList<String> topoSort(Map<String, LinkedList<String>> graph, String start) {
		sorted = new LinkedList<>();
		visited = new HashMap<>();
		for (String node : graph.keySet()) {
			visited.put(node, WHITE);
		}
		
		// the general case is (order doesn't matter) > for each unvisited node: visit(node)
		if (!visit(start, graph)) {	// if the graph is a DAG
			return new LinkedList<String>(Arrays.asList(BUILD_ERROR));
		}
		
		return sorted;
	}
	
	/**
	 * @param 	node
	 * @return	false if the graph is not a DAG, true if it is a DAG
	 */
	public static boolean visit(String node, Map<String, LinkedList<String>> graph) {
		if (visited.get(node) == GREY) {
			return false;	// not a DAG!
		}
		if (visited.get(node) == WHITE) {
			visited.put(node, GREY);
			for (String neighbour : graph.get(node)) {
				if(!visit(neighbour, graph)) {	// if the graph is a DAG
					return false;
				}
			}
			visited.put(node, BLACK);
			sorted.addLast(node);
		}
		return true;
	}
	
	public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        int vertexCount = scanner.nextInt();
        
        String[] vertices = new String[vertexCount];
        Map<String, LinkedList<String>> graph = new HashMap<>();
        
        for (int i = 0; i < vertexCount; ++i) {
            String vertex = scanner.next();
            graph.put(vertex, new LinkedList<String>());
            vertices[i] = vertex;
        }
        
        String target = scanner.next();
        
        for (int i = 0; i < vertexCount; ++i) {
            int N = scanner.nextInt();
            for(int j = 0; j < N; ++j) {
                graph.get(vertices[i]).add(scanner.next());
            }
        }
        
        String sorted = topoSort(graph, target).toString();
        sorted = sorted.substring(1, sorted.length() - 1);
        System.out.println(sorted.replaceAll(",\\s+", " ").trim());
	}
	
}
