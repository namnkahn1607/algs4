package module7;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class SimpleQuickSort {

    private static int partition(int[] arr, int L, int R) {
        int leftWall = L + 1;

        for (int i = L + 1; i <= R; ++i) {
            if (arr[i] < arr[L]) {
                swap(arr, i, leftWall);
                ++leftWall;
            }
        }

        swap(arr, L, leftWall - 1);

        return leftWall - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void sort(int[] arr, int L, int R) {
        if (L >= R) return;

        int pivot = partition(arr, L, R);
        sort(arr, L, pivot - 1);
        sort(arr, pivot + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = {18, 19, 33, 20, 19, 12, 8, 24, 14, 14};
        StdRandom.shuffle(arr);

        SimpleQuickSort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}