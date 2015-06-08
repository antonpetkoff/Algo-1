package stack;

import queue.Queue;

public class Stack<T> implements StackInterface<T> {

    private Queue<T> queue;
    private Queue<T> aux;
    
    public Stack() {
        queue = new Queue<T>();
        aux = new Queue<T>();
    }
    
    @Override
    public void push(T value) {
        queue.push(value);
    }

    @Override
    public T pop() {
        if (queue.size() == 0) {
            throw new IllegalStateException("No elements to pop!");
        }
        
        while (queue.size() - 1 != 0) {
            aux.push(queue.pop());
        }
        T result = queue.pop();
        
        Queue<T> temp = queue;
        queue = aux;
        aux = temp;
        
        return result;
    }

    @Override
    public T peek() {
        if (queue.size() == 0) {
            throw new IllegalStateException("No elements to pop!");
        }
        
        while (queue.size() - 1 != 0) {
            aux.push(queue.pop());
        }
        
        T result = queue.pop();
        queue.push(result);
        
        Queue<T> temp = queue;
        queue = aux;
        aux = temp;
        
        return result;
    }

    @Override
    public int size() {
        return queue.size();
    }
    
}
