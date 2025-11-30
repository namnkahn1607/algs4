import java.util.Arrays;
import java.util.Queue;

/** Kruskal's Algorithm: MST
 * construct the MST of an Weighted Undirected Graph
 * - Time: E.logE (worst case)
 * - Space: E
 */
public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G) {
        final int V = G.V();
        final int E = G.E();
        Edge[] edgeArray = new Edge[E];
        int t = 0;

        for (Edge e : G.edges()) {
            edgeArray[t++] = e;
        }

        Arrays.sort(edgeArray);
        final UF uf = new UF(V);

        for (int i = 0; i < E && mst.size() < V - 1; ++i) {
            Edge e = edgeArray[i];
            final int v = e.either();
            final int w = e.other(v);

            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.add(e);
                weight += e.weight();
            }
        }

        if (mst.size() != V - 1) {
            throw new RuntimeException("Not producing a MST.");
        }
    }

    public Iterable<Edge> edges() {
        return this.mst;
    }

    public double weight() {
        return this.weight;
    }
}