package heap.sort;

/**
 *  Min-Heap
 */
public class Heap {

    public static final int ROOT_BOUND = -1;
    
    private int[] heap;
    private int size;
    
    public Heap() {
        this.heap = null;
        this.size = 0;
    }
    
    public int parent(int i) {
        return i == 0 ? ROOT_BOUND : i / 2;
    }
    
    public int left(int i) {
        return 2 * i + 1;
    }
    
    public int right(int i) {
        return 2 * i + 2;
    }
    
    public int size() {
        return size;
    }
    
    public void siftUp(int i) {
        if (parent(i) == ROOT_BOUND) {
            return;
        }
        
        if (heap[i] < heap[parent(i)]) {
            swap(heap, i, parent(i));
            siftUp(parent(i));
        }
    }
    
    public void insert(int elem) {
        heap[size] = elem;
        siftUp(size);
        ++size;
    }
    
    public void heapify(int[] elements, int size) {
        this.heap = new int[size];
        this.size = 0;
        
        for (int i = 0; i < size; ++i) {
            insert(elements[i]);
        }
    }
    
    public void siftDown(int p) {
        int childIndex = left(p);
        int minIndex = p;
        
        // find smallest child
        for (int i = 0; i < 2; ++i) {
            if (childIndex + i < size) {
                if (heap[childIndex + i] < heap[minIndex]) {
                    minIndex = childIndex + i;
                }
            }
        }
        
        if (minIndex != p) {    // if smaller child exists
            swap(heap, minIndex, p);
            siftDown(minIndex);
        }
    }
    
    public int getMin() {
        if (size == 0) {
            throw new IllegalStateException("No elements in heap!");
        }
        return heap[0];
    }
    
    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("No elements in heap!");
        }
        
        int min = heap[0];
        heap[0] = heap[size - 1];
        --size;
        siftDown(0);
        return min;
    }
    
    public void sort(int[] arr) {
        heapify(arr, arr.length);
        
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = extractMin();
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {8, 5, 4, 0, 3, 4, 2, 1};
        (new Heap()).sort(arr);
        
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
    }
    
}
