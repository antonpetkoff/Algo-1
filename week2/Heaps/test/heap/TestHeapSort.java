package heap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import heap.Heap;

public class TestHeapSort {
    
    private List<Integer> numbers;
    private static final int SIZE = 100000;
    private static final int MAX_VALUE = 10000;
    private Random rand = new Random();

    @Before
    public void setUp() {
        numbers = new ArrayList<Integer>(SIZE);

        for (int i = 0; i < SIZE; ++i) {
            numbers.add(rand.nextInt(MAX_VALUE));
        }
    }
    
    @Test
    public void testHeapSort() {
        (new Heap<Integer>()).sort(numbers);
        
        for (int i = 1; i < SIZE; ++i) {
            assertTrue(numbers.get(i - 1).compareTo(numbers.get(i)) <= 0);
        }
    }
    
}
