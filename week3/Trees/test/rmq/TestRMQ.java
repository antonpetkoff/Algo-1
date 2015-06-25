package rmq;

import org.junit.Before;
import org.junit.Test;

public class TestRMQ {

    private RMQ rmq;
    
    @Before
    public void setUp() {
        int[] values = {19, 11, 15, 4, 7, 13, 11};
        RMQ rmq = new RMQ(values);
    }
    
    @Test
    public void testConstruction() {
        
    }
    
}
