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
    	final int COUNT = 1000000;
        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
        
        List<Integer> keys = new ArrayList<>();
        Random rand = new Random();
        int temp = 0;
        for (int i = 0; i < COUNT; ++i) {
            temp = rand.nextInt();
            keys.add(temp);
            ht.put(temp, 1);
            assertEquals(Integer.valueOf(i+1), Integer.valueOf(ht.size()));
            assertEquals(Integer.valueOf(1), ht.get(temp));
            assertTrue(ht.contains(temp));
        }
        
//        for (int i = COUNT; i > 0; --i) {
//        	temp = rand.nextInt(keys.size());
//        	ht.remove(keys.get(temp));
//        	assertEquals(Integer.valueOf(i - 1), Integer.valueOf(ht.size()));
//        	assertFalse(ht.contains(keys.get(temp)));
//        	keys.remove(temp);
//        }
//
//        assertEquals(Integer.valueOf(0), Integer.valueOf(ht.size()));
    }

}
