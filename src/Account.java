import java.util.ArrayList;

public class Account {

    private double balance;
    private final ArrayList<Transaction> transitionList = new ArrayList<>();

    /**
     * private deposit.
     */
    private void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("So tien ban nap vao khong hop le!");
            return;
        }

        balance += amount;
        transitionList.add(new Transaction(Transaction.DEPOSIT, amount, balance));
    }

    /**
     * private withdraw.
     */
    private void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("So tien ban rut ra khong hop le!");
            return;
        } else if (amount > balance) {
            System.out.println("So tien ban rut vuot qua so du!");
            return;
        }

        balance -= amount;
        transitionList.add(new Transaction(Transaction.WITHDRAW, amount, balance));
    }

    /**
     * public addTransaction.
     */
    public void addTransaction(double amount, String operation) {
        switch (operation) {
            case "deposit":
                deposit(amount);
                break;

            case "withdraw":
                withdraw(amount);
                break;

            default:
                System.out.println("Yeu cau khong hop le!");
        }
    }

    /**
     * public printTransaction.
     */
    public void printTransaction() {
        for (int i = 0; i < transitionList.size(); ++i) {
            Transaction current = transitionList.get(i);
            String operation = current.getOperation().equals("deposit") ? "Nap tien" : "Rut tien";

            System.out.printf("Giao dich %d: %s $%.2f. So du luc nay: $%.2f.\n",
                    i + 1, operation, current.getAmount(), current.getBalance());
        }
    }
}