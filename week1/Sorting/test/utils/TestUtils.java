package utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utilities.Utils;

public class TestUtils {

    private static int ARRAY_SIZE = 10;
    private Integer[] array;
    
    @Before
    public void setUp() {
        array = new Integer[ARRAY_SIZE];
        
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            array[i] = i;
        }
    }
    
    @Test
    public void testIsSorted() {
        assertTrue(Utils.isSorted(array));
    }
    
    @Test
    public void testIsNotSorted() {
        array[0] = 100;
        assertFalse(Utils.isSorted(array));
    }
    
}
