package queue;

public class Queue<T> implements QueueInterface<T> {

    static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        
        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public Queue() {
        head = tail = null;
        size = 0;
    }
    
    @Override
    public void push(T value) {
        Node<T> newNode = new Node<T>(value, null, null);

        newNode.prev = tail;
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;            
        }
        tail = newNode;
        ++size;
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("No elements to pop!");
        }
        
        Node<T> temp = head;
        if (head == tail) {             // only 1 element
            head = tail = null;
        } else {                        // 2 or more elements
            head = head.next;
        }
        temp.prev = temp.next = null;   // avoid loitering
        --size;
        return temp.item;
    }

    @Override
    public T peek() {
        if (size > 0) {
            return tail.item;
        }
        
        throw new IllegalStateException("No elements to peek!");
    }

    @Override
    public int size() {
        return size;
    }

}
