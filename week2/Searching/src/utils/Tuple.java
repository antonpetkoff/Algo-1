package utils;

public final class Tuple {
    
    public final int left;
    public final int right;
    
    public Tuple(int left, int right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "[" + left + "," + right + "]";
    }
    
}
