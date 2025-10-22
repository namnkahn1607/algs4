package module8;

import java.util.Comparator;

public class HyperHeapsort {
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        sort(arr, Comparator.naturalOrder());
    }

    public static <T> void sort(T[] arr, Comparator<? super T> cmp) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        sort(arr, 0, arr.length - 1, cmp);
    }

    public static <T> void sort(T[] arr, int L, int R, Comparator<? super T> cmp) {
        for (int i = L + (R - L) / 2; i >= L; --i) {
            heapify(arr, i, R, cmp);
        }

        for (int i = R; i > L; --i) {
            swap(arr, L, i);
            heapify(arr, L, i - 1, cmp);
        }
    }

    private static <T> void heapify(T[] arr, int i, int heapEnd, Comparator<? super T> cmp) {
        while (2 * i + 1 <= heapEnd) {
            final int L = 2 * i + 1;
            final int R = 2 * i + 2;
            int max = L;

            if (R <= heapEnd && cmp.compare(arr[R], arr[max]) > 0) {
                max = R;
            }

            if (cmp.compare(arr[i], arr[max]) < 0) {
                swap(arr, i, max);
                i = max;
            } else {
                break;
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}