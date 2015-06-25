package online.median;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMedian {

    private Median median = new Median();
    
    @Test
    public void testMedian() {
        assertEquals(5, median.insert(5));
        assertEquals(6, median.insert(6));
        assertEquals(6, median.insert(7));
        assertEquals(6, median.insert(4));
        assertEquals(5, median.insert(3));
        assertEquals(6, median.insert(10));
        assertEquals(6, median.insert(20));
        assertEquals(7, median.insert(30));
        assertEquals(7, median.insert(40));
        assertEquals(10, median.insert(50));
    }
    
}
