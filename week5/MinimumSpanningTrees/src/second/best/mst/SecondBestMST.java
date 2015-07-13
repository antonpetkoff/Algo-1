package second.best.mst;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import power.supply.Edge;
import power.supply.Graph;
import power.supply.MSTPrim;

public class SecondBestMST {

    public static int maxEdgeWeightInCicyle(Edge edge) {
        int start = edge.either(), end = edge.other(start);
        // TODO
        return 0;
    }
    
    public static int secondBestWeight(Graph graph) {
        MSTPrim best = new MSTPrim(graph);
        
        Set<Edge> treeComplement = new TreeSet<Edge>();
        for (Integer vertex : graph.getVertices()) {
            treeComplement.addAll(graph.getEdgesFrom(vertex));
        }
        treeComplement.removeAll(best.mst());
        
        int minDiff = 0;
        for (Edge edge : treeComplement) {
            // find cycle from edge.first to edge.second
            // calculate minDiff = edge.weight - maxEdgeFromCycleWeight
        }
        
        return best.totalWeight() + minDiff;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        
        Graph graph = new Graph();
        for (int i = 0; i < N; ++i) {
            graph.addEdge(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        System.out.println(secondBestWeight(graph));
        
        scanner.close();
    }
    
}
