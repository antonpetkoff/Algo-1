package pouring.glasses;

import static org.junit.Assert.*;

import org.junit.Test;

import pouring.glasses.PouringGlasses.Triplet;

public class TestPouringGlasses {
    
    @Test
    public void testPour() {
        PouringGlasses.capacity = new Triplet(4, 7, 6);
        Triplet state = new Triplet(4, 5, 4);
        assertEquals(new Triplet(4, 3, 6), PouringGlasses.pour(state, 1, 2));
        assertEquals(state, PouringGlasses.pour(state, 2, 0));
    }
    
}
