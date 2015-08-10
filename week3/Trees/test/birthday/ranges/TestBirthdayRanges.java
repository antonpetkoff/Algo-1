package birthday.ranges;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBirthdayRanges {
	
    @Test
    public void testBirthdayRanges() {
        int values[] = {5, 10, 6, 7, 3, 4, 5, 11, 21, 300, 15};
        BirthdayRanges br = new BirthdayRanges(values);
        assertEquals(7, br.count(2, 10));
        assertEquals(5, br.count(10, 365));
        
        br.add(8, 3);
        assertEquals(6, br.count(7, 11));
        
        br.remove(8, 2);
        assertEquals(4, br.count(7, 11));

        br.add(18, 5);
        assertEquals(8, br.count(10, 20));
        
        br.add(5, 1);
        assertEquals(15, br.count(5, 25));
        
        br.remove(5, 10);
        assertEquals(6, br.count(1, 10));
    }
    
}
