package hash.table;

import java.util.ArrayList;

public class HashTable<T> {
    
    private static class Entry<E> {
        public E value;
        public Entry<E> next;
        
        public Entry(E value, Entry<E> next) {
            this.value = value;
            this.next = next;
        }
        
        @Override
        public int hashCode() {
            return value.hashCode();
        }
        
    }

    private static final int INIT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private static final float GROWTH_FACTOR = 2.0f;
    
    private ArrayList<Entry<T>> buckets;
    private int capacity;
    private int size;
    
    public HashTable() {
        buckets = new ArrayList<Entry<T>>();
        capacity = INIT_CAPACITY;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public void put(T elem) {
        if (size / capacity >= LOAD_FACTOR) {
            resize(Math.round(capacity * GROWTH_FACTOR));
        }
        
        putAtIndex(buckets, indexFor(elem), elem);
        ++size;
    }
    
    public void resize(int newCapacity) {
        ArrayList<HashTable.Entry<T>> newBuckets = new ArrayList<HashTable.Entry<T>>(newCapacity);
        capacity = newCapacity;
        
        for (int i = 0; i < buckets.size(); ++i) {
            HashTable.Entry<T> entry = buckets.get(i);
            while (entry != null) {
                putAtIndex(newBuckets, indexFor(entry.value), entry.value);
                entry.value = null;     // avoid loitering
                entry = entry.next;
            }
        }
        
        buckets = newBuckets;
    }
    
    public int indexFor(T elem) {
        return Math.abs(elem.hashCode()) % capacity;
    }
    
    public void putAtIndex(ArrayList<HashTable.Entry<T>> buckets, int index, T elem) {
        HashTable.Entry<T> entry = buckets.get(index), prev = entry;
        
        if (prev != null) {
            HashTable.Entry<T> temp = prev.next;
            while (temp != null) {
                temp = temp.next;
            }
            temp = new HashTable.Entry<T>(elem, null);
            prev.next = temp;
        } else {
            buckets.set(index, new HashTable.Entry<T>(elem, null));
        }
    }
    
}
