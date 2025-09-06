package module2;/* Abstract Union Find: Size Weight + Path Compression
 * N: number of elements
 * K: number of method calls
 * initialize: O(N)
 * connected: best - O(K.α(K,N)), worst - O(K.logN)
 * union: best - O(K.a(K,N)), worst - O(K.logN)
 * where a() is Ackermann function
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

abstract public class AbstractUF {

    protected final int[] id;
    protected final int[] treeSize;
    protected final int size;

    public AbstractUF(int N) {
        if (N <= 0)
            throw new IllegalArgumentException();

        size = N;
        id = new int[N];
        treeSize = new int[N];

        for (int i = 0; i < N; ++i) {
            id[i] = i;
            treeSize[i] = 1;
        }
    }

    public void union(int p, int q) {
        final int pRoot = root(p);
        final int qRoot = root(q);

        if (pRoot == qRoot) return;

        if (treeSize[pRoot] <= treeSize[qRoot]) {
            id[pRoot] = qRoot;
            treeSize[qRoot] += treeSize[pRoot];

        } else {
            id[qRoot] = pRoot;
            treeSize[pRoot] += treeSize[qRoot];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int root(int p) {
        validateVal(p);

        if (p != id[p])
            id[p] = root(id[p]);

        return id[p];
    }

    protected void validateVal(int x) {
        if (x < 0 || x >= size)
            throw new IndexOutOfBoundsException();
    }

    public static <T extends AbstractUF> void run(T uf) {
        while (!StdIn.isEmpty()) {
            final int p = StdIn.readInt();
            final int q = StdIn.readInt();

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.printf("connected %d to %d.%n", p, q);
                continue;
            }

            StdOut.printf("%d is already connected to %d.%n", p, q);
        }
    }
}