package vector;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestVector {

    private Vector<Integer> vector;
    
    @Before
    public void initVector() {
        vector = new Vector<Integer>();
    }
    
    @Test
    public void testSwapLeft() {
        Integer[] numbers = (Integer[]) Arrays.asList(1, 2, 3, 0).toArray();
        
        Vector.swapLeft(numbers, 0, numbers.length - 1);
        for (int i = 0; i < numbers.length; ++i) {
            assertEquals(Integer.valueOf(i), numbers[i]);
        }
    }
    
    @Test
    public void testAdd() {
        assertEquals(0, vector.size());
        assertEquals(0, vector.capacity());
        
        vector.add(0);
        
        assertEquals(1, vector.size());
        assertEquals(Vector.INITIAL_CAPACITY, vector.capacity());
        assertEquals(Integer.valueOf(0), vector.get(0));
    }
    
}
