package kintersects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KIntersects {

	public static List<Integer> intersect(List<Integer> a, List<Integer> b) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> intersection = new LinkedList<>();
		
		for (Integer item : a) {
			map.put(item, 0);
		}
		for (Integer item : b) {
			if (map.containsKey(item)) {
				intersection.add(item);
			}
		}
		
		return intersection;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt(), M = 0;
		List<List<Integer>> lists = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			M = scanner.nextInt();
			lists.add(new ArrayList<>());
			for (int j = 0; j < M; ++j) {
				lists.get(i).add(scanner.nextInt());
			}
		}
		
		List<Integer> result = null;
		for (int i = 0; i < lists.size() - 1; ++i) {
			result = intersect(lists.get(i), lists.get(i + 1));
			lists.set(i + 1, result);
		}
		
		for (Integer item : lists.get(lists.size() - 1)) {
			System.out.println(item);
		}
		
		scanner.close();
	}
	
}
