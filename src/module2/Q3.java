package module2;/* Union Find Interview Question
* assume N is the total number of elements
* successor: O(logN) first call, O(1) next call
* remove: relies on find, so they're the same
* */

public class Q3 {
    private final int[] successor;
    private final boolean[] deleted;
    private final int total;

    public Q3(int N) {
        successor = new int[N];
        deleted = new boolean[N];
        total = N;

        for (int i = 0; i < N; ++i)
            successor[i] = i;
    }

    public void remove(int x) {
        validateVal(x);

        if (deleted[x]) return;

        successor[x] = successor(x + 1);
    }

    public int successor(int x) {
        validateVal(x);

        while (x != successor[x])
            successor[x] = successor(successor[x]);

        return successor[x];
    }

    private void validateVal(int x) {
        if (x < 0 || x >= total) throw new IllegalArgumentException();
    }
}