package module5;

import java.util.Arrays;

public class SelectionSort {

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
        int[] arr = {2, 4, 10, 8, 1, 5, 7, 3};
        SelectionSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}