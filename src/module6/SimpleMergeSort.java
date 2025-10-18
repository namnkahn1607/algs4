package module6;

/* Simple Mergesort: 2-way Mergesort + 2-size Cutoff
 * N: size of array
 * average/best/worst: O(NlogN) time & O(N) space
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SimpleMergeSort {

    private static void merge(int[] arr, int L, int M, int R) {
        final int size1 = M - L + 1;
        final int size2 = R - M;

        int[] arr1 = new int[size1];
        int[] arr2 = new int[size2];

        System.arraycopy(arr, L, arr1, 0, size1);
        System.arraycopy(arr, M + 1, arr2, 0, size2);

        int i = 0, j = 0, k = L;

        while (i < size1 && j < size2) {
            if (arr1[i] <= arr2[j]) {
                arr[k++] = arr1[i++];
            } else {
                arr[k++] = arr2[j++];
            }
        }

        while (i < size1) {
            arr[k++] = arr1[i++];
        }

        while (j < size2) {
            arr[k++] = arr2[j++];
        }
    }

    public static void sort(int[] arr, int L, int R) {
        if (L + 1>= R) {
            if (L + 1 == R && arr[L] > arr[R]) {
                swap(arr, L, R);
            }

            return;
        }

        final int M = L + (R - L) / 2;
        sort(arr, L, M);
        sort(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SimpleMergeSort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}