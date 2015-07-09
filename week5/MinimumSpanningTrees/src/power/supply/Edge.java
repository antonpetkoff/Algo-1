package power.supply;

/**
 *  Weighted edge
 */
public class Edge implements Comparable<Edge> {
    private final int first;
    private final int second;
    private final double weight;

    public Edge(int a, int b, double weight) {
        this.first = a;
        this.second = b;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return first;
    }

    public int other(int vertex) {
        if (vertex == first)
            return second;
        else if (vertex == second)
            return first;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight())
            return -1;
        else if (this.weight() > that.weight())
            return +1;
        else
            return 0;
    }

    public String toString() {
        return String.format("%d-%d %.2f", first, second, weight);
    }
}