package online.median;

public class Median {
    
    private Heap<Integer> maxHeap;
    private Heap<Integer> minHeap;
    private Integer median;
    
    public Median() {
        maxHeap = new Heap<Integer>((o1, o2) -> o2.compareTo(o1));
        minHeap = new Heap<Integer>((o1, o2) -> o1.compareTo(o2));
    }

    //inserts the number and returns the median
    public int insert(int number){
        if (median == null) {
            median = number;
            minHeap.insert(number);
            return median;
        }
        
        if (number >= median) {
            if (minHeap.size() - 1 == maxHeap.size()) {
                maxHeap.insert(minHeap.remove());
                minHeap.insert(number);
                median = minHeap.peek();
            } else {    // median stays the same
                minHeap.insert(number);
            }
        } else {
            if (maxHeap.size() == minHeap.size()) {
                minHeap.insert(maxHeap.remove());
                maxHeap.insert(number);
                median = minHeap.peek();
            } else {    // median stays the same
                maxHeap.insert(number);
            }
        }
        
        return median;
    }
    
}
