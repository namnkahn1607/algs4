package module6;

import java.util.Arrays;

public class Q2 {
    public static int kthRank(int[] a, int[] b, int k) {
        final int n1 = a.length;
        final int n2 = b.length;

        if (n1 > n2) {
            return kthRank(b, a, k);
        }

        int L = 0, R = n1;

        while (L <= R) {
            final int i = L + (R - L) / 2;
            final int j = k + 1 - i;

            if (j < 0) {
                R = i - 1;
                continue;
            }

            if (j > n2) {
                L = i + 1;
                continue;
            }

            int aLeft = i > 0 ? a[i - 1] : Integer.MIN_VALUE;
            int bLeft = j > 0 ? b[j - 1] : Integer.MIN_VALUE;
            int aRight = i < n1 ? a[i] : Integer.MAX_VALUE;
            int bRight = j < n2 ? b[j] : Integer.MAX_VALUE;

            if (aLeft <= bRight && bLeft <= aRight) {
                return Math.max(aLeft, bLeft);
            } else if (aLeft > bRight) {
                R = i - 1;
            } else {
                L = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10, 12};
        int k = 4;

        // Version 1
        System.out.println(kthRank(
                a, Arrays.stream(b, 0, b.length - 1).toArray(),
                (a.length + b.length) / 2
        ));

        // Version 2
        System.out.println(kthRank(a, b, (a.length + b.length) / 2));

        // Version 3
        System.out.println(kthRank(a, b, k));
    }
}