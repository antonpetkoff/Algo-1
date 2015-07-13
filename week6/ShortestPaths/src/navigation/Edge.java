package navigation;

/**
 *  Weighted edge
 */
public class Edge implements Comparable<Edge> {
    private final int first;
    private final int second;
    private final int weight;

    public Edge(int a, int b, int weight) {
        this.first = a;
        this.second = b;
        this.weight = weight;
    }

    public int weight() {
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
        return this.weight() - that.weight();
    }

    public String toString() {
        return String.format("%d %d %d", first, second, weight);
    }
}