package birthday.ranges;

import java.util.Arrays;

import org.junit.Test;

import static birthday.ranges.BirthdayRanges.birthdaysCount;
import static org.junit.Assert.*;

public class TestBirthdayRanges {

    @Test
    public void testBirthdayRanges1() {
        Integer[] birthdays = (Integer[]) Arrays.asList(5, 10, 6, 7, 3, 4, 5, 11, 21, 300, 15).toArray();
        Range[] ranges = new Range[] { new Range(4, 9), new Range(6, 7), new Range(200, 225), new Range(300, 365) };
        Integer[] counts = birthdaysCount(birthdays, ranges);
        
        assertEquals(4, counts.length);
        assertEquals(Integer.valueOf(5), counts[0]);
        assertEquals(Integer.valueOf(2), counts[1]);
        assertEquals(Integer.valueOf(0), counts[2]);
        assertEquals(Integer.valueOf(1), counts[3]);
    }
    
    @Test
    public void testBirthdayRanges2() {
        Integer[] birthdays = (Integer[]) Arrays.asList(3, 6, 1, 25, 3, 52, 2).toArray();
        Range[] ranges = new Range[] { new Range(2, 7), new Range(2, 80), new Range(0, 0), new Range(150, 160) };
        Integer[] counts = birthdaysCount(birthdays, ranges);
        
        assertEquals(4, counts.length);
        assertEquals(Integer.valueOf(4), counts[0]);
        assertEquals(Integer.valueOf(6), counts[1]);
        assertEquals(Integer.valueOf(0), counts[2]);
        assertEquals(Integer.valueOf(0), counts[3]);
    }

}
