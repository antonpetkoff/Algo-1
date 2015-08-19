package rand.set;

import java.util.Arrays;
import java.util.Random;

public class RandSet {

	public static class Node {
		public int key;
		public int auxIndex;
		public Node next;
		
		public Node(int k, int i, Node n) {
			key = k;
			auxIndex = i;
			next = n;
		}
		
		@Override
		public int hashCode() {
			return key ^ auxIndex;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj instanceof Node) {
				Node n = (Node) obj;
				return key == n.key && auxIndex == n.auxIndex;
			}
			return false;
		}
		
		@Override
		public String toString() {
        	StringBuilder sb = new StringBuilder();
        	Node temp = this;
        	while (temp != null) {
        		sb.append("{" + temp.key + ", [" + temp.auxIndex + "]} -> ");
        		temp = temp.next;
        	}
        	return sb.toString(); 
		}
	}
	
	public static final int INIT_CAPACITY = 16;
	public static final float LOAD_FACTOR = 0.75f;
	public static final float GROWTH_FACTOR = 2.0f;
	private static final Random rand = new Random();

	private Node[] bins;
	private int[] keys;
	private int capacity;
	private int size;
	
	public RandSet() {
    	capacity = INIT_CAPACITY;
    	size = 0;
    	bins = new Node[capacity];
    	keys = new int[capacity];
	}
	
	public int size() {
		return size;
	}
	
    public int[] keys() {
    	return Arrays.copyOf(keys, size);
    }
	
    public int hash(int key) {
        return Math.abs(key) % capacity;
    }
    
    public void put(int key) {
        if (size / (float) capacity >= LOAD_FACTOR) {
            resize(Math.round(capacity * GROWTH_FACTOR));
        }

        Node newNode = new Node(key, size, null);
        putAtIndex(bins, hash(key), newNode);
        keys[size++] = newNode.key;
    }
    
    public void resize(int newCapacity) {
    	int[] newKeys = new int[newCapacity];
    	for (int i = 0; i < size; ++i) {
    		newKeys[i] = keys[i];
    	}
    	keys = newKeys;
    	
        Node[] newBins = new Node[newCapacity];
    	capacity = newCapacity;

        for (int i = 0; i < bins.length; ++i) {
            Node node = bins[i], newNode = null;
            while (node != null) {
                newNode = new Node(node.key, node.auxIndex, null);
                putAtIndex(newBins, hash(node.key), newNode);
                node = node.next;
            }
        }

        bins = newBins;
    }
    
    /**
     * @param bins 		the array of Nodes in which to put newNode
     * @param index		the position in bins
     * @param newNode	the new node to be added to bins
     */
    public void putAtIndex(Node[] bins, int index, Node newNode) {
        Node node = bins[index];

        if (node != null) {
            Node prev = node, temp = prev.next;
            while (temp != null) {
            	prev = temp;
                temp = temp.next;
            }
            temp = newNode;
            prev.next = temp;
        } else {
            bins[index] = newNode;
        }
    }
    
    public int random() {
    	return keys[rand.nextInt(size)];
    }
    
    public boolean contains(int key) {
    	Node node = bins[hash(key)];
    	Integer result = null;
    	
    	if (node != null) {
    		Node temp = node;
    		while (temp != null && temp.key != key) {
    			temp = temp.next;
    		}
    		result = temp != null ? temp.key : null;
    	} else {
    		result = null;
    	}
    	
    	return result != null;
    }
	
}
