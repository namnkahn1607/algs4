package module5;

import java.util.Arrays;

public class InsertionSort {
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
        int[] arr = {2, 10, 8, 5, 7 , 9};
        InsertionSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}