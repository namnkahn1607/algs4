import edu.princeton.cs.algs4.Insertion;

import java.io.File;
import java.util.Scanner;

public class FileSorter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        File directory = new File(scan.nextLine());
        File[] files = directory.listFiles();

        if (files == null)
            throw new IllegalArgumentException();

        Insertion.sort(files);

        for (File file : files)
            System.out.println(file.getName());

        scan.close();
    }
}