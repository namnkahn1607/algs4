import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/** Weighted Undirected Graph
 */
public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    // Construct empty Graph
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException(
                "Number of vertices can not be negative."
            );
        }

        this.E = 0;
        this.V = V;
        this.adj = (Bag<Edge>[]) new Bag[V];

        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<Edge>();
        }
    }

    // Construct Graph from inputstream
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException(
                "Input to EdgeWeightedGraph is null."
            );
        }

        try {
            this.V = in.readInt();
            this.E = in.readInt();

            if (V < 0 || E < 0) {
                throw new IllegalArgumentException(
                    "Number of vertices or edges can not be null."
                );
            }

            this.adj = (Bag<Edge>[]) new Bag[V];
            
            for (int v = 0; v < V; ++v) {
                adj[v] = new Bag<Edge>();
            }

            for (int i = 0; i < E; ++i) {
                int v = in.readInt();
                int w = in.readInt();
                double weight = in.readDouble();
                addEdge(new Edge(v, w, weight));
            }
            
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(
                "Invalid input stream to EdgeWeightedGraph."
            );
        }
    }

    // Adding an Edge into Graph
    public void addEdge(Edge e) {
        final int v = e.either();
        final int w = e.other(v);
        
        validateVertex(v);
        validateVertex(w);

        adj[v].add(e);
        adj[w].add(e);
        ++E;
    }

    // All vertex's neighbors
    public Iterable<Edge> adj(int vertex) {
        validateVertex(vertex);
        return adj[vertex];
    }

    // Degree of one Vertex
    public int degree(int vertex) {
        validateVertex(vertex);
        return adj[vertex].size();
    }

    // All Edges in Graph
    public Iterable<Edge> edges() {
        List<Edge> list = new ArrayList<>();

        for (int v = 0; v < V; ++v) {
            int selfLoops = 0;

            for (Edge e : adj[v]) {
                final int diff = e.either() - v;

                if (diff > 0) {
                    list.add(e);
                } else if (diff == 0) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }

                    ++selfLoops;
                }
            }
        }

        return list;
    }

    // Number of Edges
    public int E() {
        return this.E;
    }

    // Number of Vertices
    public int V() {
        return this.V;
    }

    // String representation of Graph
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
            String.format("V: %d\nE: %d\n", V(), E())
        );

        for (Edge edge : edges()) {
            sb.append(edge).append("\n");
        }

        return sb.toString();
    }

    private void validateVertex(int vertex) {
        if (vertex < 0 || vertex > V) {
            throw new IllegalArgumentException(
                "Out of bound vertex index."
            );
        }
    }
}