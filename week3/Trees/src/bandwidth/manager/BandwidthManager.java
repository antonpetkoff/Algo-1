package bandwidth.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BandwidthManager {

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
	
    public enum PRIORITIES {
        ICMP, UDP, RTM, IGMP, DNS, TCP
    }

    public static final class Packet {
    	public static int counter = 0;
    	
        public final String protocol;
        public final String payload;
        public final int timeStamp;

        public Packet(String protocol, String payload) {
            this.protocol = protocol;
            this.payload = payload;
            this.timeStamp = counter++;
        }
    }

    private Heap<Packet> priorityQueue;
    public static final String EMPTY_MSG = "Nothing to send!";

    public BandwidthManager() {
        priorityQueue = new Heap<Packet>(new Comparator<Packet>() {

			@Override
			public int compare(Packet o1, Packet o2) {
				int protocolPriority = PRIORITIES.valueOf(o1.protocol).ordinal() - PRIORITIES.valueOf(o2.protocol).ordinal();
				if (protocolPriority == 0) {
					return o1.timeStamp - o2.timeStamp;
				}
				return protocolPriority;
			}
		});
    }

    // receives a packet with specified protocol and pay-load
    public void rcv(String protocol, String payload) {
        priorityQueue.insert(new Packet(protocol, payload));
    }

    // returns the pay-load of the packet which should be sent
    public String send() {
        return priorityQueue.size() == 0 ? EMPTY_MSG : priorityQueue.remove().payload;
    }

    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		BandwidthManager bm = new BandwidthManager();
		
		scanner.nextLine();
		String[] command = null;
		for (int i = 0; i < N; ++i) {
			command = scanner.nextLine().split("\\s+");
			if (command[0].equals("rcv")) {
				bm.rcv(command[1], command[2]);
			} else if (command[0].equals("send")) {
				System.out.println(bm.send());
			}
		}
		
		scanner.close();
	}
    
}