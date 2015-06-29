package valid.directories;

import java.util.LinkedList;
import java.util.Queue;

public class ValidDirectories {

    public static final int SIZE = 6;

    public static final int FOLDER = 1;
    public static final int FILE = 2;

    public static final int[][] graph = { { 0, 1, 0, 1, 0, 2 }, { 0, 0, 2, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 2, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

    public static boolean[] visited = new boolean[SIZE];

    public static boolean isValid(int[][] graph) {

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

    public static boolean BFSValidation(int[][] graph, int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] inPath = new boolean[SIZE];

        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            visited[vertex] = true;
            inPath[vertex] = true;

            for (int i = 0; i < SIZE; ++i) {
                if (graph[vertex][i] == FOLDER) {
                    if (visited[i]) {
                        return false;
                    }

                    if (!inPath[i]) {
                        queue.add(i);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = isValid(graph);
        System.out.println(result);
    }

}