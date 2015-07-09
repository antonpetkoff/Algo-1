package power.supply;

import java.util.Scanner;

public class PowerSupply {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        
        Graph graph = new Graph();
        for (int i = 0; i < N; ++i) {
            graph.addEdge(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        MSTPrim prim = new MSTPrim(graph);
        //System.out.println(graph);
        //System.out.println(prim.minimumSpanningTree());
        System.out.println(prim.totalWeight());
        
        scanner.close();
    }
    
}
