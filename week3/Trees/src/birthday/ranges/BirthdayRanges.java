package birthday.ranges;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BirthdayRanges {

	public static class BinaryIndexedTree {

	    int[] tree; // [1...N-1 | N...2N-1]
	    int initialCapacity;

	    /**
	     * @param initialSize
	     *            initial capacity, guarantees a range [0, initialSize]
	     * @param values
	     *            increment by one tree[values[i]] for i = 0 to i =
	     *            values.length
	     */
	    public BinaryIndexedTree(int initialCapacity, int[] values) {
	        this.initialCapacity = initialCapacity;
	        int size = (int) Math.pow(2, Math.ceil(Math.log(initialCapacity) / Math.log(2)));
	        tree = new int[2 * size]; // size = 2N, where N = 2^K for K in the Naturals

	        initTree(values);
	    }

	    private void initTree(int[] values) {
	        int i = 0;

	        for (i = 0; i < values.length; ++i) {
	            ++tree[tree.length / 2 + values[i]]; // N + i
	        }

	        for (i = tree.length / 2 - 1; i > 0; --i) {
	            tree[i] = tree[left(i)] + tree[right(i)];
	        }
	    }
	    
	    public int get(int index) {
	    	return tree[tree.length / 2 + index];
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

	    /**
	     * @param bound
	     *            is inclusive!
	     * @return sum of [0, bound]
	     */
	    public int query(int bound) {
	        boundsCheck(bound);

	        if (bound == tree.length / 2 - 1) {
	            return tree[tree.length - 1];
	        }
	        
	        int sum = 0;
	        int i = tree.length / 2 + 1 + bound;
	        while (i != 0) { // while pointer != root
	            if (i % 2 != 0) { // is right child
	                sum += tree[left(parent(i))];
	            }
	            i = parent(i);
	        }

	        return sum;
	    }

	    public void update(int pos, int value) {
	        boundsCheck(pos);

	        int pointer = tree.length / 2 + pos;
	        tree[pointer] = tree[pointer] + value < 0 ? 0 : tree[pointer] + value;
	        pointer = parent(pointer);

	        while (pointer != 0) { // pointer != root
	        	tree[pointer] = tree[left(pointer)] + tree[right(pointer)];
	        	pointer = parent(pointer);
	        }
	    }

	    private void boundsCheck(int bound) {
	        if (bound < 0 || bound > tree.length - 1) {
	            throw new IllegalArgumentException("Out of bounds!");
	        }
	    }

	    @Override
	    public String toString() {
	    	StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < tree.length; ++i) {
	        	if (i % (tree.length / 2) == 0) {
	        		sb.append("| ");
	        	}
	    		sb.append(tree[i] + " ");
	        }
	        return sb.toString();
	    }
	    
	}
	
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
	
	public static PrintWriter out;
	
    private BinaryIndexedTree bit;
    
    public BinaryIndexedTree getBit() {
    	return bit;
    }
    
    public BirthdayRanges(int[] values) {
        bit = new BinaryIndexedTree(365, values);
    }
    
    // adds people who are born on a specific day
    public void add(int day, int numberOfPeople) {
        bit.update(day, numberOfPeople);
    }

    // removes people who are born on a specific day
    public void remove(int day, int numberOfPeople) {
        bit.update(day, -numberOfPeople);
    }

    // returns the number of people born in a range
    public int count(int startDay, int endDay) {
        int a = bit.query(startDay), b = bit.query(endDay);
        return b - a + bit.get(startDay);
    }
    
    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
    
        int daysCount = scanner.nextInt(), commandsCount = scanner.nextInt();
        int[] values = new int[daysCount];
        
        for (int i = 0; i < daysCount; i++) {
            values[i] = scanner.nextInt();
        }
        
        BirthdayRanges br = new BirthdayRanges(values);
        
        String command = null;
        for (int i = 0; i < commandsCount; ++i) {
            command = scanner.next();
            if (command.equals("count")) {
                out.println(br.count(scanner.nextInt(), scanner.nextInt()));
            } else if (command.equals("add")) {
                br.add(scanner.nextInt(), scanner.nextInt());
            } else if (command.equals("remove")) {
                br.remove(scanner.nextInt(), scanner.nextInt());
            }
        }
        
        out.close();
    }

}
