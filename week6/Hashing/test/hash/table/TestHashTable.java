package hash.table;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestHashTable {

    private HashTable<Integer, Integer> ht;
    
    @Before
    public void setUp() {
        ht = new HashTable<>();
    }
    
    @Test
    public void testPutFindContainsSize() {
    	final int COUNT = 10000;
        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
        
        List<Integer> keys = new ArrayList<>();
        Random rand = new Random();
        int temp = 0;
        for (int i = 0; i < COUNT; ++i) {
            temp = rand.nextInt();
            keys.add(temp);
            ht.put(temp, 1);
            assertTrue(ht.contains(temp));
            assertEquals(Integer.valueOf(1), ht.get(temp));
            assertEquals(keys.size(), ht.size());
            assertEquals(keys.size(), ht.keys().size());
        }
        
        
        
        int index = 0;
        for (int i = COUNT; i > 0; --i) {
        	index = rand.nextInt(keys.size());
            assertEquals(keys.size(), ht.size());

            assertTrue(ht.contains(keys.get(index)));
        	ht.remove(keys.get(index));
        	assertFalse(ht.contains(keys.get(index)));
        	keys.remove(index);
            
        	assertEquals(keys.size(), ht.size());
            System.out.println();
        }

        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
    }

}
