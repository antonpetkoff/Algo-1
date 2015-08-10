package klists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class KLists {

    public static class Heap<T extends Comparable<T>> {

        public static final int ROOT_BOUND = -1;
        
        private List<T> heap;
        private int size;
        
        public Heap() {
            this.heap = new ArrayList<T>(1);
            this.size = 0;
        }
        
        public int parent(int i) {
            return i == 0 ? ROOT_BOUND : (i - 1) / 2;
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
            
            if (heap.get(i).compareTo(heap.get(parent(i))) < 0) {
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
        
        public void heapify(List<T> elements, int size) {
            this.heap = new ArrayList<T>();
            this.size = size;
            
            for (int i = 0; i < size; ++i) {
                this.heap.add(elements.get(i));
            }
            
            for (int i = size - 1; i >= 0; --i) {
                siftDown(i);
            }
        }
        
        public void siftDown(int p) {
            int childIndex = left(p);
            int minIndex = p;
            
            // find smallest child
            for (int i = 0; i < 2; ++i) {
                if (childIndex + i < size) {
                    if (heap.get(childIndex + i).compareTo(heap.get(minIndex)) < 0) {
                        minIndex = childIndex + i;
                    }
                }
            }
            
            if (minIndex != p) {    // if smaller child exists
                Collections.swap(heap, minIndex, p);
                siftDown(minIndex);
            }
        }
        
        public T getMin() {
            if (size == 0) {
                throw new IllegalStateException("No elements in heap!");
            }
            return heap.get(0);
        }
        
        public T extractMin() {
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
                arr.set(i, extractMin());
            }
        }
    }
    
    public static class Tuple<L extends Comparable<L>, R> implements Comparable<Tuple<L, R>> {

        public L left;
        public R right;

        public Tuple(L left, R right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Tuple<L, R> o) {
            return this.left.compareTo(o.left);
        }

    }

    public static class Node implements Comparable<Node> {
        public int value;
        public Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    // Merge K sorted lists.
    public static Node merge(List<Node> lists) {
        Heap<Tuple<Node, Integer>> heap = new Heap<Tuple<Node, Integer>>();
        Node head = new Node(0, null), result = head;
        int length = 0;

        // initialize heap and accumulate length
        for (int i = 0; i < lists.size(); ++i) {
            heap.insert(new Tuple<Node, Integer>(lists.get(i), i));

            Node temp = lists.get(i);
            while (temp != null) {
                ++length;
                temp = temp.next;
            }
        }

        Node node = null;
        int index = 0;
        for (int i = 0; i < length; ++i) {
            node = heap.getMin().left;
            index = heap.extractMin().right;

            head.next = node;
            head = head.next;

            if (lists.get(index).next != null) {
                lists.set(index, lists.get(index).next);
                heap.insert(new Tuple<Node, Integer>(lists.get(index), index));
            }
        }

        return result.next;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt(), tempInt = 0;
        List<Node> lists = new ArrayList<Node>(K);
        Node tempNode = null;
        
        for (int i = 0; i < K; ++i) {
            lists.add(new Node(scanner.nextInt(), null));
            tempNode = lists.get(i);
            
            while (-1 != (tempInt = scanner.nextInt())) {
                tempNode.next = new Node(tempInt, null);
                tempNode = tempNode.next;
            }
        }
        
        Node merged = merge(lists);
        
        StringBuilder sb = new StringBuilder();
        while (merged != null) {
            sb.append(merged.value + " ");
            merged = merged.next;
        }
        System.out.println(sb.toString().trim());
        
        scanner.close();
    }

}
