package rmq;

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
        System.out.println("i stops at: " + i);

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
    
    public void printState() {
        for (int i = 0; i < tree.length; ++i) {
            System.out.print(tree[i] + " ");
        }
    }
    
    public static void main(String[] args) {
        //int[] values = {19, 11, 15, 4, 7, 13, 11};
        int[] values = {19, 11, 15, 4, 7, 13, 11, 2, 3, 5, 12, 7, 23, 17, 4, 6};
        RMQ rmq = new RMQ(values);
        rmq.printState();
        System.out.println();
        
        System.out.println(rmq.min(5, 10));
    }
    
}
