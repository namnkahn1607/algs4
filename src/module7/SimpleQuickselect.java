package module7;

/* Simple Quickselect: 2-way Partition
 * N: size of array
 * average/best/worst: O(N)
 * this version might attempt to produce wrong answer
 * for duplicate-populated arrays. BE CAUTIOUS!
 */

import edu.princeton.cs.algs4.StdRandom;

public class SimpleQuickselect {

    public static int select(int[] arr, int k) {
        if (k < 0 || k > arr.length) {
            throw new IndexOutOfBoundsException("k is not within array");
        }

        int L = 0, R = arr.length - 1;

        while (L < R) {
            final int pivot = SimpleQuicksort.partition(arr, L, R);

            if (pivot > k) {
                R = pivot - 1;
            } else if (pivot < k) {
                L = pivot + 1;
            } else {
                return arr[pivot];
            }
        }

        return arr[L];
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        int k = 5;

        StdRandom.shuffle(arr);
        System.out.println(SimpleQuickselect.select(arr, k));
    }
}