package closest.store;

import static org.junit.Assert.*;

import org.junit.Test;

import static closest.store.ClosestCoffeeStore.closestCoffeeStore;

public class TestClosestCoffeeStore {

    public static final boolean[][] graph = { { false, true, false, true, false, false },
            { true, false, true, false, false, false }, { false, true, false, false, true, false },
            { true, false, false, false, false, false }, { false, false, true, false, false, true },
            { false, false, false, false, true, false }, };

    public static final boolean[] isCoffeeStore = { false, false, true, false, false, true };

    @Test
    public void testClosestCoffeeStore() {
        assertEquals(2, closestCoffeeStore(graph, isCoffeeStore, 0));
    }
    
}
