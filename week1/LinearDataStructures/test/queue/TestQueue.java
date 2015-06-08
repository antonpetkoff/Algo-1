package queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestQueue {

    private Queue<Integer> queue;
    
    @Before
    public void initQueue() {
        queue = new Queue<Integer>();
    }
    
    @Test
    public void testPush() {
        assertEquals(0, queue.size());
        
        queue.push(0);
        assertEquals(1, queue.size());

        queue.push(1);
        assertEquals(2, queue.size());
    }
    
    @Test
    public void testPop() {
        int count = 3;
        
        for (int i = 0; i < count; ++i) {
            queue.push(i);
        }
        
        int i = 0;
        while (queue.size() != 0) {
            assertEquals(Integer.valueOf(i++), queue.pop());
        }
    }
    
}
