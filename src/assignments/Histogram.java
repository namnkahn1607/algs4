// 1.1.15
package assignments;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Histogram {
    public static int[] histogram(int[] a, int M) {
        int[] output = new int[M];

        for (int x : a) {
            if (x >= 0 && x < M)
                ++output[x];
        }

        return output;
    }

    public static void main(String[] args) {
        int M = StdIn.readInt();
        int[] arr = new int[M];

        for (int i = 0; i < M; ++i)
            arr[i] = StdIn.readInt();

        StdOut.println(Arrays.toString(histogram(arr, M)));
    }
}