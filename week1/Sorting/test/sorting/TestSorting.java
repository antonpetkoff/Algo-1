package sorting;

import static org.junit.Assert.*;
import utilities.Utils;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestSorting {

    private Integer[] numbers;
    private static final int SIZE = 1000;
    private Random rand = new Random();
    
    @Before
    public void setUp() {
        numbers = new Integer[SIZE];
        
        for (int i = 0; i < SIZE; ++i) {
            numbers[i] = rand.nextInt(10);
        }
    }
    
    @Test
    public void testSelection() {
        assertFalse(Utils.isSorted(numbers));
        
        Selection.sort(numbers);
        
        assertTrue(Utils.isSorted(numbers));
    }
    
    @Test
    public void testInsertion() {
        assertFalse(Utils.isSorted(numbers));
        
        Insertion.sort(numbers);
        
        assertTrue(Utils.isSorted(numbers));
    }
    
    @Test
    public void testMerge() {
        assertFalse(Utils.isSorted(numbers));
        
        Merge.sort(numbers);
        
        assertTrue(Utils.isSorted(numbers));
    }
    
    @Test
    public void testQuick() {
        assertFalse(Utils.isSorted(numbers));
        
        Quick.sort(numbers);
        
        assertTrue(Utils.isSorted(numbers));
        
        Quick.sort(numbers);
        
        assertTrue(Utils.isSorted(numbers));
        
    }
    
}
