// 1.1.20
package acronym;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class lnFactorialN {
    public double lnN(int N) {
        if (N == 1) return 0;

        return Math.log(N) + lnN(N - 1);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdOut.println(new lnFactorialN().lnN(N));
    }
}