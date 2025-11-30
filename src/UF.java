/** Weighted Quick Union with Path Compression
 * - Union by rank
 */
public class UF {
    
    private final int[] parent;
    private final int[] rank;
    private int count;

    public UF(int N) {
        this.parent = new int[N];
        this.rank = new int[N];
        this.count = N;

        for (int i = 0; i < N; ++i) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int i) {
        validate(i);

        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    public boolean union(int x, int y) {
        final int rx = find(x);
        final int ry = find(y);

        if (rx == ry) {
            return false;
        }
    
        final int diff = rank[rx] - rank[ry];
        --count;

        if (diff < 0) {
            parent[rx] = ry;
        } else if (diff > 0) {
            parent[ry] = rx;
        } else {
            parent[rx] = ry;
            ++rank[ry];
        }

        return true;
    }

    public int count() {
        return this.count;
    }

    private void validate(int val) {
        final int N = parent.length;

        if (val < 0 || val >= N) {
            throw new IllegalArgumentException(String.format(
                "Index must be in range 0 and %d.", N - 1 
            ));
        }
    }
}