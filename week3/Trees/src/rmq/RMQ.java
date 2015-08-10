package rmq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RMQ {
	
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

    public int[] tree; // [1...N-1 | N...2N-1]

    /**
     * @param initialSize
     * @param values    initial values
     */
    public RMQ(int[] values) {
        int size = (int) Math.pow(2, Math.ceil(Math.log(values.length) / Math.log(2)));
        tree = new int[2 * size]; // size = 2N, where N = 2^K

        initTree(values);
    }

    private void initTree(int[] values) {
        int i = 0;

        for (i = 0; i < values.length; ++i) {
            tree[tree.length / 2 + i] = values[i];
        }

        for (int j = i; j < tree.length / 2; ++j) {
            tree[tree.length / 2 + j] = Integer.MAX_VALUE;
        }

        for (i = tree.length / 2 - 1; i > 0; --i) {
            tree[i] = Math.min(tree[left(i)], tree[right(i)]);
        }
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return i / 2;
    }
    
    public int min(int l, int r) {
        l += tree.length / 2;
        r += tree.length / 2;
        
        int lMin = tree[l], rMin = tree[r], temp = 0;
        
        while (l < r) {
            if (r - l == 1) {
            	break;
            }
        	
            // move left pointer one level up and to the right
            if (l % 2 == 0) {   // left pointer is left child
                l = parent(l);
                lMin = Math.min(lMin, tree[l]);
            } else {            // left pointer is right child
                temp = Math.min(lMin, tree[l]);
                l = parent(l + 1);
                lMin = Math.min(temp, tree[l]);
            }
            
            // move right pointer one level up and to the left
            if (r % 2 == 0) {   // right pointer is left child
            	temp = Math.min(rMin, tree[r]);
                r = parent(r - 1);
                rMin = Math.min(temp, tree[r]);
            } else {            // right pointer is right child
                r = parent(r);
                rMin = Math.min(rMin, tree[r]);
            }
        }
        
        return Math.min(lMin, rMin);
    }
    
    public void set(int pos, int value) {
        int pointer = tree.length / 2 + pos;
        tree[pointer] = value;
        pointer = parent(pointer);

        while (pointer != 0) {
            tree[pointer] = Math.min(tree[left(pointer)], tree[right(pointer)]);
            pointer = parent(pointer);
        }
    }
    
    public void printState() {
        for (int i = 0; i < tree.length; ++i) {
        	if (i % (tree.length / 2) == 0) {
        		System.out.print("| ");
        	}
        	
        	if (tree[i] == Integer.MAX_VALUE) {
        		System.out.print("INF ");
        	}
        	else {
        		System.out.print(tree[i] + " ");
        	}
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
    	MyScanner scanner = new MyScanner();
        int N = scanner.nextInt(), K = scanner.nextInt();

        int[] values = new int[N];
        for (int i = 0; i < values.length; i++) {
			values[i] = scanner.nextInt();
		}
        RMQ rmq = new RMQ(values);

        String[] command = null;
        for (int i = 0; i < K; i++) {
			command = scanner.nextLine().split("\\s+");
			if (command[0].equals("min")) {
				System.out.println(rmq.min(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
			} else if (command[0].equals("set")) {
				rmq.set(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
			}
		}
    }
    
}
