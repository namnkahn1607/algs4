package module4;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        Iterator<String> it = queue.iterator();

        for (int i = 0; i < k; ++i) {
            System.out.println(it.next());
        }
    }
}
