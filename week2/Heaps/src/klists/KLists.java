package klists;

import heap.sort.Heap;

import java.util.Arrays;
import java.util.List;

public class KLists {

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
        Node a = new Node(3, new Node(5, new Node(7, new Node(9, null))));
        Node b = new Node(2, new Node(4, new Node(6, null)));
        Node c = new Node(0, new Node(1, new Node(8, new Node(10, null))));
        List<Node> lists = Arrays.asList(a, b, c);
        Node merged = merge(lists);
        
        while (merged != null) {
            System.out.println(merged.value);
            merged = merged.next;
        }
    }
}
