package stack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestStack {

    private Stack<Integer> stack;
    
    @Before
    public void initStack() {
        stack = new Stack<Integer>();
    }
    
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        
        stack.push(0);
        assertEquals(1, stack.size());

        stack.push(1);
        assertEquals(2, stack.size());
        
        assertEquals(Integer.valueOf(1), stack.pop());
        assertEquals(1, stack.size());
    }
    
    @Test
    public void testPop() {
        int count = 3;
        
        for (int i = 0; i < count; ++i) {
            stack.push(i);
        }
        
        while (stack.size() != 0) {
            assertEquals(Integer.valueOf(--count), stack.pop());
        }
    }
    
}
