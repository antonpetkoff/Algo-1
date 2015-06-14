package sorting;

import static org.junit.Assert.*;
import static utilities.Utils.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestSorting {

    private Integer[] numbers;
    private static final int SIZE = 10000;
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
        assertFalse(isSorted(numbers));
        Selection.sort(numbers);
        assertTrue(isSorted(numbers));
    }

    @Test
    public void testInsertion() {
        assertFalse(isSorted(numbers));
        Insertion.sort(numbers);
        assertTrue(isSorted(numbers));
    }

    @Test
    public void testMerge() {
        assertFalse(isSorted(numbers));
        Merge.sort(numbers);
        assertTrue(isSorted(numbers));
    }

    @Test
    public void testQuick() {
        assertFalse(isSorted(numbers));
        Quick.sort(numbers);
        assertTrue(isSorted(numbers));
        Quick.sort(numbers);
        assertTrue(isSorted(numbers));
    }
    
    @Test
    public void testCounting() {
        assertFalse(isSorted(numbers));
        Counting.sort(numbers);
        assertTrue(isSorted(numbers));
        Counting.sort(numbers);
        assertTrue(isSorted(numbers));
    }

}
