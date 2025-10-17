package module5;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SimpleInsertionSort {
    public static void sort(int[] arr) {
        final int m = arr.length;

        for (int i = 1; i < m; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SimpleInsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}