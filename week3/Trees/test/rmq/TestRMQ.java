package rmq;

import static org.junit.Assert.*;

import java.util.Random;

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
    
    public static void testAllPossibleMinQueries(RMQ rmq, int[] values) {
    	for (int i = 0; i < values.length; ++i) {
    		for (int j = i; j < values.length; ++j) {
    			if (minOfRange(values, i, j) != rmq.min(i, j)) {
    				System.out.println(i + " " + j);
    			}
//    			assertEquals(minOfRange(values, i, j), rmq.min(i, j));
    		}
    	}
    }
    
    @Test
    public void testExample() {
    	int[] values = {70, 69, 77, 16, 96, 41, 61, 78, 74, 8, 9, 64, 30, 12, 76, 79, 42, 31, 91, 78};
    	RMQ rmq = new RMQ(values);
    	rmq.printState();
    }
    
    @Test
    public void bulkTestQueries() {
    	Random rand = new Random();
    	final int SIZE = 2000, BOUND = 10000;
    	int[] values = new int[SIZE];
    	
    	System.out.print("values: ");
    	for (int i = 0; i < values.length; ++i) {
    		values[i] = rand.nextInt(BOUND);
    		System.out.print(values[i] + ", ");
    	}
    	System.out.println();
    	
    	RMQ rmq = new RMQ(values);
    	
    	testAllPossibleMinQueries(rmq, values);
    }
    
    @Test
    public void testSetQueries() {
    	rmq.set(7, 50);
    	assertEquals(50, rmq.tree[23]);
    	assertEquals(11, rmq.tree[11]);
    	assertEquals(7, rmq.tree[5]);
    	assertEquals(4, rmq.tree[2]);
    	assertEquals(3, rmq.tree[1]);
    	assertEquals(4, rmq.min(0, 7));
    	assertEquals(11, rmq.min(6, 7));
    }
    
}
