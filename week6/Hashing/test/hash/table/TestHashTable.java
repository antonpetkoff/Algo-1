package hash.table;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestHashTable {

    private HashTable<Double> ht;
    
    @Before
    public void setUp() {
        ht = new HashTable<Double>();
    }
    
    @Test
    public void testPut() {
        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
        
        for (int i = 0; i < 10000; ++i) {
            ht.put(Math.random());
            assertEquals(Integer.valueOf(i), Integer.valueOf(ht.size()));
        }
    }
    
}
