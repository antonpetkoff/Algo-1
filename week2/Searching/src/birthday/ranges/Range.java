package birthday.ranges;

public final class Range {
    
    public final int left;
    public final int right;
    
    public Range(int left, int right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "[" + left + "," + right + "]";
    }
    
}
