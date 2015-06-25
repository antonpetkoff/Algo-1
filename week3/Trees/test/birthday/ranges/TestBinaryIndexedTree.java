package birthday.ranges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBinaryIndexedTree {

    private static BinaryIndexedTree bit;
    private static final int N = 8;
    
    @Before
    public void setUp() {
        int[] values = {0, 1, 1, 2, 4, 4, 4, 5, 5};
        bit = new BinaryIndexedTree(6, values);
    }
    
    @Test
    public void testTreeConstruction() {
        assertEquals(1, bit.tree[N + 0]);
        assertEquals(2, bit.tree[N + 1]);
        assertEquals(1, bit.tree[N + 2]);
        assertEquals(0, bit.tree[N + 3]);
        assertEquals(3, bit.tree[N + 4]);
        assertEquals(2, bit.tree[N + 5]);
        assertEquals(0, bit.tree[N + 6]);
        assertEquals(0, bit.tree[N + 7]);
        
        assertEquals(0, bit.tree[N - 1]);
        assertEquals(5, bit.tree[N - 2]);
        assertEquals(1, bit.tree[N - 3]);
        assertEquals(3, bit.tree[N - 4]);
        
        assertEquals(5, bit.tree[N - 5]);
        assertEquals(4, bit.tree[N - 6]);
        
        assertEquals(9, bit.tree[N - 7]);
    }
    
    @Test
    public void testQueries() {
        assertEquals(1, bit.query(0));
        assertEquals(3, bit.query(1));
        assertEquals(4, bit.query(2));
        assertEquals(4, bit.query(3));
        assertEquals(7, bit.query(4));
        assertEquals(9, bit.query(5));
        assertEquals(9, bit.query(6));
    }
    
    @Test
    public void testUpdate() {
        bit.update(0, 5);
        bit.update(3, 5);
        
        assertEquals(6, bit.tree[N + 0]);
        assertEquals(2, bit.tree[N + 1]);
        assertEquals(1, bit.tree[N + 2]);
        assertEquals(5, bit.tree[N + 3]);
        assertEquals(3, bit.tree[N + 4]);
        assertEquals(2, bit.tree[N + 5]);
        assertEquals(0, bit.tree[N + 6]);
        assertEquals(0, bit.tree[N + 7]);
        
        assertEquals(0, bit.tree[N - 1]);
        assertEquals(5, bit.tree[N - 2]);
        assertEquals(6, bit.tree[N - 3]);
        assertEquals(8, bit.tree[N - 4]);
        
        assertEquals(5, bit.tree[N - 5]);
        assertEquals(14, bit.tree[N - 6]);
        
        assertEquals(19, bit.tree[N - 7]);
    }
    
}
