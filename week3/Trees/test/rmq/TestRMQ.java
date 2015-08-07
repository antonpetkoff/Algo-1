package rmq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *	Zero-based arguments expected 
 */
public class TestRMQ {
	
    private RMQ rmq;
    private int[] values = {19, 11, 15, 4, 7, 13, 11, 2, 3, 5, 12, 7, 23, 17, 4, 6};
    
    private static int minOfRange(int[] arr, int start, int end) {
    	int min = arr[start];
    	for (int i = start; i <= end; ++i) {
    		min = Math.min(min, arr[i]);
    	}
    	return min;
    }
    
    @Before
    public void setUp() {
        rmq = new RMQ(values);
    }
    
    @Test
    public void testAllMinQueries() {
    	
    	for (int i = 0; i < values.length; ++i) {
    		for (int j = i; j < values.length; ++j) {
    			System.out.println(i + " " + j);
    			assertEquals(minOfRange(values, i, j), rmq.min(i, j));
    		}
    	}
    	
    }
    
}
