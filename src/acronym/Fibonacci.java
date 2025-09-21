// 1.1.19
package acronym;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
    public long F(int N) {
        int[] dp = {0, 1};

        for (int i = 2; i <= N; ++i)
            dp[i % 2] = dp[0] + dp[1];

        return dp[N % 2];
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdOut.println(new Fibonacci().F(N));
    }
}