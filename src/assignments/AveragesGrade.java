// 1.1.21
package assignments;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class AveragesGrade {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        List<Integer> firsts = new ArrayList<>();
        List<Integer> seconds = new ArrayList<>();

        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int first = StdIn.readInt();
            int second = StdIn.readInt();

            names.add(name);
            firsts.add(first);
            seconds.add(second);
        }

        for (int i = 0; i < names.size(); ++i) {
            String name = names.get(i);
            int first = firsts.get(i);
            int second = seconds.get(i);

            StdOut.printf("%s %d %d %3.f", name, first, second, first * 1.0 / second);
        }
    }
}