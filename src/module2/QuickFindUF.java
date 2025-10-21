package module2;

/* Quick Find
 * N: number of elements
 * K: number of method calls
 * initialize: O(N)
 * union: O(N.K) - not fast
 * connected: O(K)
 * */

import edu.princeton.cs.algs4.StdIn;

public class QuickFindUF extends AbstractUF {
    public QuickFindUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        if (connected(p, q)) return;

        final int IDp = id[p];
        final int IDq = id[q];

        for (int i = 0; i < size; ++i) {
            if (id[i] == IDp)
                id[i] = IDq;
        }
    }

    @Override
    public int root(int p) {
        validateVal(p);

        return id[p];
    }

    public static void main(String[] args) {
        final int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
        run(uf);
    }
}