package hash.table;

public class HashTable<K, V> {

    public static class Entry<K, V> {
        public K key;
        public V value;
        public Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        @Override
        public int hashCode() {
        	return key.hashCode() ^ value.hashCode();
        }
        
        @Override
        public boolean equals(Object obj) {
        	if (this == obj) {
        		return true;
        	}
        	if (obj instanceof Entry) {
				Entry<?, ?> entry = (Entry<?, ?>) obj;
				return key.equals(entry.key) && value.equals(entry.value);
			}
        	return false;
        }
        
        @Override
        public String toString() {
        	StringBuilder sb = new StringBuilder();
        	Entry<K, V> temp = this;
        	while (temp != null) {
        		sb.append("{" + temp.key + "=" + temp.value + "} -> ");
        		temp = temp.next;
        	}
        	return sb.toString();
        }
    }

    public static final int INIT_CAPACITY = 16;
    public static final float LOAD_FACTOR = 0.75f;
    public static final float GROWTH_FACTOR = 2.0f;

    private Entry<K, V>[] table;
    private int capacity;
    private int size;


    @SuppressWarnings("unchecked")
	public HashTable() {
    	capacity = INIT_CAPACITY;
    	size = 0;
    	table = (Entry<K, V>[]) new Entry[capacity];
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (size / (float) capacity >= LOAD_FACTOR) {
            resize(Math.round(capacity * GROWTH_FACTOR));
        }

        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
        putAtIndex(table, indexFor(key), newEntry);
        ++size;
    }

    public void resize(int newCapacity) {
    	@SuppressWarnings("unchecked")
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];
        capacity = newCapacity;

        for (int i = 0; i < table.length; ++i) {
            Entry<K, V> entry = table[i], newEntry = null;
            while (entry != null) {
                newEntry = new Entry<K, V>(entry.key, entry.value, null);
                putAtIndex(newTable, indexFor(entry.key), newEntry);
                entry = entry.next;
            }
        }

        table = newTable;
    }

    public int indexFor(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void putAtIndex(Entry<K, V>[] table, int index, Entry<K, V> newEntry) {
        Entry<K, V> entry = table[index], prev = entry;

        if (prev != null) {
            Entry<K, V> temp = prev.next;
            while (temp != null) {
                temp = temp.next;
            }
            temp = newEntry;
            prev.next = temp;
        } else {
            table[index] = newEntry;
        }
    }

    public V get(K key) {
    	Entry<K, V> entry = table[indexFor(key)];
    	V result = null;
    	
    	if (entry != null) {
    		Entry<K, V> temp = entry;
    		while (temp != null && !temp.key.equals(key)) {
    			temp = temp.next;
    		}
    		result = temp != null ? temp.value : null;
    	} else {
    		result = null;
    	}
    	
    	return result;
    }
    
    public boolean contains(K key) {
    	return get(key) != null;
    }
    
    // XXX: TODO
    public void remove(K key) {
    	int index = indexFor(key);
    	Entry<K, V> entry = table[index];
    	System.out.println("Removing: " + key);
		System.out.println("Before removal: " + table[index]);
    	
    	if (entry == null) {		// empty bin
    		System.out.println("hit empty bin");
    		return;
    	}

    	if (entry.next == null) {	// only one node
			table[index] = null;
		} else {					// two or more nodes

			
			Entry<K, V> temp = entry, previous = null;
			while (temp != null && !temp.key.equals(key)) {
				previous = temp;
				temp = temp.next;
			}
			
			if (previous == null) {	// temp hasn't moved because entry.key is the same as key
				previous = entry;
			}
			
			Entry<K, V> replacement = previous.next;
			previous.key = replacement.key;
			previous.value = replacement.value;
			previous.next = replacement.next;
			
		}
    	
    	--size;
    	System.out.println("After removal: " + table[index]);
    }

}
