package online.median;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Median {

    public static class Heap<T> {

        public static final int ROOT_BOUND = -1;

        private List<T> heap;
        private int size;
        private Comparator<T> comp;

        public Heap(Comparator<T> comp) {
            this.heap = new ArrayList<T>(1);
            this.size = 0;
            this.comp = comp;
        }

        private int parent(int i) {
            return i == 0 ? ROOT_BOUND : i / 2;
        }

        private int left(int i) {
            return 2 * i + 1;
        }

        public int size() {
            return size;
        }

        private void siftUp(int i) {
            if (parent(i) == ROOT_BOUND) {
                return;
            }

            if (comp.compare(heap.get(i), heap.get(parent(i))) < 0) {
                Collections.swap(heap, i, parent(i));
                siftUp(parent(i));
            }
        }

        public void insert(T elem) {
            if (size == heap.size()) {
                heap.add(elem);
            }
            heap.set(size, elem);
            siftUp(size);
            ++size;
        }

        private void heapify(List<T> elements, int size) {
            this.heap = new ArrayList<T>();
            this.size = size;

            for (int i = 0; i < size; ++i) {
                this.heap.add(elements.get(i));
            }

            for (int i = size - 1; i >= 0; --i) {
                siftDown(i);
            }
        }

        private void siftDown(int p) {
            int childIndex = left(p);
            int minIndex = p;

            // find smallest child
            for (int i = 0; i < 2; ++i) {
                if (childIndex + i < size) {
                    if (comp.compare(heap.get(childIndex + i), heap.get(minIndex)) < 0) {
                        minIndex = childIndex + i;
                    }
                }
            }

            if (minIndex != p) { // if smaller child exists
                Collections.swap(heap, minIndex, p);
                siftDown(minIndex);
            }
        }

        public T peek() {
            if (size == 0) {
                throw new IllegalStateException("No elements in heap!");
            }
            return heap.get(0);
        }

        public T remove() {
            if (size == 0) {
                throw new IllegalStateException("No elements in heap!");
            }

            T min = heap.get(0);
            heap.set(0, heap.get(size - 1));
            --size;
            siftDown(0);
            return min;
        }

        public void sort(List<T> arr) {
            heapify(arr, arr.size());

            for (int i = 0; i < arr.size(); ++i) {
                arr.set(i, remove());
            }
        }

        @Override
        public String toString() {
            return heap.toString();
        }

    }

    private Heap<Integer> maxHeap;
    private Heap<Integer> minHeap;
    private Integer median;
    private int balance;        // -1 maxHeap is bigger, 0 equal, 1 minHeap is biggers

    public Median() {
        maxHeap = new Heap<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        minHeap = new Heap<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        median = null;
        balance = 0;
    }

    // inserts the number and returns the median
    public int insert(int number) {
        if (median == null) {
            minHeap.insert(number);
            balance = 1;
            median = minHeap.peek();
            return median;
        }
        
        switch (balance) {
            case 1:
                if (number <= median) {     // median stays the same
                    maxHeap.insert(number);
                } else {
                    minHeap.insert(number);
                    maxHeap.insert(minHeap.remove());
                    median = minHeap.peek();
                }
                balance = 0;
                break;
            case 0:
                if (number < median) {
                    maxHeap.insert(number);
                    balance = -1;
                    median = maxHeap.peek();
                } else {
                    minHeap.insert(number);
                    balance = 1;
                    median = minHeap.peek();
                }
                break;
            case -1:
                if (number <= median) {
                    maxHeap.insert(number);
                    minHeap.insert(maxHeap.remove());
                } else {     // median stays the same
                    minHeap.insert(number);
                }
                balance = 0;
                median = minHeap.peek();
                break;
        }
        return median;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] result = new int[count];

        Median median = new Median();

        for (int i = 0; i < count; ++i) {
            result[i] = median.insert(scanner.nextInt());
        }

        scanner.close();

        for (int i = 0; i < count; ++i) {
            System.out.println(result[i]);
        }
    }

}
