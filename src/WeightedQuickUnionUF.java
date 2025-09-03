/* Weighted Quick Union
 * N: number of elements
 * K: number of method calls
 * initialize: O(N)
 * connected: O(K.logN)
 * union: O(K.logN)
 * */

import edu.princeton.cs.algs4.StdIn;

public class WeightedQuickUnionUF extends AbstractUF {

    public WeightedQuickUnionUF(int N) {
        super(N);
    }

    @Override
    public int root(int p) {
        validateVal(p);

        while (id[p] != p)
            p = id[p];

        return p;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        AbstractUF.run(uf);
    }
}