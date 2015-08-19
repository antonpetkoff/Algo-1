package rand.set;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TestRandSet {

	@Test
	public void testPutSizeContainsRemove() {
		final int ITER = 100;
		for (int k = 0; k < ITER; ++k) {
			final int COUNT = 100000;
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
				assertNotNull(rs.getNode(temp));
			}
			
			for (int i = COUNT; i > 0; --i) {
				temp = rand.nextInt(keys.size());	// index
				assertEquals(keys.size(), rs.size());
				assertTrue(rs.contains(keys.get(temp)));
				rs.remove(keys.get(temp));
				keys.remove(temp);
				assertEquals(keys.size(), rs.size());
			}
		}
		
	}
	
}
