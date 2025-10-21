package module8;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Date;

public record Transaction(String name, Date date, double amount) implements Comparable<Transaction> {
    public static final Comparator<Transaction> BY_NAME = new ByName();
    public static final Comparator<Transaction> BY_DATE = new ByDate();
    public static final Comparator<Transaction> BY_AMOUNT = new ByAmount();

    private static class ByName implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.name().compareTo(o2.name());
        }
    }

    private static class ByDate implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.date().compareTo(o2.date());
        }
    }

    private static class ByAmount implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return Double.compare(o1.amount(), o2.amount());
        }
    }

    public Transaction {
        if (name == null || date == null) {
            throw new IllegalArgumentException("argument passing to Transaction is null");
        }
    }
    
    @Override
    public @NotNull String toString() {
        return String.format(
                "Transaction[name=%s, date=%s, amount=%.2f",
                name(), date().toString(), amount()
        );
    }

    @Override
    public int compareTo(@NotNull Transaction o) {
        return Double.compare(amount(), o.amount());
    }
}