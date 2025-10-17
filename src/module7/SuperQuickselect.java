package module7;

/* Super Quickselect: 2-way Partition + Median of 3 + Non-sentinel Cutoff
 * N: size of array
 * average/best/worst: O(N)
 * for array with duplicate values, algs4's implementation
 * might produce wrong answer, so this variant fixed it.
 */

import edu.princeton.cs.algs4.StdRandom;

public class SuperQuickselect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k > arr.length) {
            throw new IndexOutOfBoundsException("k is not within array");
        }

        int L = 0, R = arr.length - 1;

        while (L < R) {
            if (L + 1 >= R) {
                if (L + 1 == R && arr[L] > arr[R])
                    swap(arr, L, R);

                return arr[k];
            }

            final int pivot = SuperQuicksort.partition(arr, L, R);

            if (pivot >= k) R = pivot - 1;
            if (pivot <= k) L = pivot + 1;
        }

        return arr[L];
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        int k = 5;

        StdRandom.shuffle(arr);
        System.out.println(SuperQuickselect.select(arr, k));
    }
}