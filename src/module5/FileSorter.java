package module5;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileSorter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File directory = new File(scan.nextLine());
        File[] files = directory.listFiles();

        if (files != null) {
            Arrays.sort(files);

            for (File file : files) {
                StdOut.println(file.getName());
            }
        }
    }
}
