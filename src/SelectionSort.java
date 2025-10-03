import java.util.Scanner;

public class SelectionSort {
    public void sort(int[] array) {
        final int m = array.length;

        for (int i = 0; i < m - 1; ++i) {
            int minIndex = i;

            for (int j = i + 1; j < m; ++j) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }

            if (minIndex != i) {
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] array = new int[N];

        for (int i = 0; i < N; ++i)
            array[i] = scan.nextInt();

        new SelectionSort().sort(array);

        for (int val : array)
            System.out.print(val + " ");
    }
}
