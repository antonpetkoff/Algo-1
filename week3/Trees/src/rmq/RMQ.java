package rmq;

import java.util.Scanner;

public class RMQ {

    int[] tree; // [1...N-1 | N...2N-1]

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
            if (l % 2 == 0) {   // left pointer is left child
                l = parent(l);
                lMin = Math.min(lMin, tree[l]);
            } else {            // left pointer is right child
                temp = tree[l];
                l = parent(l + 1);
                lMin = Math.min(temp, tree[l]);
            }
            
            if (r % 2 == 0) {   // right pointer is left child
                temp = tree[r];
                r = parent(r - 1);
                rMin = Math.min(temp, tree[r]);
            } else {            // right pointer is right child
                r = parent(r);
                rMin = Math.min(rMin, tree[r]);
            }
        }
        
        return lMin;
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
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), K = scanner.nextInt();

        int[] values = new int[N];
        for (int i = 0; i < values.length; i++) {
			values[i] = scanner.nextInt();
		}
        RMQ rmq = new RMQ(values);
        System.out.print("Initial state: ");
        rmq.printState();
        
        String[] command = null;
        for (int i = 0; i < K; i++) {
			command = scanner.nextLine().split("\\s+");
			if (command[0].equals("min")) {
				System.out.println(rmq.min(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
			} else if (command[0].equals("set")) {
				rmq.set(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
				System.out.print("State after set " + Integer.parseInt(command[1]) + " " + Integer.parseInt(command[2]) + ": ");
				rmq.printState();
			}
		}
    	
        scanner.close();
    }
    
}
