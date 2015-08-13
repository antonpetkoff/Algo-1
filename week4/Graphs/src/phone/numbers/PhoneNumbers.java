package phone.numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PhoneNumbers {

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
	
	/**
	 * This disjoint set works with integers in the range [1; MAX]
	 */
	public static class DisjointSet {
		
		public static final int MAX = 1000;
		private int[] set;
		private int[] rank;
		private int componentsCount;
		
		/**
		 * @param vertices	at most MAX distinct integers
		 */
		public DisjointSet(int[] vertices) {
			set = new int[MAX + 1];		// one-based
			rank = new int[MAX + 1];	// one-based
			componentsCount = vertices.length;
			
			for (int i = 0; i < vertices.length; ++i) {
				set[vertices[i]] = vertices[i];
				rank[vertices[i]] = 1;
			}
		}
		
		public int getComponentsCount() {
			return componentsCount;
		}
		
		// path compression
		public int find(int vertex) {
			if (set[vertex] != vertex) {
				set[vertex] = find(set[vertex]);
			}
			return set[vertex];
		}
		
		// union by rank
		public void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);
			
			if (xRoot == yRoot) {
				return;
			}
			
			// append the smaller tree to the root of the larger tree
			if (rank[xRoot] < rank[yRoot]) {
				set[xRoot] = yRoot;
			} else if (rank[xRoot] > rank[yRoot]) {
				set[yRoot] = xRoot;
			} else {
				set[xRoot] = yRoot;
				++rank[yRoot];
			}
			
			--componentsCount;
		}
		
	}
	
	public static void main(String[] args) {
		MyScanner scanner = new MyScanner();
		
		int vertexCount = scanner.nextInt();
		int[] vertices = new int[vertexCount];
		
		for (int i = 0; i < vertexCount; ++i) {
			vertices[i] = scanner.nextInt();
		}
		
		DisjointSet set = new DisjointSet(vertices);
		
		int M = 0;
		for (int i = 0; i < vertexCount; ++i) {
			M = scanner.nextInt();
			for (int j = 0; j < M; ++j) {
				set.union(vertices[i], scanner.nextInt());
			}
		}
		
		System.out.println(set.getComponentsCount());
	}
	
}
