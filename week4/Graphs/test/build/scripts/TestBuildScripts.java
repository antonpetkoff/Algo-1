package build.scripts;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import static build.scripts.BuildScripts.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestBuildScripts {

	public static Random rand = new Random();
	
	public static Map<String, LinkedList<String>> generateRandomDAG(int vertexCount, double densityProb) {
		Map<String, LinkedList<String>> graph = new HashMap<>();
		
		for (int i = 0; i < vertexCount; ++i) {
			graph.put(String.valueOf(i), new LinkedList<>());
		}
		
		for (int i = 0; i < vertexCount; ++i) {
			for (int j = i + 1; j < vertexCount; ++j) {
				if (rand.nextDouble() < densityProb) {
					graph.get(String.valueOf(i)).add(String.valueOf(j));
				}
			}
		}
		
		return graph;
	}
	
	@Test
	public void testTopologicalSortOnDAG() {
		Map<String, LinkedList<String>> graph = generateRandomDAG(10, 0.5);

		for (String node : graph.keySet()) {
			assertFalse(BUILD_ERROR.equals(topologicalSort(graph, node).get(0)));
		}
	}
	
}
