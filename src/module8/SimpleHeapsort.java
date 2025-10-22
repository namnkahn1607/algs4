package module8;

import edu.princeton.cs.algs4.StdRandom;
import module7.SimpleQuicksort;

import java.util.Arrays;

public class SimpleHeapsort {
    public static void sort(int[] arr, int L, int R) {
        for (int i = L + (R - L) / 2; i >= L; --i) {
            heapify(arr, i, R);
        }

        for (int i = R; i > L; --i) {
            heapify(arr, L, i - 1);
        }
    }

    private static void heapify(int[] arr, int i, int heapEnd) {
        while (2 * i + 1 <= heapEnd) {
            final int L = 2 * i + 1;
            final int R = 2 * i + 2;
            int max = L;

            if (R <= heapEnd && arr[R] > arr[max]) {
                max = R;
            }

            if (arr[i] < arr[max]) {
                swap(arr, i, max);
                i = max;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SimpleQuicksort.sort(arr, 1, arr.length - 2);
        System.out.println(Arrays.toString(arr));

        SimpleQuicksort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}