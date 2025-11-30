/** Weighted Undirected Edge
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0 || w < 0) {
            throw new IllegalArgumentException(
                "Vertex must be a non-negative integer."
            );
        }

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int vertex) {
        if (vertex == v) return this.w;
        if (vertex == w) return this.v;
        
        throw new IllegalArgumentException(String.format(
            "Illegal endpoint %d doesn't belong to this Edge.", vertex
        ));
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("%s-%s %.2f", v, w, weight);
    }
}