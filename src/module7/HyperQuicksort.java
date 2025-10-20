package module7;

import java.util.Comparator;

public class HyperQuicksort {
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

    public static <T> int partition(T[] arr, int L, int R, Comparator<? super T> cmp) {
        T pivot = median(arr, L, R, cmp);
        int i = L + 1;
        int j = R;

        while (true) {
            while (true) {
                if (cmp.compare(arr[++i], pivot) >= 0)
                    break;
            }

            while (true) {
                if (cmp.compare(arr[--j], pivot) <= 0)
                    break;
            }

            if (i > j)
                break;

            swap(arr, i, j);
        }

        swap(arr, L + 1, j);

        return j;
    }

    private static <T> T median(T[] arr, int L, int R, Comparator<? super T> cmp) {
        final int M = L + (R - L) / 2;

        if (cmp.compare(arr[L], arr[M]) > 0) swap(arr, L, M);
        if (cmp.compare(arr[L], arr[R]) > 0) swap(arr, L, R);
        if (cmp.compare(arr[M], arr[R]) > 0) swap(arr, M, R);

        swap(arr, L + 1, M);
        return arr[L + 1];
    }

    public static <T> void sort(T[] arr, int L, int R, Comparator<? super T> cmp) {
        if (L + 1 >= R) {
            if (L + 1 == R && cmp.compare(arr[L], arr[R]) > 0)
                swap(arr, L, R);

            return;
        }

        final int pivot = partition(arr, L, R, cmp);
        sort(arr, L, pivot - 1, cmp);
        sort(arr, pivot + 1, R, cmp);
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}