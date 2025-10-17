package module7;

/* Super Quicksort: 2-way Partition + Median of 3 + Non-sentinel Cutoff
 * N: size of array
 * average: O(NlogN), worst: O(N^2) on Median of 3 killer arrays
 * handle duplicate-populated array in O(NlogN)
 * shuffle further preventing worst case
 * -> most generally efficient variant of Quicksort
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.function.IntSupplier;

public class SuperQuicksort {

    public static int partition(int[] arr, int L, int R) {
        IntSupplier median = () -> {
            final int M = L + (R - L) / 2;

            if (arr[L] > arr[M]) swap(arr, L, M);
            if (arr[L] > arr[R]) swap(arr, L, R);
            if (arr[M] > arr[R]) swap(arr, M, R);

            swap(arr,L + 1, M);
            return arr[L + 1];
        };

        final int pivot = median.getAsInt();
        int i = L + 1;
        int j = R;

        while (true) {
            while (true) {
                if (arr[++i] >= pivot)
                    break;
            }

            while (true) {
                if (arr[--j] <= pivot)
                    break;
            }

            if (i > j)
                break;

            swap(arr, i, j);
        }

        swap(arr, L + 1, j);

        return j;
    }

    public static void sort(int[] arr, int L, int R) {
        if (L + 1 >= R) {
            if (L + 1 == R && arr[L] > arr[R])
                swap(arr, L, R);

            return;
        }

        final int pivot = partition(arr, L, R);
        sort(arr, L, pivot - 1);
        sort(arr, pivot + 1, R);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SuperQuicksort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}