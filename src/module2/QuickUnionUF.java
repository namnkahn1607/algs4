package module2;/* Quick Union
 * N: number of elements
 * K: number of method calls
 * initialize: O(N)
 * union: O(N.K)
 * connect: O(N.K)
 * */

import edu.princeton.cs.algs4.StdIn;

public class QuickUnionUF extends AbstractUF {

    public QuickUnionUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        final int pRoot = root(p);
        final int qRoot = root(q);

        if (pRoot != qRoot)
            id[pRoot] = qRoot;
    }

    @Override
    public int root(int p) {
        validateVal(p);

        while (id[p] != p) {
            p = id[p];
        }

        return p;
    }

    public static void main(String[] args) {
        final int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        run(uf);
    }
}