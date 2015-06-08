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
    public void testLeftSwaps() {
        Integer[] numbers = (Integer[]) Arrays.asList(1, 2, 3, 0).toArray();

        Vector.leftSwaps(numbers, 0, numbers.length - 1);
        for (int i = 0; i < numbers.length; ++i) {
            assertEquals(Integer.valueOf(i), numbers[i]);
        }
    }
    
    @Test
    public void testRightSwaps() {
        Integer[] numbers = (Integer[]) Arrays.asList(3, 0, 1, 2).toArray();

        Vector.rightSwaps(numbers, 0, numbers.length - 1);
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

        for (int i = 1; i < Vector.INITIAL_CAPACITY; ++i) {
            vector.add(i);
        }

        assertEquals(Vector.INITIAL_CAPACITY, vector.size());
        assertEquals(Vector.INITIAL_CAPACITY, vector.capacity());

        vector.add(vector.get(vector.size() - 1) + 1);

        assertEquals(1 + Vector.INITIAL_CAPACITY, vector.size());
        assertEquals((int) (Vector.INITIAL_CAPACITY * Vector.GROWTH_RATE), vector.capacity());

    }
    
    @Test
    public void testRemove() {
        final int length = 4;
        
        for (int i = 0; i < length; ++i) {
            vector.add(i);
        }
        
        assertEquals(length, vector.size());
        
        vector.remove(1);

        assertEquals(length - 1, vector.size());
        assertEquals(Integer.valueOf(0), vector.get(0));
        assertEquals(Integer.valueOf(2), vector.get(1));
    }
    
    @Test
    public void testInsert() {
        final int length = 4;
        
        for (int i = 0; i < length; ++i) {
            vector.add(i);
        }
        
        assertEquals(length, vector.size());
        
        vector.insert(1, 6);

        assertEquals(length + 1, vector.size());
        assertEquals(Integer.valueOf(0), vector.get(0));
        assertEquals(Integer.valueOf(6), vector.get(1));
    }

}
