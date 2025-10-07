package module2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] system;
    private final WeightedQuickUnionUF uf;
    private final int dimension;
    private int opened;

    private final int topVirtualSiteIndex;
    private final int bottomVirtualSiteIndex;

    private final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();

        system = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        dimension = n;
        opened = 0;

        int totalSites = n * n;

        topVirtualSiteIndex = totalSites;
        bottomVirtualSiteIndex = totalSites + 1;
    }

    public void open(int row, int col) {
        validateOrThrow(row, col);

        if (system[row - 1][col - 1]) return;

        system[row - 1][col - 1] = true;
        ++opened;

        final int currentSiteIndex = calculateIndex(row, col);

        if (row == 1)
            uf.union(currentSiteIndex, topVirtualSiteIndex);

        if (row == dimension)
            uf.union(currentSiteIndex, bottomVirtualSiteIndex);

        for (int[] direction : dir) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (validatePos(newRow, newCol) && isOpen(newRow, newCol)) {
                final int neighborSiteIndex = calculateIndex(newRow, newCol);
                uf.union(currentSiteIndex, neighborSiteIndex);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validateOrThrow(row, col);

        return system[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;

        final int siteRootIndex = uf.find(calculateIndex(row, col));

        return siteRootIndex == uf.find(topVirtualSiteIndex);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        return uf.find(topVirtualSiteIndex) == uf.find(bottomVirtualSiteIndex);
    }

    private boolean validatePos(int row, int col) {
        return (1 <= row && row <= dimension) && (1 <= col && col <= dimension);
    }

    private void validateOrThrow(int row, int col) {
        if (!validatePos(row, col)) throw new IllegalArgumentException();
    }

    private int calculateIndex(int row, int col) {
        return (row - 1) * dimension + (col - 1);
    }
}