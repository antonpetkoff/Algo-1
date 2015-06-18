package kmin;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static kmin.KMin.kthMinimum;
import static org.junit.Assert.*;

public class TestKMin {

    @Test
    public void testKMin() {
        List<Integer> numbers = Arrays.asList(5, 2, 3, 6, 1, 4);
        for (int i = 1; i <= numbers.size(); ++i) {
            assertEquals(i, kthMinimum(numbers, i));
        }
    }
    
}
