package birthday.ranges;

public class BinaryIndexedTree {

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
        tree = new int[2 * size]; // size = 2N, where N = 2^K

        initTree(values);
    }

    private void initTree(int[] values) {
        int i = 0;

        for (i = tree.length / 2; i < tree.length; ++i) {
            tree[i] = 0;
        }

        for (i = 0; i < values.length; ++i) {
            ++tree[tree.length / 2 + values[i]]; // N + i
        }

        for (i = tree.length / 2 - 1; i > 0; --i) {
            tree[i] = tree[left(i)] + tree[right(i)];
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

        while (pointer != 0) { // pointer != root
            tree[pointer] += value;
            pointer = parent(pointer);
        }
    }

    private void boundsCheck(int bound) {
        if (bound < 0 || bound > tree.length - 1) {
            throw new IllegalArgumentException("Out of bounds!");
        }
    }

}
