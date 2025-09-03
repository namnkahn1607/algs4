import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int T;
    private final double[] fractions;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        T = trials;
        fractions = new double[trials];

        for (int i = 0; i < trials; ++i) {
            Percolation fig = new Percolation(n);

            while (!fig.percolates()) {
                final int row = 1 + StdRandom.uniformInt(n);
                final int col = 1 + StdRandom.uniformInt(n);

                fig.open(row, col);
            }

            fractions[i] = 1.0 * fig.numberOfOpenSites() / fig.totalSites;
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        final int n = StdIn.readInt();
        final int T = StdIn.readInt();

        PercolationStats unit = new PercolationStats(n, T);

        StdOut.printf("mean                    = %f%n", unit.mean());
        StdOut.printf("stddev                  = %f%n", unit.stddev());
        StdOut.printf("95% confidence interval = [%f, %f]%n", unit.confidenceLo(), unit.confidenceHi());
    }
}