package online.median;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import online.median.Heap;

public class TestHeap {
    
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
    public void testHeapSortAscending() {

        new Heap<Integer>((o1, o2) -> o1.compareTo(o2)).sort(numbers);
        
        for (int i = 1; i < SIZE; ++i) {
            assertTrue(numbers.get(i - 1).compareTo(numbers.get(i)) <= 0);
        }
    }
    
    @Test
    public void testHeapSortDescending() {
        new Heap<Integer>((o1, o2) -> o2.compareTo(o1)).sort(numbers);
        
        for (int i = 1; i < SIZE; ++i) {
            assertTrue(numbers.get(i - 1).compareTo(numbers.get(i)) >= 0);
        }
    }
    
}
