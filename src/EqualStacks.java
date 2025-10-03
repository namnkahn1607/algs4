import java.util.Scanner;

public class EqualStacks {
    public int equalHeights(int[] h1, int[] h2, int[] h3) {
        int sum1 = totalHeights(h1);
        int sum2 = totalHeights(h2);
        int sum3 = totalHeights(h3);
        int top1 = 0;
        int top2 = 0;
        int top3 = 0;

        while (true) {
            if (top1 == h1.length || top2 == h2.length || top3 == h3.length)
                return 0;

            if (sum1 == sum2 && sum2 == sum3)
                return sum1;

            if (sum1 >= sum2 && sum1 >= sum3)
                sum1 -= h1[top1++];
            else if (sum2 >= sum1 && sum2 >= sum3)
                sum2 -= h2[top2++];
            else if (sum3 >= sum2 && sum3 >= sum1)
                sum3 -= h3[top3++];
        }
    }

    private int totalHeights(int[] h) {
        int sum = 0;

        for (int i : h)
            sum += i;

        return sum;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] h1 = new int[scan.nextInt()];
        int[] h2 = new int[scan.nextInt()];
        int[] h3 = new int[scan.nextInt()];

        for (int i = 0; i < h1.length; ++i)
            h1[i] = scan.nextInt();

        for (int i = 0; i < h2.length; ++i)
            h2[i] = scan.nextInt();

        for (int i = 0; i < h3.length; ++i)
            h3[i] = scan.nextInt();

        System.out.println(new EqualStacks().equalHeights(h1, h2, h3));
    }
}
