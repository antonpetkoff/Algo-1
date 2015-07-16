package hash.table;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestHashTable {

    private HashTable ht;
    
    @Before
    public void setUp() {
        ht = new HashTable();
    }
    
    @Test
    public void testSize() {
        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
        
        Random rand = new Random();
        int temp = 0;
        for (int i = 0; i < 10000; ++i) {
            temp = rand.nextInt();
            ht.put(temp, 1);
            assertEquals(Integer.valueOf(i+1), Integer.valueOf(ht.size()));
            assertTrue(ht.contains(temp));
        }
    }

}
