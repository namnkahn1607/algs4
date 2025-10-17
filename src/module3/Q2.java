package module3;/* Analysis of Algorithms interview question:
* search in a bitonic array
* use: 3logN compares in the worst case */

public class Q2 {

    public int search(int[] arr, int target) {
        int peakIndex = findPeak(arr);

        int ASC = binarySearchASC(arr, peakIndex, target);

        if (ASC != -1)
            return ASC;

        return binarySearchDESC(arr, peakIndex + 1, target);
    }

    private int findPeak(int[] arr) {
        int L = 0, R = arr.length - 1;

        while (L < R) {
            final int M = L + (R - L) / 2;

            if (arr[M] < arr[M + 1])
                L = M + 1;
            else
                R = M;
        }

        return L;
    }

    private int binarySearchASC(int[] arr, int midBound, int target) {
        int L = 0, R = midBound;

        while (L <= R) {
            final int M = L + (R - L) / 2;

            if (arr[M] == target)
                return M;
            else if (arr[M] < target)
                L = M + 1;
            else
                R = M - 1;
        }

        return -1;
    }

    private int binarySearchDESC(int[] arr, int midBound, int target) {
        int L = midBound, R = arr.length - 1;

        while (L <= R) {
            final int M = L + (R - L) / 2;

            if (arr[M] == target)
                return M;
            else if (arr[M] < target)
                R = M - 1;
            else
                L = M + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 8, 12, 6, 4, 3, 1};

        System.out.println(new Q2().search(arr, 16));
    }
}