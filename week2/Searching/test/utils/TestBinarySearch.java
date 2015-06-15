package utils;

import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;
import static utils.TestGenerator.*;

public class TestBinarySearch {

    public static final int TEST_CASES = 100;
    public static final int MAX_RANGE = 1000000;
    public static Random rand = new Random();
    
    @Test
    public void testBinarySearch() {
        Integer[] numbers = null;
        Integer key = null;
        int linearIndex = 0, binaryIndex = 0;
        
        for (int test = 0; test < TEST_CASES; ++test) {
            numbers = generateSortedArray(rand.nextInt(MAX_RANGE));
            key = rand.nextInt();
            linearIndex = linearSearch(numbers, key);
            binaryIndex = Binary.search(numbers, key);
            assertEquals(linearIndex, binaryIndex);
        }
    }
    
}
