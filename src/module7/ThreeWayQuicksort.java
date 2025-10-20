package module7;

/* Three-way Quicksort: 3-way Partition + Median of 3 + Non-sentinel cutoff
 * N: size of array
 * average: O(NlogN) - as usual Quicksort
 * best: O(N) for 3-distinct-value arrays
 * worst: O(N^2) for very distinctive arrays
 * O(logN) space
 * -> suitable for arrays with small number of distinct values,
 * which can be called Entropy-optimal arrays.
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.function.IntSupplier;

public class ThreeWayQuicksort {
    public static void sort(char[] arr, int L, int R) {
        if (L + 1 >= R) {
            if (L + 1 == R && arr[L] > arr[R])
                swap(arr, L, R);

            return;
        }

        IntSupplier median = () -> {
            final int M = L + (R - L) / 2;

            if (arr[L] > arr[M]) swap(arr, L, M);
            if (arr[L] > arr[R]) swap(arr, L, R);
            if (arr[M] > arr[R]) swap(arr, M, R);

            swap(arr, L, M);
            return arr[L];
        };

        final int pivot = median.getAsInt();
        int lt = L, gt = R;
        int i = L;

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                ++i;
            }
        }

        sort(arr, L, lt - 1);
        sort(arr, gt + 1, R);
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        char[] arr = {
                'A' ,'R', 'B', 'W', 'W', 'A', 'A', 'R', 'W',
                'B', 'A', 'A', 'R', 'R', 'A', 'W', 'B', 'R'
        };

        StdRandom.shuffle(arr);
        ThreeWayQuicksort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}