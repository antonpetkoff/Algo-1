package rand.set;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TestRandSet {

	@Test
	public void testPutSizeContains() {
		final int COUNT = 1000000;
		RandSet rs = new RandSet();
		List<Integer> keys = new ArrayList<>();
		Random rand = new Random();
		int temp = 0;
		
		for (int i = 0; i < COUNT; i++) {
			temp = rand.nextInt();
			assertEquals(keys.size(), rs.size());
			keys.add(temp);
			rs.put(temp);
			assertEquals(keys.size(), rs.size());
			assertTrue(rs.contains(temp));
		}
		
	}
	
}
