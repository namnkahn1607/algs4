// 1.1.14
package acronym;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class lgN {
    public static void lg(int N) {
        int res = -1;
        int i = 1;

        while (i <= N) {
            ++res;
            i *= 2;
        }

        StdOut.println(res);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        lg(N);
    }
}