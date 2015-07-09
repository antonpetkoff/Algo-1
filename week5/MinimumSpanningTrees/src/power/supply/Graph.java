package power.supply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  Weighted graph - adjacency list
 */
public class Graph {

    private Map<Integer, List<Edge>> adjacencyList;
    private int edgeCount;
    
    public Graph() {
        adjacencyList = new HashMap<Integer, List<Edge>>();
        edgeCount = 0;
    }
    
    public int vertexCount() {
        return adjacencyList.size();
    }
    
    public int edgeCount() {
        return edgeCount;
    }
    
    public List<Edge> getEdgesFrom(int vertex) {
        return adjacencyList.get(vertex);
    }
    
    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }
    
    public Map<Integer, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
    
    public void addEdge(Edge edge) {
        int vertex1 = edge.either(), vertex2 = edge.other(vertex1);
        
        addToAdjacencyList(vertex1, edge);
        addToAdjacencyList(vertex2, edge);
        ++edgeCount;
    }
    
    private void addToAdjacencyList(int vertex, Edge edge) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<Edge>());
        }
        adjacencyList.get(vertex).add(edge);
    }
    
    @Override
    public String toString() {
        return adjacencyList.toString();
    }
}
