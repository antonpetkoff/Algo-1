package closest.store;

public class ClosestCoffeeStore {

    public static final boolean[][] graph = {
        {false, true, false, true, false, false},
        {true, false, true, false, false, false},
        {false, true, false, false, true, false},
        {true, false, false, false, false, false},
        {false, false, true, false, false, true},
        {false, false, false, false, true, false},
    };
    
    public static final boolean[] isCoffeeStore = {false, false, true, false, false, true};
    
    // Finds the closest coffee store to a point.
    public static int closestCoffeeStore(boolean[][] graph, boolean[] isCoffeeStore, int startingPoint) {
        return 0;
    }
    
    public static void main(String[] args) {
        int result = closestCoffeeStore(graph, isCoffeeStore, 0);
        System.out.println(result);
    }
}