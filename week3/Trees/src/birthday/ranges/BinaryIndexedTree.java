package birthday.ranges;

public class BinaryIndexedTree {

    int[] tree;     // [1...N-1 | N...2N-1]
    int initialCapacity;
    
    /**
     * @param initialSize   initial capacity, guarantees a range [0, initialSize]
     * @param values        increment by one tree[values[i]] for i = 0 to i = values.length     
     */
    public BinaryIndexedTree(int initialCapacity, int[] values) {
        this.initialCapacity = initialCapacity;
        int size = (int) Math.pow(2, Math.ceil(Math.log(initialCapacity)/Math.log(2)));
        tree = new int[2*size];   // size = 2N, where N = 2^K
        
        initTree(values);
    }
    
    private void initTree(int[] values) {
        int i = 0;
        
        for (i = tree.length / 2; i < tree.length; ++i) {
            tree[i] = 0;
        }
        
        for (i = 0; i < values.length; ++i) {
            ++tree[tree.length / 2 + values[i]];    // N + i
        }

        for (i = tree.length / 2 - 1; i > 0; --i) {
            tree[i] = tree[left(i)] + tree[right(i)];
        }
    }
    
    public void printState() {
        for (int i = 0; i < tree.length; ++i) {
            System.out.println(tree[i]);
        }
    }
    
    public int left(int i) {
        return 2 * i;
    }
    
    public int right(int i) {
        return 2 * i + 1;
    }
    
    public int parent(int i) {
        return i / 2;
    }
    
    /**
     * @param bound     is inclusive!
     * @return          sum of [0, bound]
     */
    public int query(int bound) {
        if (bound < 0 || bound > initialCapacity - 1) {
            throw new IllegalArgumentException("Out of bounds!");
        }
        
        int sum = 0;
        int i = tree.length / 2 + bound + 1;
        while (i != 1) {        // while pointer != root
            if (i % 2 != 0) {   // is right child
                sum += tree[left(parent(i))];
            }
            i = parent(i);
        }
        
        return sum;
    }
    
    //public void update(int i)
    
    public static void main(String[] args) {
        int[] values = {0, 1, 1, 2, 4, 4, 4, 5, 5};
        BinaryIndexedTree bit = new BinaryIndexedTree(6, values);
        System.out.println(bit.query(5));
    }
    
}
