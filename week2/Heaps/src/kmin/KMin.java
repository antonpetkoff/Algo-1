package kmin;

import heap.Heap;

import java.util.List;

public class KMin {

    // Finds the k-th minimum element in an unsorted collection.
    public static int kthMinimum(List<Integer> numbers, int k) {
        Heap<Integer> heap = new Heap<Integer>();
        heap.heapify(numbers, numbers.size());
        Integer min = null;
        
        for (int i = 0; i < k; ++i) {
            min = heap.extractMin();
        }
        
        return min;
    }

}
