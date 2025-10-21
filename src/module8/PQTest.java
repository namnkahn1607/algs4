package module8;

import edu.princeton.cs.algs4.StdIn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PQTest {
    public static Transaction[] inputTransactions() throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("M/d/yyyy");
        List<Transaction> transactions = new ArrayList<>();

        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            String dateStr = StdIn.readString();
            double amount = StdIn.readDouble();

            transactions.add(new Transaction(name, inputFormat.parse(dateStr), amount));
        }

        return transactions.toArray(new Transaction[0]);
    }

    public static void main(String[] args) throws ParseException {
        Transaction[] transactions = inputTransactions();
        final int M = 5;

        // largest M items in a stream of N items
        MinHeap<Transaction> minPQ = new MinHeap<>(10);

        for (Transaction transaction : transactions) {
            minPQ.insert(transaction);

            if (minPQ.size() > M) {
                minPQ.remove();
            }
        }

        System.out.println(Arrays.toString(minPQ.toArray()));

        // smallest M items in a stream of N items
        MaxHeap<Transaction> maxPQ = new MaxHeap<>(10);

        for (Transaction transaction : transactions) {
            maxPQ.insert(transaction);

            if (maxPQ.size() > M) {
                maxPQ.remove();
            }
        }

        System.out.println(Arrays.toString(maxPQ.toArray()));
    }
}