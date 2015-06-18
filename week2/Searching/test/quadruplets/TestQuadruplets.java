package quadruplets;

import static org.junit.Assert.*;
import static quadruplets.Quadruplets.zeroQuadrupletsCount;

import org.junit.Test;

public class TestQuadruplets {

    @Test
    public void testQuadruplets() {
        int[] a = new int[] { 5, 3, 4 };
        int[] b = new int[] { -2, -1, 6 };
        int[] c = new int[] { -1, -2, 4 };
        int[] d = new int[] { -1, -2, 7 };

        int actual = zeroQuadrupletsCount(a, b, c, d);
        assertEquals(7, actual);
    }

}
