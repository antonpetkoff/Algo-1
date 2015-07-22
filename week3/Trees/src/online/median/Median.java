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
        private Comparator<T> comp;
        
        public Heap(Comparator<T> comp) {
            this.heap = new ArrayList<T>(1);
            this.comp = comp;
        }
        
        private int parent(int i) {
            return i == 0 ? ROOT_BOUND : (i - 1) / 2;
        }
        
        private int left(int i) {
            return 2 * i + 1;
        }
            
        public int size() {
            return heap.size();
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
            heap.add(elem);
            siftUp(heap.size() - 1);
        }
        
        private void heapify(List<T> elements, int size) {
            this.heap = new ArrayList<T>();
            
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
            for (int i = 0; i <= 1; ++i) {
                if (childIndex + i < size()) {
                    if (comp.compare(heap.get(childIndex + i), heap.get(minIndex)) < 0) {
                        minIndex = childIndex + i;
                    }
                }
            }
            
            if (minIndex != p) {    // if smaller child exists
                Collections.swap(heap, minIndex, p);
                siftDown(minIndex);
            }
        }
        
        public T peek() {
            if (size() == 0) {
                throw new IllegalStateException("No elements in heap!");
            }
            return heap.get(0);
        }
        
        public T remove() {
            if (size() == 0) {
                throw new IllegalStateException("No elements in heap!");
            }
            
            T peek = heap.get(0);
            Collections.swap(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0);
            return peek;
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
    private static StringBuilder output = new StringBuilder();

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
    }

    // inserts the number and returns the median
    public int insert(int number) {        
        if (median == null) {
            median = number;
            minHeap.insert(number);
            output.append(median + "\n");
            return median;
        }

        if (number >= median) {
            if (minHeap.size() - 1 == maxHeap.size()) {
                minHeap.insert(number);
                maxHeap.insert(minHeap.remove());
            } else {
                minHeap.insert(number);
            }
        } else {
            if (maxHeap.size() == minHeap.size()) {
                if (maxHeap.peek() < number) {
                    minHeap.insert(number);
                } else {
                    maxHeap.insert(number);
                    minHeap.insert(maxHeap.remove());
                }
            } else { // median stays the same
                maxHeap.insert(number);
            }
        }

        median = minHeap.peek();
        output.append(median + "\n");
        return median;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Median median = new Median();

        for (int i = 0; i < count; ++i) {
            median.insert(scanner.nextInt());
        }

        scanner.close();
        System.out.println(output.toString().trim());
    }

}
