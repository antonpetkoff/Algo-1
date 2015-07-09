package power.supply;

import java.util.LinkedList;
import java.util.List;
import power.supply.Heap;

public class MSTPrim {

    private Graph graph;
    private int totalWeight;
    private boolean[] visited;      // mark when popped from queue
    private List<Edge> mst;   // resulting MST
    private Heap<Edge> priority;
    
    public MSTPrim(Graph g) {
        graph = g;
        totalWeight = 0;
        visited = new boolean[graph.vertexCount() + 1];
        mst = new LinkedList<Edge>();
        priority = new Heap<Edge>();
        
        visit(graph.getVertices().iterator().next());
        
        while (priority.size() != 0) {
            Edge minEdge = priority.remove();
            
            int vertex1 = minEdge.either(), vertex2 = minEdge.other(vertex1);
            
            if (visited[vertex1] && visited[vertex2]) {
                continue;
            }
            
            mst.add(minEdge);
            totalWeight += minEdge.weight();
            
            if (!visited[vertex1]) {
                visit(vertex1);
            }
            
            if (!visited[vertex2]) {
                visit(vertex2);
            }
        }
        
    }
    
    private void visit(int vertex) {
        visited[vertex] = true;
        for (Edge edge : graph.getEdgesFrom(vertex)) {
            if (!visited[edge.other(vertex)]) {
                priority.insert(edge);
            }
        }
    }

    public int totalWeight() {
        return totalWeight;
    }
    
    public List<Edge> mst() {
        return mst;
    }
    
}
