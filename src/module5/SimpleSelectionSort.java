package module5;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SimpleSelectionSort {

    public static void sort(int[] arr) {
        final int m = arr.length;

        for (int i = 0; i < m - 1; ++i) {
            int minIdx = i;

            for (int j = i + 1; j < m; ++j) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }

            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SimpleSelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}