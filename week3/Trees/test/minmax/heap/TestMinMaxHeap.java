package minmax.heap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import minmax.heap.MinMaxHeap.Node;
import static minmax.heap.MinMaxHeap.isMinMax;

public class TestMinMaxHeap {

    @Test
    public void testIsMinMaxTrue() {
        Node root = new Node(20,
                new Node(50, 
                        new Node(12, new Node(36, null, null), new Node(25, null, null)),
                        new Node(15, new Node(19, null, null), null)), 
                new Node(47, 
                        new Node(10, null, null), new Node(9, null, null)));
        assertTrue(isMinMax(root));
    }
    
    @Test
    public void testIsMinMaxFalse() {
        Node root = new Node(12,
                new Node(4, new Node(20, null, null), new Node (500, null, null)), 
                new Node(17, new Node(14, null, null), null));
        assertFalse(isMinMax(root));
    }
    
}
