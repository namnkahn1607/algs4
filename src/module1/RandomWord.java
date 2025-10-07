package module1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        double i = 1.0;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();

            if (StdRandom.bernoulli(1 / i))
                champion = word;

            ++i;
        }

        StdOut.println(champion);
    }
}