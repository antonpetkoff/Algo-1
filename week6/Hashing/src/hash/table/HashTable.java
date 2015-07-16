package hash.table;

import java.util.Arrays;
import java.util.List;

public class HashTable {

    private static class Entry {
        public Integer key;
        public Integer value;
        public Entry next;

        public Entry(Integer key, Integer value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int INIT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private static final float GROWTH_FACTOR = 2.0f;

    private List<Entry> buckets;
    private int capacity;
    private int size;

    public HashTable() {
        buckets = Arrays.asList(new Entry[INIT_CAPACITY]);
        capacity = INIT_CAPACITY;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void put(Integer key, Integer value) {
        if (size / capacity >= LOAD_FACTOR) {
            resize(Math.round(capacity * GROWTH_FACTOR));
        }

        Entry newEntry = new Entry(key, value, null);
        putAtIndex(buckets, indexFor(key), newEntry);
        ++size;
    }

    public void resize(int newCapacity) {
        List<Entry> newBuckets = Arrays.asList(new Entry[newCapacity]);
        capacity = newCapacity;

        for (int i = 0; i < buckets.size(); ++i) {
            Entry entry = buckets.get(i), newEntry = null;
            while (entry != null) {
                newEntry = new Entry(entry.key, entry.value, null);
                putAtIndex(newBuckets, indexFor(entry.key), newEntry);
                entry = entry.next;
            }
        }

        buckets = newBuckets;
    }

    public int indexFor(Integer elem) {
        return Math.abs(elem.hashCode()) % capacity;
    }

    public void putAtIndex(List<Entry> buckets, int index, Entry newEntry) {
        Entry entry = buckets.get(index), prev = entry;

        if (prev != null) {
            Entry temp = prev.next;
            while (temp != null) {
                temp = temp.next;
            }
            temp = newEntry;
            prev.next = temp;
        } else {
            buckets.set(index, newEntry);
        }
    }

    public boolean contains(Integer elem) {
        return buckets.get(indexFor(elem)) != null;
    }

}
