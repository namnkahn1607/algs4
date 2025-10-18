package module6;

import java.util.Comparator;

public class HyperMergeSort {
    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        sort(arr, Comparator.naturalOrder());
    }

    public static <T> void sort(T[] arr, Comparator<? super T> cmp) {
        if (arr == null || arr.length < 1) {
            return;
        }

        if (cmp == null) {
            throw new IllegalArgumentException(
                    "comparator can not be null"
            );
        }

        sort(arr, 0, arr.length - 1, cmp);
    }

    @SuppressWarnings("unchecked")
    public static <T> void merge(T[] arr, int L, int M, int R, Comparator<? super T> cmp) {
        final int size1 = M - L + 1;
        final int size2 = R - M;

        T[] arr1 = (T[]) new Object[size1];
        T[] arr2 = (T[]) new Object[size2];

        System.arraycopy(arr, L, arr1, 0, size1);
        System.arraycopy(arr, M + 1, arr2, 0, size2);

        int i = 0, j = 0, k = L;

        while (i < size1 && j < size2) {
            if (cmp.compare(arr[i], arr[j]) < 0) {
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

    public static <T> void sort(T[] arr, int L, int R, Comparator<? super T> cmp) {
        if (L + 1 >= R) {
            if (L + 1 == R && cmp.compare(arr[L], arr[R]) > 0) {
                swap(arr, L, R);
                return;
            }
        }

        final int M = L + (R - L) / 2;
        sort(arr, L, M, cmp);
        sort(arr, M + 1, R, cmp);
        merge(arr, L, M, R, cmp);
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}